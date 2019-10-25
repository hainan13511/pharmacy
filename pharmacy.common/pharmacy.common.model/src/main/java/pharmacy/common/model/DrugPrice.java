package pharmacy.common.model;

public class DrugPrice {

	private int  priceId;
	private int drugId;
	private String beforePrice;
	private String laterPrice;
	private String purchasingTime;
	private String uid;
	public DrugPrice(int priceId, int drugId, String beforePrice, String laterPrice, String purchasingTime,
			String uid) {
		super();
		this.priceId = priceId;
		this.drugId = drugId;
		this.beforePrice = beforePrice;
		this.laterPrice = laterPrice;
		this.purchasingTime = purchasingTime;
		this.uid = uid;
	}
	public DrugPrice() {
		super();
	}
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getBeforePrice() {
		return beforePrice;
	}
	public void setBeforePrice(String beforePrice) {
		this.beforePrice = beforePrice;
	}
	public String getLaterPrice() {
		return laterPrice;
	}
	public void setLaterPrice(String laterPrice) {
		this.laterPrice = laterPrice;
	}
	public String getPurchasingTime() {
		return purchasingTime;
	}
	public void setPurchasingTime(String purchasingTime) {
		this.purchasingTime = purchasingTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugPrice [priceId=");
		builder.append(priceId);
		builder.append(", drugId=");
		builder.append(drugId);
		builder.append(", beforePrice=");
		builder.append(beforePrice);
		builder.append(", laterPrice=");
		builder.append(laterPrice);
		builder.append(", purchasingTime=");
		builder.append(purchasingTime);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}
	
}
