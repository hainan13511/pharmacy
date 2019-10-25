package pharmacy.web.store.service;

import java.util.List;

import pharmacy.web.store.model.dto.InventoryDto;

public interface InventoryService {

	//查询药品库存查询
	List<InventoryDto> ShowInventory(String start ,String end ,String startTime,String endTime,String operate,String drugName);
	
	//查询总数据
	int InventoryCount(String startTime,String endTime,String operate,String drugName);
}
