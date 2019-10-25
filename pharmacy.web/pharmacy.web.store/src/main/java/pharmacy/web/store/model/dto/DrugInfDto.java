package pharmacy.web.store.model.dto;

import org.apache.solr.client.solrj.beans.Field;

import pharmacy.web.store.solr.DrugInfSolr;

/**
 * 
 * <p>
 * Title : DrugInfDto
 * </p>
 * <p>
 * Description : 药品附加类型名称dto
 * </p>
 * <p>
 * DevelopTools : Eclipse_x64_v4.10.0
 * </p>
 * <p>
 * DevelopSystem : windows 10
 * </p>
 * <p>
 * Company : org.yhn
 * </p>
 * 
 * @author : Yhn
 * @date : 2019年8月29日 上午11:48:43
 * @version : 1.0.0
 */
public class DrugInfDto extends DrugInfSolr {

    private static final long serialVersionUID = 1L;
    @Field
    private String drugTypeName;
    @Field("id")
    private String id;

    public DrugInfDto() {
        super();
    }

    public DrugInfDto(String drugTypeName) {
        super();
        this.drugTypeName = drugTypeName;
    }

    public String getDrugTypeName() {
        return drugTypeName;
    }

    public void setDrugTypeName(String drugTypeName) {
        this.drugTypeName = drugTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugInfDto [drugTypeName=");
        builder.append(drugTypeName);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
