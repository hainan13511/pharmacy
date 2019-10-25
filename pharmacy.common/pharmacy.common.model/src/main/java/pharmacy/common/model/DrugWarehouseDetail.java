package pharmacy.common.model;

public class DrugWarehouseDetail {
	
	private int dwdId;
	private int drugId;
	private String provider;
	private String effectiveDate;
	private String purchasingTime;
	private int inventoryCount;
	private int uid;
	private String unit;
	public DrugWarehouseDetail(int dwdId, int drugId, String provider, String effectiveDate, String purchasingTime,
			int inventoryCount, int uid, String unit) {
		super();
		this.dwdId = dwdId;
		this.drugId = drugId;
		this.provider = provider;
		this.effectiveDate = effectiveDate;
		this.purchasingTime = purchasingTime;
		this.inventoryCount = inventoryCount;
		this.uid = uid;
		this.unit = unit;
	}
	public DrugWarehouseDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDwdId() {
		return dwdId;
	}
	public void setDwdId(int dwdId) {
		this.dwdId = dwdId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getPurchasingTime() {
		return purchasingTime;
	}
	public void setPurchasingTime(String purchasingTime) {
		this.purchasingTime = purchasingTime;
	}
	public int getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	

}
