package pharmacy.web.warehouse.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.warehouse.dao.DrugShopInfMapper;
import pharmacy.web.warehouse.model.dto.DrugShopInfDto;
import pharmacy.web.warehouse.model.dto.OutPutInfDto;
import pharmacy.web.warehouse.service.DrugShopInfService;
@Service
public class DrugShopInfServiceImpl implements DrugShopInfService {

	@Autowired
	private DrugShopInfMapper dsim;
	
	//初始化药品采购表
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugShopInfDto> drugShopInfInit(RowBounds rb, String drugname, String supply, String uname) {
		
		ArrayList<DrugShopInfDto>list=dsim.drugShopInfInit(rb, drugname, supply, uname);
		return list;
	}

	//药品采购分页
	@Transactional(rollbackFor = Exception.class)
	public Integer drugShopCount(String drugname, String supply, String uname) {
		Integer a = dsim.drugShopCount(drugname, supply, uname);
		return a;
	}

	//药品出入库明细表
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<OutPutInfDto> outPutInfInit(RowBounds rb, String drugname, String uname) {
		ArrayList<OutPutInfDto>list=dsim.outPutInfInit(rb, drugname, uname);
		return list;
	}
	//药品出入库明细表分页
	@Transactional(rollbackFor = Exception.class)
	public Integer outPutCount(String drugname, String uname) {
		Integer a = dsim.outPutCount(drugname, uname);
		return a;
	}

}
