package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugInf;

public class DrugStopUseDto extends DrugInf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inventoryCount; 
	private Integer houseCount;
	private String  state;
	
	
	
	public DrugStopUseDto(Integer inventoryCount, Integer houseCount, String state) {
		super();
		this.inventoryCount = inventoryCount;
		this.houseCount = houseCount;
		this.state = state;
	}
	public DrugStopUseDto() {
		
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugStopUseDto [inventoryCount=");
		builder.append(inventoryCount);
		builder.append(", houseCount=");
		builder.append(houseCount);
		builder.append(", state=");
		builder.append(state);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	

}
