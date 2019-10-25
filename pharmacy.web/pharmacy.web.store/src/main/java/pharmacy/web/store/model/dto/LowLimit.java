package pharmacy.web.store.model.dto;

public class LowLimit {

	private String did;
	private String dname;
	private String inventoryCount;
	public LowLimit(String did, String dname, String inventoryCount) {
		super();
		this.did = did;
		this.dname = dname;
		this.inventoryCount = inventoryCount;
	}
	public LowLimit() {
		super();
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LowLimit [did=");
		builder.append(did);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", inventoryCount=");
		builder.append(inventoryCount);
		builder.append("]");
		return builder.toString();
	}
	
}
