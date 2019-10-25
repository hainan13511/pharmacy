package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.DrugWithdrawingMapper;
import pharmacy.web.store.model.DrugWithdrawingMessage;
import pharmacy.web.store.model.dto.DrugWithdrawingInfDto;
import pharmacy.web.store.service.DrugWithdrawingService;
@Service
public class DrugWithdrawingServiceImpl implements DrugWithdrawingService {
	
	@Autowired
	private DrugWithdrawingMapper dwm;
	
	/**
	 * 	初始化数据 页码
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DrugWithdrawingMessage initDrugWithdrawing(String drugname, String batch, RowBounds rb) {
		List<DrugWithdrawingInfDto> initDrugWithdrawingInf = dwm.initDrugWithdrawingInf(drugname, batch, rb);
		int initDrugWithdrawingInfPageNum = dwm.initDrugWithdrawingInfPageNum(drugname, batch);
		DrugWithdrawingMessage drugWithdrawingMessage = new DrugWithdrawingMessage(initDrugWithdrawingInf, initDrugWithdrawingInfPageNum);
		return drugWithdrawingMessage;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String modifyInventory(String houseid, int count, String dwdid, String drugid) {
		int housecount = dwm.getHousecount(houseid);
		//判断是否退库数量大于库存数量
		if(count>housecount) {
			return "insufficient";
		}
		//减少药房库存
		dwm.updateHouseInventory(houseid, count);
		//添加药库库存 先判断药库是否存在
		Integer inventoryid = dwm.getInventoryid(dwdid);
		if(inventoryid != null) {
			int updateInventorycount = dwm.updateInventorycount(inventoryid, count);
			if(updateInventorycount <= 0) {
				return "no";
			}
		}else {
			int addPharmacyInventory = dwm.addPharmacyInventory(drugid, count, dwdid);
			if(addPharmacyInventory <= 0) {
				return "no";
			}
		}
		return "yes";
	}
	
	
	
	

}
