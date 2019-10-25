package pharmacy.web.warehouse.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.warehouse.dao.DrugTypeMapper;
import pharmacy.web.warehouse.model.dto.DrugType;
import pharmacy.web.warehouse.service.DrugTypeService;

@Service
public class DrugTypeServiceImpl implements DrugTypeService {

	@Autowired
	private DrugTypeMapper dtm;
	
	//下拉框药品大类
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> drugTypeFrist() {
		ArrayList<String>list=dtm.drugTypeFrist();
		return list;
	}

	//下拉框药品小类
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugType> drugTypeSecond(String drugfrist) {
		ArrayList<DrugType>list=dtm.drugTypeSecond(drugfrist);
		return list;
	}
	//药品类型管理表格展示
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugType> drugTypeInit(RowBounds rb, String dtname) {
		ArrayList<DrugType>list =dtm.drugTypeInit(rb, dtname);		
		return list;
	}
	//药品类型管理表格分页
	@Transactional(rollbackFor = Exception.class)
	public int countDrugType(String dtname) {
		int a = dtm.countDrugType(dtname);
		return a;
	}
	//新增药品类型
	@Transactional(rollbackFor = Exception.class)
	public boolean addType(String tname, String fname) {
		Integer a =dtm.addType(tname, fname);
		if(a>0) {
			return true;
		}	
		return false;
	}
	//删除药品类型
	@Transactional(rollbackFor = Exception.class)
	public boolean delType(int dtid) {
		Integer a = dtm.delType(dtid);
		if(a>0) {
			return true;
		}	
		return false;
	}

	//修改药物类型
	@Transactional(rollbackFor = Exception.class)
	public boolean alterType(String newtype, int dtid) {	
		Integer a =dtm.alterType(newtype, dtid);
		if(a>0) {
			return true;
		}
		return false;
	}
	//新增药品大类
	@Transactional(rollbackFor = Exception.class)
	public boolean addBig(String bigtype) {
		Integer a = dtm.addBig(bigtype);
		if(a>0) {
			return true;
		}		
		return false;
	}
	//判断药品大类名是否有重复
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> judgeBigType(String bigtype) {
		ArrayList<String>list=dtm.judgeBigType(bigtype);
		return list;
	}
	//删除药品大类和药品所属的小类
	@Transactional(rollbackFor = Exception.class)
	public boolean delBig(String bigtype) {
		Integer b =dtm.delSmall(bigtype);
		Integer a = dtm.delBig(bigtype);
			if(b>0&&a>0) {
				return true;
			}	
		
		return false;
	}
	//修改药品大类
	@Transactional(rollbackFor = Exception.class)
	public boolean alterBig(String newbigtype, String oldbigtype) {
		Integer a=dtm.alterBig(newbigtype, oldbigtype);
		if(a>0) {
			return true;
		}
		return false;
	}


}
