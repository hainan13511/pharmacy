package pharmacy.web.warehouse.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.DrugInf;
import pharmacy.common.utils.PinYinUtil;
import pharmacy.common.utils.StringUtil;
import pharmacy.common.utils.WuBiUtil;
import pharmacy.web.warehouse.model.dto.DrugInfDto;
import pharmacy.web.warehouse.model.dto.DwdDto;
import pharmacy.web.warehouse.model.dto.MiniSetDto;
import pharmacy.web.warehouse.service.DrugSetService;

@Controller
public class DrugSetController {

	@Autowired
	private DrugSetService dss;	
	//药品设置
	@RequestMapping("/dodrugset.action")
	@ResponseBody
	public String drugSet(String drugname,String chemicalname,String commonly,String specification,String dosage,String formula
			,String method,String markup,String spell,String five,String invoice,String anti
			,String dose,String numday,String dtname,String dcost,String dsale) {
		
		String data ="no";	
		
		if(!StringUtil.isAllNotEmpty(drugname,chemicalname,commonly,specification, dosage, formula, method, markup, spell, five, invoice, anti, dose, numday, dtname,dcost,dsale)) {
			data="empty";
			return data;
		}else {
			ArrayList<String>list=dss.judgeDrugname(drugname);
				if(list.isEmpty()) {
				MiniSetDto miniset = new MiniSetDto(0, 100);
				boolean a = dss.drugSet(drugname, chemicalname, commonly, specification, dosage, formula, method, markup, spell, five, invoice, Integer.parseInt(anti), dose, numday, Integer.parseInt(dtname),Integer.parseInt(dcost),Integer.parseInt(dsale),miniset);
				if(a) {
					data="yes";
					return data;
				}				
			}else {
				data="rep";
				return data;			
			}
		}
		return data;	
	}
	//根据药品名自动生成拼音码和五笔码
	@RequestMapping("/dospellauto.action")
	@ResponseBody
	public JSONObject spellAuto(String drugname) {
		JSONObject data = new JSONObject();
		String spell=PinYinUtil.hanziToPinyin(drugname, "");
		String wubi=WuBiUtil.getWBCode(drugname);
		data.put("spell", spell);
		data.put("wubi", wubi);
		return data;
	}
	
	//药品设置界面展示
	@RequestMapping("/dodruginfinit.action")
	@ResponseBody
	public JSONObject dailyInit(String start,String end,String drugname) {
		JSONObject data = new JSONObject();
		RowBounds rb=new RowBounds(Integer.parseInt(start),Integer.parseInt(end));
		ArrayList<DrugInfDto>list=dss.drugInfInit(rb, drugname);
		int a = dss.countDrug(drugname);
		data.put("list", list);
		data.put("count", a);
		return data;
	}
	//药品采购插入
	@RequestMapping("/dodrugshop.action")
	@ResponseBody
	public String drugShop(String drugid,String supply,String drugtime,String amount,String uname,String unit) {
		
		String data ="no";
		if(!StringUtil.isAllNotEmpty(drugid,supply,drugtime,amount,uname)) {
			data="empty";
			return data;
		}else {
			DwdDto dwddto=new DwdDto(Integer.parseInt(drugid), Integer.parseInt(amount));
			boolean a=dss.drugShop(Integer.parseInt(drugid), supply, drugtime, Integer.parseInt(amount),uname,dwddto,unit);
			if(a) {
				data="yes";
				return data;
			}
		}	
		return data;	
	}
	//药品设置界面展示
	@RequestMapping("/dodrugshow.action")
	@ResponseBody
	public JSONObject dailyInit(String drugid) {
		JSONObject data = new JSONObject();
		ArrayList<DrugInfDto>list=dss.drugShow(Integer.parseInt(drugid));
		data.put("list", list);
		return data;
	}
	//药品设置修改
	@RequestMapping("/doalterdrug.action")
	@ResponseBody
	public String alterDrug(String drugname,String chemicalname,String commonly,String specification,String dosage,String formula
			,String method,String markup,String spell,String five,String invoice,String anti
			,String dose,String numday,String dtname,String drugid,String dcost,String dsale) {
		
		String data ="no";	
		if(!StringUtil.isAllNotEmpty(drugname,chemicalname,commonly,specification, dosage, formula, method, markup, spell, five, invoice, anti, dose, numday, dtname,dcost,dsale)) {
			data="empty";
			return data;
		}else {
			boolean a = dss.alterDrug(drugname, chemicalname, commonly, specification, dosage, formula, method, markup, spell, five, invoice, Integer.parseInt(anti), dose, numday, dtname, Integer.parseInt(drugid),Integer.parseInt(dcost),Integer.parseInt(dsale));
			if(a) {
				data="yes";
				return data;
			}
		}
		return data;	
	}
	//删除药品
	@RequestMapping("/dodeldrug.action")
	@ResponseBody
	public String delDrug(String drugid) {
		String data ="no";	
		boolean a =dss.delDrug(Integer.parseInt(drugid));
		if(a) {
			data="yes";
			return data;
		}	
		return data;
	}
	//医保编码配置
	@RequestMapping("/doinsuranceset.action")
	@ResponseBody
	public String insurSet(String drugid,String insurance) {
		String data ="no";	
		boolean a =dss.insurSet(Integer.parseInt(drugid), insurance);			
		if(a) {
			data="yes";
			return data;
		}			
		return data;
	}
	//药品单位下拉框
	@RequestMapping("/dounitinf.action")
	@ResponseBody
	public JSONObject unitInit() {
		JSONObject data = new JSONObject();
		ArrayList<String>list=dss.unitInf();
		data.put("list", list);
		return data;
	}
}
