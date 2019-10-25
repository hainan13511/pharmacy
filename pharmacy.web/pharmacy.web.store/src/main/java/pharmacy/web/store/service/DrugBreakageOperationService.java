package pharmacy.web.store.service;

import pharmacy.web.store.model.dto.DrugAndInventoryDto;

public interface DrugBreakageOperationService {

    // 查询药品表与药房药库库存与药品表总数
    DrugAndInventoryDto listDrugAndInventory(Integer start, Integer end, String drugName);

    // 药品报损
    Integer drugBreakage(Integer drugId, Integer userId, Integer batch, Integer num, String cause);
}
