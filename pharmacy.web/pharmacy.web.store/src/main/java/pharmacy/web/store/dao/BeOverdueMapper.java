package pharmacy.web.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.BeOverdue;

public interface BeOverdueMapper {

	ArrayList<BeOverdue> beOverdueTable(@Param("dname")String dname,RowBounds rn);
	
	int beOverdueTableCount(@Param("dname")String dname);
	
	int drugId(@Param("dname")String dname);
	
	ArrayList<BeOverdue> beOverdue();
}
