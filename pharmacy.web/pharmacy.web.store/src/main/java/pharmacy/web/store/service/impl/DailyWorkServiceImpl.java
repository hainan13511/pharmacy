package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.DrugInf;
import pharmacy.common.model.DrugType;
import pharmacy.web.store.dao.DailyWorkMapper;
import pharmacy.web.store.dao.DrugSendMapper;
import pharmacy.web.store.model.dto.DrugApplyDetailDto;
import pharmacy.web.store.model.dto.DrugApplyDto;
import pharmacy.web.store.model.dto.DrugInfDto;
import pharmacy.web.store.model.dto.ListDrugInfDto;
import pharmacy.web.store.service.DailyWorkService;
import pharmacy.web.store.solr.DrugInfSolr;
import pharmacy.web.store.solr.SolrUtil;
@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    @Autowired
    private DailyWorkMapper dailyWorkMapper;
    @Autowired
    private DrugSendMapper drugSendMapper;
    // solr目录名
    private static String core = "new_core";
    
    @Transactional(rollbackFor = Exception.class)
    public List<DrugType> listDrugType(Integer fid) {
        // 根据药品大类ID查询下属分类
        List<DrugType> list = drugSendMapper.listDrugType(fid);
        return list;
    }
    @Transactional(rollbackFor = Exception.class)
    public DrugApplyDto listDrugApply(Integer start, Integer end, String drugName, String dateStart, String dateEnd,
            Integer checkState,Integer userId) {
        // 查询药品申请表与总数
        RowBounds rb = new RowBounds(start, end);
        List<DrugApplyDetailDto> list = dailyWorkMapper.listDrugApply(rb, drugName, dateStart, dateEnd, checkState,userId);
        Integer count = dailyWorkMapper.countDrugApply(drugName, dateStart, dateEnd, checkState,userId);
        if(list.size()==0||count==0) {
            return null;
        }
        DrugApplyDto dad = new DrugApplyDto(list, count);
        return dad;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateApply(Integer id, Integer altNum) {
        // 修改请领单
        Integer i = dailyWorkMapper.updateApply(id, altNum);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertDrugApply(String drugName, Integer drugNum, Integer userId) {
        // 新增请领单
        Integer drugId = dailyWorkMapper.getDrug(drugName);
        if(drugId == null) {
            return -1;
        }
        Integer i = dailyWorkMapper.insertDrugApply(drugName, drugNum, userId);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ListDrugInfDto listDrugInf(Integer start, Integer end, DrugInfSolr drugInf) {
        // 查询药品表与总数
//        RowBounds rb = new RowBounds(start, end);
        List<DrugInfDto> list;
        try {
            list = SolrUtil.getDocument(core, drugInf, true,start,end);
            Integer count = dailyWorkMapper.countDrugInfDto(drugInf.getDrugName());
            if(list.size()==0||count==0) {
//                System.out.println("list.size():"+list.size()+"count:"+count);
                return null;
            }
            ListDrugInfDto ld = new ListDrugInfDto(list, count);
            return ld;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDrugPrice(Integer userId, Integer beforePrice, Integer price, Integer drugId) {
        // 调整药品零售价
        Integer a = dailyWorkMapper.updateDrugPrice(price,drugId);//修改药品零售价
        DrugInfDto drugInf = new DrugInfDto();
        drugInf.setDrugId(drugId);
        drugInf.setDsale(price);
        try {
            List<DrugInfDto> list = SolrUtil.getDocument(core, drugInf, true,0,5);
            if(list.size()>0) {
                String id = list.get(0).getId();
                drugInf.setId(id);
                SolrUtil.updateDate(core, drugInf);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(a == 0) {
            return a;
        }
        Integer i = dailyWorkMapper.insertDrugPriceLog(userId,beforePrice,price,drugId);
        return i;
    }
    @Override
    public List<DrugInf> listDrugInf(Integer dtId) {
        // TODO Auto-generated method stub
        List<DrugInf> list = drugSendMapper.listDrugInf(dtId);
        return list;
    }

}
