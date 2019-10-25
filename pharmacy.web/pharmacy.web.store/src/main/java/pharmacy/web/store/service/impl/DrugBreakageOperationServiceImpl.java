package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.DrugBreakageOperationMapper;
import pharmacy.web.store.model.dto.DrugAndInventoryDto;
import pharmacy.web.store.model.dto.DrugBreakageOperationDto;
import pharmacy.web.store.service.DrugBreakageOperationService;

@Service
public class DrugBreakageOperationServiceImpl implements DrugBreakageOperationService {

    @Autowired
    private DrugBreakageOperationMapper drugBreakageOperationMapper;

    @Transactional(rollbackFor = Exception.class)
    public DrugAndInventoryDto listDrugAndInventory(Integer start, Integer end, String drugName) {
        // 查询药品表与药房药库库存与药品表总数
        RowBounds rb = new RowBounds(start, end);
        List<DrugBreakageOperationDto> list = drugBreakageOperationMapper.listDrugBreakageOperationDto(rb, drugName);
        Integer count = drugBreakageOperationMapper.countDrugBreakageOperationDto(drugName);
        if(list.size()==0||count==0) {
            return null;
        }
        DrugAndInventoryDto dad = new DrugAndInventoryDto(list, count);
        return dad;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer drugBreakage(Integer drugId, Integer userId, Integer batch, Integer num, String cause) {
        // 药品报损
        Integer drugBreakage = drugBreakageOperationMapper.drugBreakage(drugId, num, batch);//药品报损
        if(drugBreakage==null||drugBreakage<=0) {
            System.out.println("shibai:"+drugBreakage);
            return drugBreakage;
        }
        Integer log = drugBreakageOperationMapper.insertDrugBreakageLog(drugId, userId, num, cause);// 报损记录
        Integer sales = drugBreakageOperationMapper.insertDrugSales(drugId, userId, num);//计入销售成本
        if(log>0&&sales>0) {
            return log;
        }else {
            throw new RuntimeException();
        }
    }

}
