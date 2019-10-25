package pharmacy.web.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.DrugInf;
import pharmacy.web.warehouse.dao.IncompatibleMapper;
import pharmacy.web.warehouse.service.IncompatibleService;
@Service
public class IncompatibleServiceImpl implements IncompatibleService{
	
	@Autowired
	private IncompatibleMapper im;

	//展示禁忌药品
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> incomDrug(int drugid) {
		ArrayList<String>list=im.incomDrug(drugid);
		return list;
	}

	//药品禁忌配置
	@Transactional(rollbackFor = Exception.class)
	public boolean incomSet(List<String>dname1,List<String> dname2) {
		Integer a=0;
		Integer b=0;
		for(int i=0;i<dname1.size();i++) {
			a =im.incomSet(dname1.get(i),dname2.get(i));
			b =im.incomSet(dname2.get(i),dname1.get(i));
		}
		if(a>0&&b>0) {
			return true;
		}
		return false;
	}
	//未配置禁忌药物展示
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugInf> outIncomDrug(String dname) {
		ArrayList<DrugInf>list=im.outIncomDrug(dname);
		return list;
	}
	
	//删除禁忌药物
	@Transactional(rollbackFor = Exception.class)
	public boolean delIncomp(String delname1, String delname2) {	
		Integer a =im.delIncomp(delname1, delname2);
		Integer b =im.delIncomp(delname2, delname1);
		if(a>0&&b>0) {
			return true;
		}
		return false;
	}



}
