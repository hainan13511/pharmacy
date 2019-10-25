package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugShopInf;

public class DrugShopInfDto extends DrugShopInf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String drugName;
	private int dcost;
	private String uname;
	public DrugShopInfDto(int inventoryId, int drugId, String provider, String effectiveDate, String purchasingTime,
			String inventoryCount, int uid, String drugName, int dcost, String uname) {
		super(inventoryId, drugId, provider, effectiveDate, purchasingTime, inventoryCount, uid);
		this.drugName = drugName;
		this.dcost = dcost;
		this.uname = uname;
	}
	public DrugShopInfDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DrugShopInfDto(int inventoryId, int drugId, String provider, String effectiveDate, String purchasingTime,
			String inventoryCount, int uid) {
		super(inventoryId, drugId, provider, effectiveDate, purchasingTime, inventoryCount, uid);
		// TODO Auto-generated constructor stub
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getDcost() {
		return dcost;
	}
	public void setDcost(int dcost) {
		this.dcost = dcost;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
