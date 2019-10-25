package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugInf;

public class ReturnManufacturerInfDto extends DrugInf {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//药房库存
	private int inventoryCount;//需要映射
	//采购日期
	private String purchasingTime;//需要映射
	//库存ID
	private int inventoryid; 
	//厂商
	private String provider;
	//单位
	private String unit;
	//用户名
	private String uname;
	//序号
	private int rn;
	
	
	public ReturnManufacturerInfDto() {
		// TODO Auto-generated constructor stub
	}


	public ReturnManufacturerInfDto(int inventoryCount, String purchasingTime, int inventoryid, String provider,
			String unit, String uname, int rn) {
		super();
		this.inventoryCount = inventoryCount;
		this.purchasingTime = purchasingTime;
		this.inventoryid = inventoryid;
		this.provider = provider;
		this.unit = unit;
		this.uname = uname;
		this.rn = rn;
	}


	public int getInventoryCount() {
		return inventoryCount;
	}


	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}


	public String getPurchasingTime() {
		return purchasingTime;
	}


	public void setPurchasingTime(String purchasingTime) {
		this.purchasingTime = purchasingTime;
	}


	public int getInventoryid() {
		return inventoryid;
	}


	public void setInventoryid(int inventoryid) {
		this.inventoryid = inventoryid;
	}


	public String getProvider() {
		return provider;
	}


	public void setProvider(String provider) {
		this.provider = provider;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
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
		builder.append("ReturnManufacturerInf [inventoryCount=");
		builder.append(inventoryCount);
		builder.append(", purchasingTime=");
		builder.append(purchasingTime);
		builder.append(", inventoryid=");
		builder.append(inventoryid);
		builder.append(", provider=");
		builder.append(provider);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", uname=");
		builder.append(uname);
		builder.append(", rn=");
		builder.append(rn);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	

	
}
