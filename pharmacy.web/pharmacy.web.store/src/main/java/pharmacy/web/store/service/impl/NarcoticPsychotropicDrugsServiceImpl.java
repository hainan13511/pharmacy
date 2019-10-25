package pharmacy.web.store.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.web.store.dao.NarcoticPsychotropicDrugsMapper;
import pharmacy.web.store.model.dto.NpdsEnterWarehouse;
import pharmacy.web.store.service.NarcoticPsychotropicDrugsService;
@Service
public class NarcoticPsychotropicDrugsServiceImpl implements NarcoticPsychotropicDrugsService{

	@Autowired
	private NarcoticPsychotropicDrugsMapper narcoticPsychotropicDrugsMapper;
	
	@Override
	public ArrayList<NpdsEnterWarehouse> NpdsEnterWarehouseTable(String stime, String etime, String dname,
			RowBounds rn) {
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		list = narcoticPsychotropicDrugsMapper.NpdsEnterWarehouseTable(stime, etime, dname, rn);
		return list;
	}

	@Override
	public int NpdsEnterWarehouseTableCount(String stime, String etime, String dname) {
		int a = 0 ;
		a = narcoticPsychotropicDrugsMapper.NpdsEnterWarehouseTableCount(stime, etime, dname);
		return a;
	}

	@Override
	public ArrayList<NpdsEnterWarehouse> NpdsSalesGradeTable(String stime, String etime, String dname, RowBounds rn) {
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		list = narcoticPsychotropicDrugsMapper.NpdsSalesGradeTable(stime, etime, dname, rn);
		return list;
	}

	@Override
	public int NpdsSalesGradeTableCount(String stime, String etime, String dname) {
		int a = 0 ;
		a = narcoticPsychotropicDrugsMapper.NpdsSalesGradeTableCount(stime, etime, dname);
		return a;
	}

	@Override
	public ArrayList<NpdsEnterWarehouse> NpdsStorageTable(String stime, String etime, String dname, RowBounds rn) {
		ArrayList<NpdsEnterWarehouse> list = new ArrayList<NpdsEnterWarehouse>();
		System.out.println("查询麻醉精神药品库存");
		list = narcoticPsychotropicDrugsMapper.NpdsStorageTable(stime, etime, dname, rn);
		return list;
	}

	@Override
	public int NpdsStorageTableCount(String stime, String etime, String dname) {
		int a = 0 ;
		a = narcoticPsychotropicDrugsMapper.NpdsStorageTableCount(stime, etime, dname);
		return a;
	}

	
}
