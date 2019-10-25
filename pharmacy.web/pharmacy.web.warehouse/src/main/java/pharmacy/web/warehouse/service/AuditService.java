package pharmacy.web.warehouse.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.AuditInfDto;

public interface AuditService {

	//初始化数据
	List<AuditInfDto> initAudit(String uname,String drugname,String state,RowBounds rb);
	//初始化页码
	int initAuditPageNum(String uname,String drugname,String state);
	//审核不通过
	boolean notApproved(String id);
	//审核通过
	String approve(String drugName,int requestCount,int uid,String applyId );
	
}
