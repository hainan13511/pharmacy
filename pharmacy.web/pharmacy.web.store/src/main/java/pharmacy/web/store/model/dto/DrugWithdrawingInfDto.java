package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugInf;

public class DrugWithdrawingInfDto extends DrugInf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//药房库存ID
	private int houseid;
	//批号ID
	private int dwdid;
	//药房库存
	private int houseCount;//需要映射
	//单位
	private String unit;
	//序号
	private int rn;
	public DrugWithdrawingInfDto() {
		// TODO Auto-generated constructor stub
	}
	public DrugWithdrawingInfDto(int houseid, int dwdid, int houseCount, String unit, int rn) {
		super();
		this.houseid = houseid;
		this.dwdid = dwdid;
		this.houseCount = houseCount;
		this.unit = unit;
		this.rn = rn;
	}
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}
	public int getDwdid() {
		return dwdid;
	}
	public void setDwdid(int dwdid) {
		this.dwdid = dwdid;
	}
	public int getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugWithdrawingInfDto [houseid=");
		builder.append(houseid);
		builder.append(", dwdid=");
		builder.append(dwdid);
		builder.append(", houseCount=");
		builder.append(houseCount);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", rn=");
		builder.append(rn);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
