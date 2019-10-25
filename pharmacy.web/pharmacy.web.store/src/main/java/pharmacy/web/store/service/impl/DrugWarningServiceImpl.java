package pharmacy.web.store.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.BeOverdueMapper;
import pharmacy.web.store.dao.DrugBreakageOperationMapper;
import pharmacy.web.store.dao.LowLimitMapper;
import pharmacy.web.store.dao.UnsalableMapper;
import pharmacy.web.store.model.dto.BeOverdue;
import pharmacy.web.store.model.dto.LowLimit;
import pharmacy.web.store.model.dto.Unsalable;
import pharmacy.web.store.service.DrugWarningService;
@Service
public class DrugWarningServiceImpl implements DrugWarningService{

	@Autowired
	private LowLimitMapper lowLimitMapper;
	@Autowired
	private BeOverdueMapper beOverdueMapper;
	@Autowired
	private UnsalableMapper unsalableMapper;
	@Autowired
	private DrugBreakageOperationMapper drugBreakageOperationMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<LowLimit> drugLowLimitTable(String dname,RowBounds rn) {
		ArrayList<LowLimit> list = new ArrayList<LowLimit>();
		int limit = 200;
		list = lowLimitMapper.lowLimitTable(dname, limit, rn);
		return list;
	}


	@Transactional(rollbackFor = Exception.class)
	public int drugLowLimitTableCount(String dname) {
		int zs = 0;
		int limit = 200;
		zs = lowLimitMapper.lowLimitTableCount(dname, limit);
		
		
		return zs;
	}


	@Transactional(rollbackFor = Exception.class)
	public ArrayList<BeOverdue> BeOverdueTable(String dname, RowBounds rn) {
		ArrayList<BeOverdue> list = new  ArrayList<BeOverdue>();
		list = beOverdueMapper.beOverdueTable(dname, rn);
		return list;
	}


	@Override
	public int BeOverdueTableCount(String dname) {
		int zs = 0;
		zs = beOverdueMapper.beOverdueTableCount(dname);
		return zs;
	}


	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Unsalable> drugUnsalableTable(String dname, RowBounds rn) {
		ArrayList<Unsalable> list = new ArrayList<Unsalable>();
		list = unsalableMapper.drugUnsalableTable(dname, rn);
		return list;
	}


	@Transactional(rollbackFor = Exception.class)
	public int drugUnsalableTableCount(String dname) {
		int zs = 0;
		zs = unsalableMapper.drugUnsalableTableCount(dname);
		return zs;
	}


	@Transactional(rollbackFor = Exception.class)
	public int drugBreakage(int uid, int number, String str, int batch, String drumname) {
		int drugId = beOverdueMapper.drugId(drumname);
		Integer drugBreakage = drugBreakageOperationMapper.drugBreakage(drugId, number, batch);
		if(drugBreakage==null||drugBreakage<=0) {
            return drugBreakage;
        }
		Integer log = drugBreakageOperationMapper.insertDrugBreakageLog(drugId, uid, number, str);// 报损记录
		Integer sales = drugBreakageOperationMapper.insertDrugSales(drugId, uid, number);//计入销售成本
		if(log>0&&sales>0) {
            return log;
        }else {
            throw new RuntimeException();
        }
	}


	@Override
	public ArrayList<LowLimit> drugLowLimit() {
		ArrayList<LowLimit> list = new ArrayList<LowLimit>();
		int limit = 200;
		list = lowLimitMapper.lowLimit(limit);
		return list;
	}


	@Override
	public ArrayList<BeOverdue> BeOverdue() {
		ArrayList<BeOverdue> list = new  ArrayList<BeOverdue>();
		list = beOverdueMapper.beOverdue();
		return list;
	}


	@Override
	public ArrayList<Unsalable> drugUnsalable() {
		ArrayList<Unsalable> list = new ArrayList<Unsalable>();
		list = unsalableMapper.drugUnsalable();
		return list;
	}
	
	

}
