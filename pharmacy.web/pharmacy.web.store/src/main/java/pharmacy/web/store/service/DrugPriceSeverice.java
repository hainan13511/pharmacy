package pharmacy.web.store.service;

import java.util.List;

import pharmacy.web.store.model.dto.DrugPriceDto;

public interface DrugPriceSeverice {

	//查询药品价格
	List<DrugPriceDto> list(String start,String end,String startTime,String endTime,String operate,String drugname);
	//查询总数据
	int listCount(String startTime,String endTime,String operate,String drugname);
}
