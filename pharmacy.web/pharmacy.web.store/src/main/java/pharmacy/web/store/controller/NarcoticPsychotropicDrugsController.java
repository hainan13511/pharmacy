package pharmacy.web.store.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.web.store.model.dto.NpdsEnterWarehouse;
import pharmacy.web.store.service.NarcoticPsychotropicDrugsService;

@Controller
public class NarcoticPsychotropicDrugsController {

	@Autowired
	private NarcoticPsychotropicDrugsService narcoticPsychotropicDrugsService;
	
	//麻醉精神药品入库明细表
	@RequestMapping("/NpdsEnterWarehouseTable.action")
	@ResponseBody
	public ArrayList<NpdsEnterWarehouse> NpdsEnterWarehouseTable(String dname,String stime,String etime,int ks,int jt){
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = narcoticPsychotropicDrugsService.NpdsEnterWarehouseTable(stime, etime, dname, rn);
		return list;
	}
	//麻醉精神药品入库明细表统计
	@RequestMapping("/NpdsEnterWarehouseTableCount.action")
	@ResponseBody
	public int NpdsEnterWarehouseTableCount(String dname,String stime,String etime){
		int a = 0;
		a = narcoticPsychotropicDrugsService.NpdsEnterWarehouseTableCount(stime, etime, dname);
		return a;
	}
	//麻醉精神药品入库明细
	@RequestMapping("/NpdsSalesGradeTable.action")
	@ResponseBody
	public ArrayList<NpdsEnterWarehouse> NpdsSalesGradeTable(String dname,String stime,String etime,int ks,int jt){
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = narcoticPsychotropicDrugsService.NpdsSalesGradeTable(stime, etime, dname, rn);
		return list;
	}
	//麻醉精神药品入库明细
	@RequestMapping("/NpdsSalesGradeTableCount.action")
	@ResponseBody
	public int NpdsSalesGradeTableCount(String dname,String stime,String etime){
		int a = 0;
		a = narcoticPsychotropicDrugsService.NpdsSalesGradeTableCount(stime, etime, dname);
		return a;
	}
	//麻醉精神药品库存表
	@RequestMapping("/NpdsStorageTable.action")
	@ResponseBody
	public ArrayList<NpdsEnterWarehouse> NpdsStorageTable(String dname,String stime,String etime,int ks,int jt){
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		list = narcoticPsychotropicDrugsService.NpdsStorageTable(stime, etime, dname, rn);
		return list;
	}
	//麻醉精神药品库存表数量
	@RequestMapping("/NpdsStorageTableCount.action")
	@ResponseBody
	public int NpdsStorageTableCount(String dname,String stime,String etime){
		int a = 0;
		a = narcoticPsychotropicDrugsService.NpdsStorageTableCount(stime, etime, dname);
		return a;
	}
}
