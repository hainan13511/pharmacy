package pharmacy.web.store.model.dto;

public class StatisticsDrugDto {

	private String drugName;
	private String inventoryCount;
	public StatisticsDrugDto(String drugName, String inventoryCount) {
		super();
		this.drugName = drugName;
		this.inventoryCount = inventoryCount;
	}
	public StatisticsDrugDto() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatisticsDrugDto [drugName=");
		builder.append(drugName);
		builder.append(", inventoryCount=");
		builder.append(inventoryCount);
		builder.append("]");
		return builder.toString();
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	
}
