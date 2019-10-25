package pharmacy.web.store.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.model.UserInfo;
import pharmacy.web.store.dao.BeOverdueMapper;
import pharmacy.web.store.model.dto.BeOverdue;
import pharmacy.web.store.model.dto.LowLimit;
import pharmacy.web.store.model.dto.Unsalable;
import pharmacy.web.store.service.DailyWorkService;
import pharmacy.web.store.service.DrugWarningService;
import pharmacy.web.store.service.DrugWithdrawingService;

@Controller
public class DrugWarningController {

	
	@Autowired
	private DailyWorkService dailyWorkService;
	@Autowired
	private DrugWarningService drugWarningService;
	@Autowired
	private DrugWithdrawingService drugWithdrawingService;
	@Autowired
	private BeOverdueMapper beOverdueMapper;
	
	//药品低限表
	@RequestMapping("/drugLowLimitTable.action")
	@ResponseBody
	public ArrayList<LowLimit> drugLowLimitTable(String dname,int ks,int jt){
		ArrayList<LowLimit> list = new ArrayList<LowLimit>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = drugWarningService.drugLowLimitTable(dname, rn);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).toString();
		}
		return list;
	}
	//药品低限表统计
	@RequestMapping("/drugLowLimitTableCount.action")
	@ResponseBody
	public int drugLowLimitTableCount(String dname){
		int zs = 0;
		zs = drugWarningService.drugLowLimitTableCount(dname);
		return zs;
	}
	//药品过期表
	@RequestMapping("/BeOverdueTable.action")
	@ResponseBody
	public ArrayList<BeOverdue> BeOverdueTable(String dname,int ks,int jt){
		ArrayList<BeOverdue> list = new ArrayList<BeOverdue>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = drugWarningService.BeOverdueTable(dname, rn);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).toString();
		}
		return list;
	}
	//药品过期表统计
	@RequestMapping("/BeOverdueTableCount.action")
	@ResponseBody
	public int BeOverdueTableCount(String dname){
		int zs = 0;
		zs = drugWarningService.BeOverdueTableCount(dname);
		System.out.println("zs="+zs);
		return zs;
	}
	//药品滞销表
	@RequestMapping("/drugUnsalableTable.action")
	@ResponseBody
	public ArrayList<Unsalable> drugUnsalableTable(String dname,int ks,int jt){
		ArrayList<Unsalable> list = new ArrayList<Unsalable>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = drugWarningService.drugUnsalableTable(dname, rn);
		return list;
	}
	//药品过期表统计
	@RequestMapping("/drugUnsalableTableCount.action")
	@ResponseBody
	public int drugUnsalableTableCount(String dname){
		int zs = 0;
		zs = drugWarningService.drugUnsalableTableCount(dname);
		System.out.println("zs="+zs);
		return zs;
	}
		
	//药品报损
	@RequestMapping("/drugBreakage1.action")
	@ResponseBody
	public int drugBreakage(HttpServletRequest request,int number, String str,int batch,String drumname){
		int zs = 0;
		HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        zs = drugWarningService.drugBreakage(u.getUid(), number, str, batch, drumname);
        System.out.println("zs="+zs);
        return zs;
	}
	//药品请领
	@RequestMapping("/drugapplty1.action")
	@ResponseBody
	public String drugapplty(HttpServletRequest request,int number,String drumname){
		String string ="no";
		int zs = 0;
		HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        zs = dailyWorkService.insertDrugApply(drumname, number, u.getUid());
        if(zs>0) {
			 string ="yes";
		}
        System.out.println("zs="+zs);
        return string;
	}
	//药品退库
	@RequestMapping("/modifyInventory1.action")
	@ResponseBody
	public String modifyInventory(String houseid, int number, String dwdid,String drumname){
		int drugId = beOverdueMapper.drugId(drumname);
		String modifyInventory = drugWithdrawingService.modifyInventory(houseid, number, dwdid, drugId+"");
        return modifyInventory;
	}
}