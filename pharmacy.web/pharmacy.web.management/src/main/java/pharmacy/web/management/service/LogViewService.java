package pharmacy.web.management.service;

import java.util.List;

import pharmacy.web.management.model.dto.DrugLogDto;

public interface LogViewService {

	//日志查看初始化
	List<DrugLogDto> logList(String start,String end,String beginTime,String endTime,String operate );
	//查询日志的总数据
	int logCount(String beginTime,String endTime,String operate);
}
