package pharmacy.web.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.common.model.PharmacyInventory;
import pharmacy.web.warehouse.model.dto.AuditInfDto;

public interface AuditMapper {

	//初始化数据
	List<AuditInfDto> initAudit(@Param("uname") String uname,@Param("drugname") String drugname,@Param("state") String state,RowBounds rb);
	
	//获取页码
	int initAuditPageNum(@Param("uname") String uname,@Param("drugname") String drugname,@Param("state") String state);
	
	//审核
	int audit (@Param("id") String aid,@Param("state") int state);
	
	//获取当前药品库存
	Integer getDrugCount(@Param("drugId") String drugId);
	
	//查询药库是否存在该药品
	Integer selectHouseid(@Param("dwdid") int dwdid);
	
	//获取各批次的药品 按购买先后顺序排列
	List<PharmacyInventory> getPharmacyInventory(@Param("drugId") String drugId);
	
	//添加药房端库存
	int addStoreInventory(@Param("count") int count,@Param("houseid") int houseid);
	
	//减少药库库存
	int reduceInventoryCount(@Param("count") int count ,@Param("inventoryId")  int inventoryId);
	
	//为药房添加数据
	int insertStore(@Param("drugId") String drugId,@Param("count") int count,@Param("dwdid") int dwdid);
	
	
	
	
	//添加出入库明细表
	int insertOutPutWarehouse(@Param("drugId") String drugId,@Param("uid") int uid,@Param("operation") String operation,@Param("count") int count);

}

