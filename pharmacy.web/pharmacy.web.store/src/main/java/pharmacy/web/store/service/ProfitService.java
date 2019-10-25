package pharmacy.web.store.service;

import java.util.List;

import pharmacy.web.store.model.dto.DrugSalesDto;

public interface ProfitService {

	//查询药品盘点盈亏表
	List<DrugSalesDto> profitList(String start ,String end ,String startTime,String endTime);
	
	//查找药品的总数据
	int profitCount(String startTime,String endTime);
}
