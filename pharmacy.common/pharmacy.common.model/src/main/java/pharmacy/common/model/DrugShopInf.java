package pharmacy.common.model;

import java.io.Serializable;

public class DrugShopInf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int inventoryId;
	private int drugId;
	private String provider;
	private String effectiveDate;
	private String purchasingTime;
	private String inventoryCount;
	private int uid;
	public DrugShopInf(int inventoryId, int drugId, String provider, String effectiveDate, String purchasingTime,
			String inventoryCount, int uid) {
		super();
		this.inventoryId = inventoryId;
		this.drugId = drugId;
		this.provider = provider;
		this.effectiveDate = effectiveDate;
		this.purchasingTime = purchasingTime;
		this.inventoryCount = inventoryCount;
		this.uid = uid;
	}
	public DrugShopInf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
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
	public String getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugShopInf [inventoryId=");
		builder.append(inventoryId);
		builder.append(", drugId=");
		builder.append(drugId);
		builder.append(", provider=");
		builder.append(provider);
		builder.append(", effectiveDate=");
		builder.append(effectiveDate);
		builder.append(", purchasingTime=");
		builder.append(purchasingTime);
		builder.append(", inventoryCount=");
		builder.append(inventoryCount);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}
	
	

}
