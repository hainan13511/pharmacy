package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugPrice;

public class DrugPriceDto extends DrugPrice {

	private String drugName;
	private String uname;
	public DrugPriceDto(int priceId, int drugId, String beforePrice, String laterPrice, String purchasingTime,
			String uid, String drugName, String uname) {
		super(priceId, drugId, beforePrice, laterPrice, purchasingTime, uid);
		this.drugName = drugName;
		this.uname = uname;
	}
	public DrugPriceDto() {
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugPriceDto [drugName=");
		builder.append(drugName);
		builder.append(", uname=");
		builder.append(uname);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
