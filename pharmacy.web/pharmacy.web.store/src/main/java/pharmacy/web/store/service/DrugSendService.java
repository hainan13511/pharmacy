package pharmacy.web.store.service;

import java.util.List;

import pharmacy.common.model.DrugSales;
import pharmacy.common.model.DrugType;
import pharmacy.web.store.model.dto.DrugInfDto;
import pharmacy.web.store.solr.DrugInfSolr;

public interface DrugSendService {

    // 根据药品大类ID查询下属分类
    List<DrugType> listDrugType(Integer fid);

    // 搜索查询药品
    List<DrugInfDto> listDrugInf(DrugInfSolr drugInf);

    // 药品销售记录
    Integer drugSendLog(List<DrugSales> list, Integer uid);
    
}
