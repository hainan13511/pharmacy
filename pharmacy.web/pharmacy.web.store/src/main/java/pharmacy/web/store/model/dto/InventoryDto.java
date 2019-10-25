package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugInf;

public class InventoryDto extends DrugInf{
	private static final long serialVersionUID = 1L;
	private int dwdid; 
	private String unit;
	private String purchasingTime;
	private Integer inventoryCount; 
	private Integer houseCount;
	
	

	public InventoryDto(int dwdid, String unit, String purchasingTime, Integer inventoryCount, Integer houseCount) {
		super();
		this.dwdid = dwdid;
		this.unit = unit;
		this.purchasingTime = purchasingTime;
		this.inventoryCount = inventoryCount;
		this.houseCount = houseCount;
	}

	public InventoryDto() {
		super();
	}

	public int getDwdid() {
		return dwdid;
	}

	public void setDwdid(int dwdid) {
		this.dwdid = dwdid;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPurchasingTime() {
		return purchasingTime;
	}

	public void setPurchasingTime(String purchasingTime) {
		this.purchasingTime = purchasingTime;
	}

	public Integer getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public Integer getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventoryDto [dwdid=");
		builder.append(dwdid);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", purchasingTime=");
		builder.append(purchasingTime);
		builder.append(", inventoryCount=");
		builder.append(inventoryCount);
		builder.append(", houseCount=");
		builder.append(houseCount);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	

}
