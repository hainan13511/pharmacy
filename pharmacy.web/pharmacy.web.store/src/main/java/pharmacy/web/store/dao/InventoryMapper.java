package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.InventoryDto;

public interface InventoryMapper {

	//查询药品库存的信息
	List<InventoryDto> InventoryList(RowBounds rb, @Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate,@Param("drugName") String drugName);

	//查询药品库存的总数据
	int InventoryCount( @Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate,@Param("drugName") String drugName);
}
