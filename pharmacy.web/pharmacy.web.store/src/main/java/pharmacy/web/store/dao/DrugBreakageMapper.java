package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugBreakageDto;

public interface DrugBreakageMapper {

	//查询药品报损明细信息
	List<DrugBreakageDto> breakageList(RowBounds rb,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate);
	//查询药品报损的总数据
	int breakageCount(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate);
}
