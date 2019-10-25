package pharmacy.web.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.management.model.dto.DrugLogDto;

public interface DrugLogMapper {

	//查看初始化数据
	List<DrugLogDto> loglit(RowBounds rb,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate);
	//查询日志的总数据
	int logCount(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate);
}
