package pharmacy.web.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pharmacy.web.warehouse.dao.DrugSetMapper;
import pharmacy.web.warehouse.model.dto.DrugInfDto;
import pharmacy.web.warehouse.model.dto.DwdDto;
import pharmacy.web.warehouse.model.dto.MiniSetDto;
import pharmacy.web.warehouse.service.DrugSetService;
import pharmacy.web.warehouse.solr.DrugInf;
import pharmacy.web.warehouse.solr.DrugInfDtoSolr;
import pharmacy.web.warehouse.solr.SolrUtil;
@Service
public class DrugSetServiceImpl implements DrugSetService {

	@Autowired
	private DrugSetMapper dsp;
    // solr目录名
    private static String core = "new_core";
	//药品设置
	@Transactional(rollbackFor = Exception.class)
	public boolean drugSet(String drugname, String chemicalname, String commonly, String specification, String dosage,
			String formula, String method, String markup, String spell, String five, String invoice, int anti,
			String dose, String numday, int dtname,int dcost,int dsale,MiniSetDto miniset) {
		
		Integer b = dsp.miniSet(miniset);
		int drugid= miniset.getDrugId();
		DrugInf di = new DrugInf(drugid, drugname, chemicalname, commonly, specification, dosage, formula, method, markup, spell, five, invoice, anti, dose, numday, dtname, 1, dcost, dsale, "");
		try {
			SolrUtil.addDocumentByBean(di, core);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer a = dsp.drugSet(drugid,drugname, chemicalname, commonly, specification, 
		dosage, formula, method, markup, spell, five, invoice, anti, dose, numday, dtname,dcost,dsale);			
		if(b>0&&a>0) {
			return true;		
		}
		return false;
	}
	//药品设置展示初始化
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugInfDto> drugInfInit(RowBounds rb, String drugname) {	
		ArrayList<DrugInfDto>list=dsp.drugInfInit(rb, drugname);
		return list;
	}
	//药品设置展示数量页码
	@Transactional(rollbackFor = Exception.class)
	public int countDrug(String drugname) {	
		int a=dsp.countDrug(drugname);
		return a;
	}
	//药品采购插入
	@Transactional(rollbackFor = Exception.class)
	public boolean drugShop(int drugid, String supply, String drugtime, int amount,String uname,DwdDto dwddto,String unit) {
			
		Integer c=dsp.addDrugCount(dwddto);
		int dwdId=dwddto.getDwdId();
		Integer a =dsp.drugShop(dwdId,drugid, supply, drugtime, amount,uname,unit);
		Integer b =dsp.putDrugShop(drugid, uname,amount);
		if(a>0&&b>0&&c>0) {
			return true;
		}		
		return false;
	}
	//药品详情展示
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<DrugInfDto> drugShow(int drugid) {
		ArrayList<DrugInfDto>list=dsp.drugShow(drugid);
		return list;
	}
	//修改药品设置
	@Transactional(rollbackFor = Exception.class)
	public boolean alterDrug(String drugname, String chemicalname, String commonly, String specification, String dosage,
			String formula, String method, String markup, String spell, String five, String invoice, int anti,
			String dose, String numday, String dtname, int drugid,int dcost,int dsale) {
		Integer a =dsp.alterDrug(drugname, chemicalname, commonly, specification, dosage, formula, method, markup, spell, five, invoice, anti, dose, numday, dtname, drugid,dcost,dsale);
		if(a>0) {
			return true;
		}
		return false;
	}
	//删除药品
	@Transactional(rollbackFor = Exception.class)
	public boolean delDrug(int drugid) {	
		Integer a =dsp.delDrug(drugid);
		DrugInf di = new DrugInf();
		di.setDrugId(drugid);
		try {
			List<DrugInfDtoSolr> list = SolrUtil.getDocument(core, di, false, 0, 100);
			String id = list.get(0).getId();
			SolrUtil.deleteDocumentById(id, core);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(a>0) {
			return true;
		}
		return false;
	}
	//医保编码配置
	@Transactional(rollbackFor = Exception.class)
	public boolean insurSet(int drugid, String insurance) {
		Integer a=dsp.insurSet(drugid, insurance);
		if(a>0) {
			return true;
		}
		return false;
	}
	//药品单位下拉框
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> unitInf() {
		ArrayList<String>list=dsp.unitInf();
		return list;
	}
	//药品设置判断药品名是否已存在
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> judgeDrugname(String drugname) {
		ArrayList<String>list=dsp.judgeDrugname(drugname);
		return list;
	}
}
