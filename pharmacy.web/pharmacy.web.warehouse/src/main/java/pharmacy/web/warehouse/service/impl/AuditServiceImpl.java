package pharmacy.web.warehouse.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.PharmacyInventory;
import pharmacy.web.warehouse.dao.AuditMapper;
import pharmacy.web.warehouse.model.dto.AuditInfDto;
import pharmacy.web.warehouse.service.AuditService;
@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditMapper auditMapper;
	
	/**
	 * 	初始化数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<AuditInfDto> initAudit(String uname,String drugname,String state,RowBounds rb) {
		List<AuditInfDto> initAudit = auditMapper.initAudit( uname, drugname,state,rb);
		return initAudit;
	}

	/**
	 * 	初始化页码
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int initAuditPageNum(String uname,String drugname,String state) {
		int pageNum = auditMapper.initAuditPageNum( uname, drugname,state);
		return pageNum;
	}
	
	/**
	 * 	审核不通过
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean notApproved(String id) {
		int flag = auditMapper.audit(id, 2);
		if(flag>0) {
			return true;
		}
		return false;
	}
	/**
	 * 	审核通过
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String approve(String drugId,int requestCount,int uid ,String applyId) {
		int count = requestCount;
		Integer drugCount = auditMapper.getDrugCount(drugId);
		if( drugCount != null && drugCount >= requestCount) {
			//获取各批次的药品 按购买先后顺序排列
			List<PharmacyInventory> pharmacyInventory = auditMapper.getPharmacyInventory(drugId);
			//判断第一批药品是否足够发货
			if(pharmacyInventory.get(0).getInventoryCount() >=requestCount) {
				Integer houseid = auditMapper.selectHouseid(pharmacyInventory.get(0).getDwdid());
				if(houseid != null) {
					//药房端库存添加
					auditMapper.addStoreInventory(requestCount, houseid);
					//减少药库端库存
					auditMapper.reduceInventoryCount(requestCount, pharmacyInventory.get(0).getInventoryid());
					//添加出入库明细
					auditMapper.insertOutPutWarehouse(drugId, uid, "出库",count);
					//修改状态
					auditMapper.audit(applyId, 1);
					return "yes";
				}else {
					//药房端库存数据添加
					auditMapper.insertStore(drugId, requestCount, pharmacyInventory.get(0).getDwdid());
					//减少药库端库存
					auditMapper.reduceInventoryCount(requestCount, pharmacyInventory.get(0).getInventoryid());
					//添加出入库明细
					auditMapper.insertOutPutWarehouse(drugId, uid, "出库",count);
					//修改状态
					auditMapper.audit(applyId, 1);
					return "yes";
				}
			}else {
				for (int i = 0; i < pharmacyInventory.size(); i++) {
					PharmacyInventory pi = pharmacyInventory.get(i);
					Integer houseid = auditMapper.selectHouseid(pi.getDwdid());
					if(houseid!=null) {
						if(requestCount >= pi.getInventoryCount()) {
							//药房端库存数据添加
							auditMapper.addStoreInventory(pi.getInventoryCount(), houseid);
							//减少药库端库存
							auditMapper.reduceInventoryCount(pi.getInventoryCount(), pi.getInventoryid());
							requestCount = requestCount - pi.getInventoryCount();
						}else {
							//药房端库存数据添加
							auditMapper.insertStore(drugId, requestCount, pi.getDwdid());
							//减少药库端库存
							auditMapper.reduceInventoryCount(requestCount, pi.getInventoryid());
							break;
						}
					}else {
						if(requestCount >= pi.getInventoryCount()) {
							//药房端库存数据添加
							auditMapper.insertStore(drugId, pi.getInventoryCount(), pi.getDwdid());
							//减少药库端库存
							auditMapper.reduceInventoryCount(pi.getInventoryCount(), pi.getInventoryid());
							requestCount = requestCount - pi.getInventoryCount();
						}else {
							//药房端库存数据添加
							auditMapper.insertStore(drugId, requestCount, pi.getDwdid());
							//减少药库端库存
							auditMapper.reduceInventoryCount(requestCount, pi.getInventoryid());
							break;
						}
					}
				}
				//修改状态
				auditMapper.audit(applyId, 1);
				//添加出入库明细
				auditMapper.insertOutPutWarehouse(drugId, uid, "出库",count);
				return "yes";
			}
			
		}
		return "deficiency";
	}

}
