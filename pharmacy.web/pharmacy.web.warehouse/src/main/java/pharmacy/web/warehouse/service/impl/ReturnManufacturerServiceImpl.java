package pharmacy.web.warehouse.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.warehouse.dao.ReturnManufacturerMapper;
import pharmacy.web.warehouse.model.dto.ReturnManufacturerInfDto;
import pharmacy.web.warehouse.service.ReturnManufacturerService;
@Service
public class ReturnManufacturerServiceImpl implements ReturnManufacturerService {

	@Autowired
	private ReturnManufacturerMapper rmm;
	
	//查询数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<ReturnManufacturerInfDto> initReturnManufacturer(String drugname, String supply, String uname,
			RowBounds rb) {
		List<ReturnManufacturerInfDto> initReturnManufacturer = rmm.initReturnManufacturer(drugname, supply, uname, rb);
		return initReturnManufacturer;
	}
	
	//查询页码
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int initReturnManufacturerPageNum(String drugname, String supply, String uname) {
		int initReturnManufacturerPageNum = rmm.initReturnManufacturerPageNum(drugname, supply, uname);
		return initReturnManufacturerPageNum;
	}
	
	//减少库存
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String reduceInventories(String inventoryid, String drug_count){
		int inventoryCount = rmm.getInventoryCount(inventoryid);
		if(inventoryCount < Integer.parseInt(drug_count)) {
			return "insufficient";
		}
		int reduceInventories = rmm.reduceInventories(inventoryid, drug_count);
		if(reduceInventories > 0) {
			return "yes";
		}
		return "no";
	}
	
}
