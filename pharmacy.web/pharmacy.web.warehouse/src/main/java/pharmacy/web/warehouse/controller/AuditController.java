package pharmacy.web.warehouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.UserInfo;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.warehouse.model.dto.AuditInfDto;
import pharmacy.web.warehouse.service.AuditService;
@Controller
public class AuditController {
	
	
	@Autowired
	private AuditService auditService;

	
	/**
	 * 	初始化
	 * @param uname 用户名
	 * @param drugname 药品名
	 * @return 返回JOSN 
	 */
	@RequestMapping("/initAudit.action")
	@ResponseBody
	public JSONObject initAudit( String uname,String drugname,String start,String end,String state) {
		JSONObject data  = new JSONObject();
		RowBounds rb = new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<AuditInfDto> initAudit = auditService.initAudit(uname,drugname,state,rb);
		int initAuditPageNum = auditService.initAuditPageNum(uname,drugname,state);
		for (AuditInfDto auditInfDto : initAudit) {
			System.out.println(auditInfDto.toString());
		}
		int page;
		if(initAuditPageNum%5 == 0) {
			page = initAuditPageNum/5;
		}else {
			page = initAuditPageNum/5+1;
		}
		if(page == 0) {
			page = 1;
		}
		data.put("list", initAudit);
		data.put("pageNum", page);
		return data;
	}
	
	/**
	 * 	审核不通过
	 * @param applyid 申请ID
	 * @return 然会JSON
	 */
	@RequestMapping("/notApproved.action")
	@ResponseBody
	public JSONObject notApproved(String applyid) {
		JSONObject data  = new JSONObject();
		if(StringUtil.isAllNotEmpty(applyid)){
			boolean notApproved = auditService.notApproved(applyid);
			if(notApproved) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 * 	审核通过
	 * @param drugid 药品ID
	 * @param applyid 请求ID
	 * @param count 请求数量
	 * @param request 用于获取当前登陆ID
	 * @return 返回JSON
	 */
	@RequestMapping("/approve.action")
	@ResponseBody
	public JSONObject approve(String drugid,String applyid,String count,HttpServletRequest request) {
		JSONObject data  = new JSONObject();
		System.out.println("---------------------------------");
		System.out.println(drugid);
		System.out.println(applyid);
		System.out.println(count);
		System.out.println("---------------------------------");
		if(StringUtil.isAllNotEmpty(drugid,applyid,count)){
			UserInfo user = (UserInfo)request.getSession().getAttribute("login");
			int requestCount = Integer.parseInt(count);
			String approve = auditService.approve(drugid, requestCount, user.getUid(), applyid);
			if(approve.equals("yes")) {
				data.put("result", "yes");
			}else {
				data.put("result", "deficiency");
			}

		}
		return data;
	}
}
