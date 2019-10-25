package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugWithdrawingInfDto;

public interface DrugWithdrawingMapper {

	//查询数据初始页面表格
	List<DrugWithdrawingInfDto> initDrugWithdrawingInf(@Param("drugname") String drugname ,@Param("batch") String  batch,RowBounds rb);
	//获取页码
	int initDrugWithdrawingInfPageNum(@Param("drugname") String drugname ,@Param("batch") String  batch);
	//查询房库存
	int getHousecount(@Param("houseid") String houseid);
	//修改药房库存
	int updateHouseInventory(@Param("houseid") String houseid,@Param("count") int count);
	//判断药库是否存在该药品
	Integer getInventoryid(@Param("dwdid") String dwdid);
	//添加药库库存
	int updateInventorycount(@Param("inventoryid") int inventoryid,@Param("count") int count);
	//药库如果不存在该药品
	int addPharmacyInventory(@Param("drugid") String drugid,@Param("count") int count,@Param("dwdid") String dwdid);
}
