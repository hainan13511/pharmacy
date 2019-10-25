package pharmacy.web.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.NpdsEnterWarehouse;

public interface NarcoticPsychotropicDrugsMapper {

	ArrayList<NpdsEnterWarehouse> NpdsEnterWarehouseTable(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname,RowBounds rn);

	int NpdsEnterWarehouseTableCount(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname);
	
	ArrayList<NpdsEnterWarehouse> NpdsSalesGradeTable(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname,RowBounds rn);

	int NpdsSalesGradeTableCount(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname);
	
	ArrayList<NpdsEnterWarehouse> NpdsStorageTable(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname,RowBounds rn);

	int NpdsStorageTableCount(@Param("stime")String stime,@Param("etime")String etime,@Param("dname")String dname);
	
}
