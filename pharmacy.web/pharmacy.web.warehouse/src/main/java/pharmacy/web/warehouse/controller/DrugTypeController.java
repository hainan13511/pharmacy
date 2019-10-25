package pharmacy.web.warehouse.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.warehouse.model.dto.DrugInfDto;
import pharmacy.web.warehouse.model.dto.DrugType;
import pharmacy.web.warehouse.service.DrugTypeService;

@Controller
public class DrugTypeController {
	
	@Autowired
	private DrugTypeService dts;
	
	//下拉框药品大类
	@RequestMapping("/dodrugtypefrist.action")
	@ResponseBody
	public JSONObject drugTypeFrist() {
		JSONObject data = new JSONObject();
		ArrayList<String>list=dts.drugTypeFrist();
		data.put("list", list);		
		return data;
	}
	
	//下拉框药品大类
	@RequestMapping("/dodrugtypesecond.action")
	@ResponseBody
	public JSONObject drugTypeSecond(String drugfrist) {
		JSONObject data = new JSONObject();
		ArrayList<DrugType>list=dts.drugTypeSecond(drugfrist);
		data.put("list", list);		
		return data;
	}
	//药品类型表格展示
	@RequestMapping("/dodrugtypeinit.action")
	@ResponseBody
	public JSONObject drugTypeInit(String start,String end,String dtname) {
		JSONObject data = new JSONObject();
		RowBounds rb=new RowBounds(Integer.parseInt(start),Integer.parseInt(end));
		ArrayList<DrugType>list=dts.drugTypeInit(rb, dtname);
		int a = dts.countDrugType(dtname);
		data.put("list", list);
		data.put("count", a);
		return data;
	}
	//药品类型新增
	@RequestMapping("/doaddtype.action")
	@ResponseBody
	public String addType(String tname,String fname) {
		String data ="no";	
		if(!StringUtil.isAllNotEmpty(tname,fname)) {
			data="empty";
			return data;
		}else {
			ArrayList<String>list=dts.judgeBigType(tname);
			if(list.isEmpty()) {
				boolean a=dts.addType(tname, fname);
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
	
	//药品类型删除
	@RequestMapping("/dodeltype.action")
	@ResponseBody
	public String delType(String dtid) {
		String data ="no";
		boolean a = dts.delType(Integer.parseInt(dtid));
		if(a) {
			data="yes";
			return data;
		}
		return data;
	}
	//药品类型修改
	@RequestMapping("/doaltertype.action")
	@ResponseBody
	public String alterType(String newtype,String dtid) {
		String data ="no";		
		if(!StringUtil.isAllNotEmpty(newtype)) {
			data="empty";
			return data;
		}else {
			ArrayList<String>list=dts.judgeBigType(newtype);
			if(list.isEmpty()) {
				boolean a = dts.alterType(newtype, Integer.parseInt(dtid));
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
	//新增药品大类
	@RequestMapping("/doaddbig.action")
	@ResponseBody
	public String addBig(String newbig) {
		String data ="no";
		if(!StringUtil.isAllNotEmpty(newbig)) {
			data="empty";
			return data;
		}else {
			ArrayList<String>list=dts.judgeBigType(newbig);
			if(list.isEmpty()) {
				boolean a = dts.addBig(newbig);
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
	//删除药品大类和所属的小类
	@RequestMapping("/dodelbig.action")
	@ResponseBody
	public String delBig(String bigname) {
		String data ="no";
		boolean a = dts.delBig(bigname);
		if(a) {
			data="yes";
			return data;
		}
		return data;	
	}
	//修改药品大类
	@RequestMapping("/doalterbig.action")
	@ResponseBody
	public String alterBig(String newbigtype,String oldbigtype) {
		String data ="no";
		if(!StringUtil.isAllNotEmpty(newbigtype)) {
			data="empty";
			return data;
		}else {
			ArrayList<String>list=dts.judgeBigType(newbigtype);
			if(list.isEmpty()) {
				boolean a = dts.alterBig(newbigtype, oldbigtype);
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

}
