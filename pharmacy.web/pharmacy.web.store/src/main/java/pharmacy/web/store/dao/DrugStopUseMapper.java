package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugStopUseDto;

public interface DrugStopUseMapper {

	//查询数据
	List<DrugStopUseDto> drugStopUse(RowBounds rb, @Param("startTime") String startTime,@Param("endTime") String endTime,@Param("stateName") String stateName,@Param("drugName") String drugName);
	
	//查看总数据
	int count(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("stateName") String stateName,@Param("drugName") String drugName);

	//修改药品的状态
	int UpdateDrugState(@Param("drugid") String drugid,@Param("state") String state);
}
