package pharmacy.web.warehouse.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.DrugType;

public interface DrugTypeService {
	
	//下拉框药品大类
	ArrayList<String> drugTypeFrist();
	//下拉框药品小类
	ArrayList<DrugType> drugTypeSecond(String drugfrist);
	//药品类型管理表格展示
	ArrayList<DrugType> drugTypeInit(RowBounds rb,String dtname);
	//药品类型分页
	int countDrugType(String dtname);
	//新增药品类型
	boolean addType(String tname,String fname);
	//删除药品类型
	boolean delType(int dtid);
	//修改药物类型
	boolean alterType(String newtype,int dtid);
	//新增药品大类
	boolean addBig(String bigtype);
	//判断药品大类名是否有重复
	ArrayList<String> judgeBigType(String bigtype);
	//删除药品大类
	boolean delBig(String bigtype);
	//修改药品大类
	boolean alterBig(String newbigtype,String oldbigtype);

}
