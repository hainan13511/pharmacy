package pharmacy.web.warehouse.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import pharmacy.common.utils.StringUtil;

/**
 * Created by Tiger-Li on 2017/10/22.
 */
public class SolrUtil {
    public static String SOLR_URL = "http://127.0.0.1:8080/solr";

    /**
     * 获取solr服务
     *
     * @return
     */
    public static HttpSolrClient.Builder getSolrClient(String core) {
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(SOLR_URL + core);
        return builder;
    }

    /**
     * 添加文档，通过bean方式
     *
     * @param drugInf
     * @param core
     * @throws Exception
     */
    public static void addDocumentByBean(DrugInf drugInf, String core) throws Exception {
    	System.out.println("插入了");
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        solrClient.addBean(drugInf);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 添加文档，通过bean方式
     *
     * @param persons
     * @param core
     * @throws Exception
     */
    public static void addDocumentByBeans(List<DrugInf> persons, String core) throws Exception {
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        solrClient.addBeans(persons);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 根据id删除索引
     *
     * @param drugInfId
     * @param core
     * @throws Exception
     */
    public static void deleteDocumentById(String drugInfId, String core) throws Exception {
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        solrClient.deleteById(drugInfId);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 根据id集合删除索引
     *
     * @param ids
     * @param core
     * @throws Exception
     */
    public static void deleteDocumentByIds(List<String> ids, String core) throws Exception {
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        solrClient.deleteById(ids);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 查询方法
     * 
     * @param core
     * @param drugInf
     * @param isHighlighting
     * @return
     * @throws Exception
     */
    public static List<DrugInfDtoSolr> getDocument(String core, DrugInf drugInf, boolean isHighlighting, Integer start,
            Integer end) throws Exception {
        // 创建连接对象
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
//        int page = 1;
//        int rows = 10;
        SolrQuery solrQuery = new SolrQuery("*:*"); // 构造搜索条件
        if (drugInf.getDrugId()+""!=""&&drugInf.getDrugId()!=0) {
        	System.out.println("搜索了" + drugInf.getDrugId());
        	solrQuery.setQuery("drugid:" + drugInf.getDrugId());
        }
        if (!StringUtil.isEmpty(drugInf.getDrugName())) {// 搜索关键词
            solrQuery.setQuery("drugid:*" + drugInf.getDrugId() + "*");
            String str = parseKeywords(drugInf.getDrugName());
            System.out.println("搜索了" + str);
            solrQuery.setQuery("drug_name:*" + str + "*");
        }
        // 分片信息
//        solrQuery.setFacet(true).setFacetMinCount(1).setFacetLimit(5).addFacetField("desc");
        // 设置分页
//        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
//        solrQuery.setRows(rows);
        solrQuery.setStart(start);
        solrQuery.setRows(end);
        // 设置查询的排序参数，1-排序的字段名，2-排序方式（ORDER：asc desc）
        solrQuery.setSort("drugid", ORDER.asc);
        // 是否需要高亮
        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); // 开启高亮组件
            solrQuery.addHighlightField("drug_name");// 高亮字段
            solrQuery.setHighlightSimplePre("<font color='red'>");// 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePost("</font>");// 后缀
        }

        QueryResponse queryResponse = solrClient.query(solrQuery);
        List<DrugInfDtoSolr> persons = queryResponse.getBeans(DrugInfDtoSolr.class);
        // 直接获取的反回结果
//        SolrDocumentList docs = queryResponse.getResults();
        if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (DrugInfDtoSolr person : persons) {
                    String id = person.getId();
                    // 验证索引
                    if (!highlighting.getKey().equals(id)) {
                        continue;
                    }
                    // 设置高亮
                    if (highlighting.getValue().get("drug_name") != null) {
                        String str = highlighting.getValue().get("drug_name").get(0);
                        str.replaceAll("\\[", "").replaceAll("\\]", "");
                        person.setDrugName(str);
                    }
                    break;
                }
            }
        }
        return persons;
    }

    /**
     * 添加
     * @param core
     * @param drugInf
     * @throws SolrServerException
     * @throws IOException
     */
    public static void addDate(String core, DrugInf drugInf) throws SolrServerException, IOException {
        // 创建连接对象
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        System.out.println("添加了");
        solrClient.addBean(drugInf);
        // 提交
        solrClient.commit();
    }
    /**
     * 修改
     * 
     * @param core
     * @param drugInf
     * @throws SolrServerException
     * @throws IOException
     */
    public static void updateDate(String core, DrugInfDtoSolr drugInf) throws SolrServerException, IOException {
        // 创建连接对象
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();
        System.out.println("修改了");
        // 2.创建一个文档对象
        SolrInputDocument inputDocument = new SolrInputDocument();
        inputDocument.addField("id", drugInf.getId());
        // 局部更新需要借助Map, 这个Map的Key必须是“set”
        Map<String, String> publisherMap = new HashMap<>();
        publisherMap.put("set", drugInf.getDsale() + "");
        // 修改图书的出版社, key是field, value是上述的Map
        inputDocument.addField("dsale", publisherMap);
        // _version_值为0: 如果待修改的文档存在, 则修改; 如果不存在, 则添加
        inputDocument.addField("_version_", 0);
        // 3.将文档写入索引库中
        solrClient.add(inputDocument);
        // 提交
        solrClient.commit();
    }

    /**
     * 删除
     * 
     * @param core
     * @param drugInf
     * @throws Exception
     */
    public static void deleteDocment(String core, DrugInf drugInf) throws Exception {

        // 建立solr连接
        HttpSolrClient.Builder builder = getSolrClient("/" + core);
        SolrClient solrClient = builder.build();

        // 根据id删除文档
        solrClient.deleteById("93ce4d22-8e8a-44d7-a871-b60575d969a7");

        // 提交操作
        solrClient.commit();
    }

    /**
     * solr 官方的处理方法 如果query中带有非法字符串，结果直接报错，所以你对用户的输入必须要先做处理
     * 
     * @param s
     * @return
     */
    protected static String parseKeywords(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^'
                    || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?'
                    || c == '|' || c == '&' || c == ';' || c == '/' || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获取分词结果
     * 
     * @param solrClient
     * @param keywords
     * @return
     * @throws Exception
     */
//    public static String splitWords(HttpSolrClient solrClient,String keywords) throws Exception{
//        SolrQuery query = new SolrQuery();
//
//        query.add(CommonParams.QT, "/analysis/field"); // query type
//
//        query.add(AnalysisParams.FIELD_VALUE, keywords);
//
//        query.add(AnalysisParams.FIELD_TYPE, "text_ik");
//        QueryResponse response=solrClient.query(query);
//
//        NamedList<Object> analysis =  (NamedList<Object>) response.getResponse().get("analysis");// analysis node
//
//        NamedList<Object> field_types =  (NamedList<Object>) analysis.get("field_types");// field_types node
//
//        NamedList<Object> text_ik =  (NamedList<Object>) field_types.get("text_ik");// text_chinese node
//
//        NamedList<Object> index =  (NamedList<Object>) text_ik.get("index");// index node
//
//        List<SimpleOrderedMap<String>> list =  (ArrayList<SimpleOrderedMap<String>>) index.get("org.wltea.analyzer.lucene.IKTokenizer");// tokenizer node
//
//        String nextQuery="";
//        for(Iterator<SimpleOrderedMap<String>> iter = list.iterator(); iter.hasNext();)
//
//        {
//
//            nextQuery += iter.next().get("text") + " ";
//
//        }
//
//
//        return nextQuery.trim();
//    }

    /**
     * 提交以及关闭服务
     *
     * @param solrClient
     * @throws Exception
     */
    public static void commitAndCloseSolr(SolrClient solrClient) throws Exception {
        solrClient.commit();
        solrClient.close();
    }

    /**
     * <b>function:</b> query 基本用法测试
     */
//    public void queryCase() {
//        //AND 并且
//        SolrQuery params = new SolrQuery("name:apple AND manu:inc");
//
//        //OR 或者
//        params.setQuery("name:apple OR manu:apache");
//        //空格 等同于 OR
//        params.setQuery("name:server manu:dell");
//
//        //params.setQuery("name:solr - manu:inc");
//        //params.setQuery("name:server + manu:dell");
//
//        //查询name包含solr apple
//        params.setQuery("name:solr,apple");
//        //manu不包含inc
//        params.setQuery("name:solr,apple NOT manu:inc");
//
//        //50 <= price <= 200
//        params.setQuery("price:[50 TO 200]");
//        params.setQuery("popularity:[5 TO 6]");
//        //params.setQuery("price:[50 TO 200] - popularity:[5 TO 6]");
//        //params.setQuery("price:[50 TO 200] + popularity:[5 TO 6]");
//
//        //50 <= price <= 200 AND 5 <= popularity <= 6
//        params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
//        params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");
//
//        //过滤器查询，可以提高性能 filter 类似多个条件组合，如and
//        //params.addFilterQuery("id:VA902B");
//        //params.addFilterQuery("price:[50 TO 200]");
//        //params.addFilterQuery("popularity:[* TO 5]");
//        //params.addFilterQuery("weight:*");
//        //0 < popularity < 6  没有等于
//        //params.addFilterQuery("popularity:{0 TO 6}");
//
//        //排序
//        params.addSortField("id", ORDER.asc);
//
//        //分页：start开始页，rows每页显示记录条数
//        //params.add("start", "0");
//        //params.add("rows", "200");
//        //params.setStart(0);
//        //params.setRows(200);
//
//        //设置高亮
//        params.setHighlight(true); // 开启高亮组件
//        params.addHighlightField("name");// 高亮字段
//        params.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
//        params.setHighlightSimplePost("</font>");//后缀
//        params.setHighlightSnippets(1);//结果分片数，默认为1
//        params.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
//
//        //分片信息
//        params.setFacet(true)
//                .setFacetMinCount(1)
//                .setFacetLimit(5)//段
//                .addFacetField("name")//分片字段
//                .addFacetField("inStock");
//
//        //params.setQueryType("");
//
//        try {
//            QueryResponse response = server.query(params);
//
//        /*List<Index> indexs = response.getBeans(Index.class);
//        for (int i = 0; i < indexs.size(); i++) {
//            fail(indexs.get(i));
//        }*/
//
//            //输出查询结果集
//            SolrDocumentList list = response.getResults();
//            fail("query result nums: " + list.getNumFound());
//            for (int i = 0; i < list.size(); i++) {
//                fail(list.get(i));
//            }
//
//            //输出分片信息
//            List<FacetField> facets = response.getFacetFields();
//            for (FacetField facet : facets) {
//                fail(facet);
//                List<Count> facetCounts = facet.getValues();
//                for (FacetField.Count count : facetCounts) {
//                    System.out.println(count.getName() + ": " + count.getCount());
//                }
//            }
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        }
//    }

//    12、 分片查询、统计
//
///**
// * <b>function:</b> 分片查询， 可以统计关键字及出现的次数、或是做自动补全提示
// */
//
//    public void facetQueryCase() {
//        SolrQuery params = new SolrQuery("*:*");
//
//        //排序
//        params.addSortField("id", ORDER.asc);
//
//        params.setStart(0);
//        params.setRows(200);
//
//        //Facet为solr中的层次分类查询
//        //分片信息
//        params.setFacet(true)
//                .setQuery("*:*")
//                .setFacetMinCount(1)
//                .setFacetLimit(5)//段
//                        //.setFacetPrefix("electronics", "cat")
//                .setFacetPrefix("cor")//查询manu、name中关键字前缀是cor的
//                .addFacetField("manu")
//                .addFacetField("name");//分片字段
//
//        try {
//            QueryResponse response = server.query(params);
//
//            //输出查询结果集
//            SolrDocumentList list = response.getResults();
//            fail("Query result nums: " + list.getNumFound());
//
//            for (int i = 0; i < list.size(); i++) {
//                fail(list.get(i));
//            }
//
//            fail("All facet filed result: ");
//            //输出分片信息
//            List<FacetField> facets = response.getFacetFields();
//            for (FacetField facet : facets) {
//                fail(facet);
//                List<Count> facetCounts = facet.getValues();
//                for (FacetField.Count count : facetCounts) {
//                    //关键字 - 出现次数
//                    fail(count.getName() + ": " + count.getCount());
//                }
//            }
//
//            fail("Search facet [name] filed result: ");
//            //输出分片信息
//            FacetField facetField = response.getFacetField("name");
//            List<Count> facetFields = facetField.getValues();
//            for (Count count : facetFields) {
//                //关键字 - 出现次数
//                fail(count.getName() + ": " + count.getCount());
//            }
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        }
//    }

}
