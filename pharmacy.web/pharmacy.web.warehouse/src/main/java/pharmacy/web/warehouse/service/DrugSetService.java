package pharmacy.web.warehouse.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.common.model.DrugInf;
import pharmacy.web.warehouse.model.dto.DrugInfDto;
import pharmacy.web.warehouse.model.dto.DwdDto;
import pharmacy.web.warehouse.model.dto.MiniSetDto;

public interface DrugSetService {
	
	//药品设置
	boolean drugSet(String drugname,String chemicalname,String commonly,String specification,String dosage,String formula
			,String method,String markup,String spell,String five,String invoice,int anti
			,String dose,String numday,int dtname,int dcost,int dsale,MiniSetDto miniset);
	//药品设置展示初始化
	ArrayList<DrugInfDto> drugInfInit(RowBounds rb,String drugname);
	//药品设置展示数量页码
	int countDrug(String drugname);
	//药品采购插入
	boolean drugShop(int drugid,String supply,String drugtime,int amount,String uname,DwdDto dwddto,String unit);
	//药品详情展示
	ArrayList<DrugInfDto> drugShow(int drugid);
	//修改药品设置
	boolean alterDrug(String drugname,String chemicalname,String commonly,String specification,String dosage,String formula
			,String method,String markup,String spell,String five,String invoice,int anti
			,String dose,String numday,String dtname,int drugid,int dcost,int dsale);
	//删除药品
	boolean delDrug(int drugid);
	//医保编码设置
	boolean insurSet(int drugid,String insurance);
	//药品单位下拉框
	ArrayList<String> unitInf();
	//药品设置判断药品名是否已存在
	ArrayList<String> judgeDrugname(String drugname);
	
	
}
