package pharmacy.web.warehouse.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.DrugShopInfDto;
import pharmacy.web.warehouse.model.dto.OutPutInfDto;

public interface DrugShopInfService {
	
	//药品采购初始化表格
	ArrayList<DrugShopInfDto> drugShopInfInit(RowBounds rb,String drugname,String supply,String uname);
	//药品采购分页
	Integer drugShopCount(String drugname,String supply,String uname);
	//药品出入库明细表
	ArrayList<OutPutInfDto> outPutInfInit(RowBounds rb,String drugname,String uname);
	//药品出入库明细表分页
	Integer outPutCount(String drugname,String uname);
	

}
