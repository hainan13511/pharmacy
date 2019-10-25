package pharmacy.web.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.Unsalable;

public interface UnsalableMapper {

	ArrayList<Unsalable> drugUnsalableTable(@Param("dname")String dname,RowBounds rn);
	
	int drugUnsalableTableCount(@Param("dname")String dname);
	
	ArrayList<Unsalable> drugUnsalable();
}
