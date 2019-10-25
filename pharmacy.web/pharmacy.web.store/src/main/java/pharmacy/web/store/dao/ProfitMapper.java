package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugSalesDto;

public interface ProfitMapper {

	//查询盈亏表的信息
	List<DrugSalesDto> profitList(RowBounds rb,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	//查询盈亏表的总数据
	int profitCount(@Param("startTime") String startTime,@Param("endTime") String endTime);
}
