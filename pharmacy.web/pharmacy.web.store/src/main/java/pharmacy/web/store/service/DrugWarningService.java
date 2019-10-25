package pharmacy.web.store.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.BeOverdue;
import pharmacy.web.store.model.dto.LowLimit;
import pharmacy.web.store.model.dto.Unsalable;

public interface DrugWarningService {

	ArrayList<LowLimit> drugLowLimitTable(String dname,RowBounds rn);
	
	int drugLowLimitTableCount(String dname);
	
	ArrayList<BeOverdue> BeOverdueTable(String dname,RowBounds rn);
	
	int BeOverdueTableCount(String dname);
	
	ArrayList<Unsalable> drugUnsalableTable(String dname,RowBounds rn);
	
	int drugUnsalableTableCount(String dname);
	
	int drugBreakage(int uid,int number,String str,int batch,String drumname);
	
	ArrayList<LowLimit> drugLowLimit();
	
	ArrayList<BeOverdue> BeOverdue();
	
	ArrayList<Unsalable> drugUnsalable();
	
}
