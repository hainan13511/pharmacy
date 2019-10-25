package pharmacy.web.warehouse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.DrugInf;

public interface IncompatibleMapper {
	
	//禁忌药物展示
	ArrayList<String> incomDrug(@Param("drugid")int drugid);
	//配置药品禁忌
	Integer incomSet(@Param("dname1")String dname1,@Param("dname2")String dname2);
	//展示还没配置禁忌的药物
	ArrayList<DrugInf> outIncomDrug(@Param("dname")String dname);
	//删除禁忌药物
	Integer delIncomp(@Param("delname1")String delname1,@Param("delname2")String delname2);
	

}
