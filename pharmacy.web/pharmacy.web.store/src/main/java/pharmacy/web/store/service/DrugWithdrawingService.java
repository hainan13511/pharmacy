package pharmacy.web.store.service;


import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.DrugWithdrawingMessage;

public interface DrugWithdrawingService {
	//初始化信息
	DrugWithdrawingMessage initDrugWithdrawing(String drugname ,String  batch, RowBounds rb);
	//修改药库
	String modifyInventory(String houseid,int count,String dwdid,String drugid);
}
