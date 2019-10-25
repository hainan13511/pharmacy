package pharmacy.web.warehouse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.DrugShopInfDto;
import pharmacy.web.warehouse.model.dto.OutPutInfDto;

public interface DrugShopInfMapper {
	
	//药品采购初始化表格
	ArrayList<DrugShopInfDto> drugShopInfInit(RowBounds rb,@Param("drugname")String drugname,
			@Param("supply")String supply,@Param("uname")String uname);
	//药品采购分页
	Integer drugShopCount(@Param("drugname")String drugname,
			@Param("supply")String supply,@Param("uname")String uname);
	//药品出入库明细表
	ArrayList<OutPutInfDto> outPutInfInit(RowBounds rb,@Param("drugname")String drugname,
		@Param("uname")String uname);
	//药品出入库明细表分页
	Integer outPutCount(@Param("drugname")String drugname,@Param("uname")String uname);

}
