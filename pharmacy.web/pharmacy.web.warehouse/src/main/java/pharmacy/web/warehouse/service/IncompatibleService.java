package pharmacy.web.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.DrugInf;

public interface IncompatibleService {

	//禁忌药物展示
	ArrayList<String> incomDrug(int drugid);
	//配置药品禁忌
	boolean incomSet(List<String>dname1,List<String> dname2);
	//禁忌药物展示除了自己
	ArrayList<DrugInf> outIncomDrug(String dname);
	//删除禁忌药品
	boolean delIncomp(String delname1,String delname2);
	
}
