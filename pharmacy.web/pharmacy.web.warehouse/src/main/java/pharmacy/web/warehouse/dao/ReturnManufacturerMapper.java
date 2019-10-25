package pharmacy.web.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.ReturnManufacturerInfDto;

public interface ReturnManufacturerMapper {
	
	//初始化数据
	List<ReturnManufacturerInfDto> initReturnManufacturer(@Param("drugname") String drugname,@Param("supply") String supply,@Param("uname") String uname,RowBounds rb);
	//获取页码
	int initReturnManufacturerPageNum(@Param("drugname") String drugname,@Param("supply") String supply,@Param("uname") String uname);
	//查询库存
	int getInventoryCount(@Param("inventoryid") String inventoryid);
	//减少库存
	int reduceInventories(@Param("inventoryid") String inventoryid,@Param("drug_count") String drug_count); 
	
}
