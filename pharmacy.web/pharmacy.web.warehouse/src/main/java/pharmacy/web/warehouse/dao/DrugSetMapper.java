package pharmacy.web.warehouse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import pharmacy.web.warehouse.model.dto.DrugInfDto;
import pharmacy.web.warehouse.model.dto.DwdDto;
import pharmacy.web.warehouse.model.dto.MiniSetDto;


public interface DrugSetMapper {
	//药品设置
	Integer drugSet(@Param("drugId")int drugid,@Param("drugname")String drugname,@Param("chemicalname")String chemicalname,@Param("commonly")String commonly
			,@Param("specification")String specification,@Param("dosage")String dosage,@Param("formula")String formula
			,@Param("method")String method,@Param("markup")String markup,@Param("spell")String spell
			,@Param("five")String five,@Param("invoice")String invoice,@Param("anti")int anti
			,@Param("dose")String dose,@Param("numday")String numday,@Param("dtname")int dtname
			,@Param("dcost")int dcost,@Param("dsale")int dsale);
	//药品低限量初始化默认入
	Integer miniSet(MiniSetDto miniset);
	//药品设置展示初始化
	ArrayList<DrugInfDto> drugInfInit(RowBounds rb,@Param("drugname")String drugname);
	//药品设置展示数量页码
	int countDrug(@Param("drugname")String drugname);
	//药品详情显示
	ArrayList<DrugInfDto> drugShow(@Param("drugid")int drugid);
	//修改药品设置
	Integer alterDrug(@Param("drugname")String drugname,@Param("chemicalname")String chemicalname,@Param("commonly")String commonly
			,@Param("specification")String specification,@Param("dosage")String dosage,@Param("formula")String formula
			,@Param("method")String method,@Param("markup")String markup,@Param("spell")String spell
			,@Param("five")String five,@Param("invoice")String invoice,@Param("anti")int anti
			,@Param("dose")String dose,@Param("numday")String numday,@Param("dtname")String dtname
			,@Param("drugid")int drugid,@Param("dcost")int dcost,@Param("dsale")int dsale);
	
	//删除药品
	Integer delDrug(@Param("drugid")int drugid);
	//插入药品采购统计表
	Integer drugShop(@Param("dwdId")int dwdId,@Param("drugid")int drugid,@Param("supply")String supply,@Param("drugtime")String drugtime
			,@Param("amount")int amount,@Param("uname")String uname,@Param("unit")String unit);
	//插入药品出入库明细表
	Integer putDrugShop(@Param("drugid")int drugid,@Param("uname")String uname,@Param("amount")int amount);
	//判断库存中是否有该药
	ArrayList<String> judgeDrugCount(@Param("drugid")int drugid);
	//增加新药的库存
	Integer addDrugCount(DwdDto dwddto);
	//医保编码设置
	Integer insurSet(@Param("drugid")int drugid,@Param("insurance")String insurance);
	//下拉框药品单位
	ArrayList<String>unitInf();
	//药品设置判断药品名是否已存在
	ArrayList<String> judgeDrugname(@Param("drugname")String drugname);
	
}
