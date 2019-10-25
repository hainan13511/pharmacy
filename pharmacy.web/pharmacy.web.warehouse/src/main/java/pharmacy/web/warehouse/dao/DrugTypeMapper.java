package pharmacy.web.warehouse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.DrugType;

public interface DrugTypeMapper {
	
	//下拉框药品大类
	ArrayList<String> drugTypeFrist();
	//下拉框药品小类
	ArrayList<DrugType> drugTypeSecond(@Param("drugfrist")String drugfrist);
	//药品类型管理表格展示
	ArrayList<DrugType> drugTypeInit(RowBounds rb,@Param("dtname")String dtname);
	//药品类型分页
	int countDrugType(@Param("dtname")String dtname);
	//新增药品类型
	Integer addType(@Param("tname")String tname,@Param("fname")String fname);
	//删除药品类型
	Integer delType(@Param("dtid")int dtid);
	//修改药品类型
	Integer alterType(@Param("newtype")String newtype,@Param("dtid")int dtid);
	//新增药品大类
	Integer addBig(@Param("bigtype")String bigtype); 
	//判断药品类名是否有重复
	ArrayList<String> judgeBigType(@Param("bigtype")String bigtype);
	//删除药品大类
	Integer delBig(@Param("bigtype")String bigtype);
	//删除药品大类中的所有小类
	Integer delSmall(@Param("bigtype")String bigtype);
	//修改药品类型
	Integer alterBig(@Param("newbigtype")String newbigtype,@Param("oldbigtype")String oldbigtype);
	
	
	
	
	
}
