package pharmacy.web.store.service;

import java.util.List;

import pharmacy.web.store.model.dto.DrugBreakageDto;

public interface DrugBreakageSeverice {

	//药品报损明细
	List<DrugBreakageDto> breakageList(String start,String end ,String startTime,String endTime,String operate);
	//查询数据
	int breakCount(String startTime,String endTime,String operate);
}
