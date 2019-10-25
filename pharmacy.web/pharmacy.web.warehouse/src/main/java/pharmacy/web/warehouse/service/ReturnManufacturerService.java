package pharmacy.web.warehouse.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.ReturnManufacturerInfDto;

public interface ReturnManufacturerService {

	//查询数据
	List<ReturnManufacturerInfDto> initReturnManufacturer(String drugname, String supply, String uname,RowBounds rb);
	//查询页码
	int initReturnManufacturerPageNum( String drugname, String supply, String uname);
	//减少库存数
	String reduceInventories( String inventoryid, String drug_count); 
}
