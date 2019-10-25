package pharmacy.web.store.service;

import java.util.List;

import pharmacy.web.store.model.dto.DrugStopUseDto;

public interface DrugStopUseService {

	//查询药品的信息
	List<DrugStopUseDto> DrugStopuse(String start,String end,String startTime,String endTime,String stateName,String drugName);
	
	//查询总数据
	int count(String startTime,String endTime,String stateName,String drugName);
	
	//药品停用（修改状态）
	int updateState(String drugid);
	//药品恢复
	int updateStart(String drugId);
}
