package pharmacy.web.store.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.NpdsEnterWarehouse;

public interface NarcoticPsychotropicDrugsService {

	ArrayList<NpdsEnterWarehouse> NpdsEnterWarehouseTable(String stime,String etime,String dname,RowBounds rn);
	
	int NpdsEnterWarehouseTableCount(String stime,String etime,String dname);
	
	ArrayList<NpdsEnterWarehouse> NpdsSalesGradeTable(String stime,String etime,String dname,RowBounds rn);
	
	int NpdsSalesGradeTableCount(String stime,String etime,String dname);
	
	ArrayList<NpdsEnterWarehouse> NpdsStorageTable(String stime,String etime,String dname,RowBounds rn);
	
	int NpdsStorageTableCount(String stime,String etime,String dname);
}
