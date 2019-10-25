package pharmacy.web.store.service;

import java.util.List;

import pharmacy.common.model.DrugInf;
import pharmacy.common.model.DrugType;
import pharmacy.web.store.model.dto.DrugApplyDto;
import pharmacy.web.store.model.dto.ListDrugInfDto;
import pharmacy.web.store.solr.DrugInfSolr;

public interface DailyWorkService {

    // 根据药品大类ID查询下属分类
    List<DrugType> listDrugType(Integer fid);

    // 搜索查询药品
    List<DrugInf> listDrugInf(Integer dtId);
    
    // 查询药品申请表与总数
    DrugApplyDto listDrugApply(Integer start, Integer end, String drugName, String dateStart, String dateEnd,
            Integer checkState, Integer userId);

    // 修改请领单
    Integer updateApply(Integer id, Integer altNum);

    // 新增请领单
    Integer insertDrugApply(String drugName, Integer drugNum, Integer userId);

    // 查询药品表与总数
    ListDrugInfDto listDrugInf(Integer start, Integer end, DrugInfSolr drugInf);

    // 调整药品零售价
    Integer updateDrugPrice(Integer userId,Integer beforePrice, Integer price, Integer drugId);
}
