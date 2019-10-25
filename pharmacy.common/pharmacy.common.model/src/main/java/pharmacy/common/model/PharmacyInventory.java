package pharmacy.common.model;

public class PharmacyInventory {

	//药库库存ID
	private int inventoryid;
	//药品ID
	private int drugid;
	//数量
	private int inventoryCount;
	//采购ID
	private int dwdid;
	
	public PharmacyInventory() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyInventory(int inventoryid, int drugid, int inventoryCount, int dwdid) {
		super();
		this.inventoryid = inventoryid;
		this.drugid = drugid;
		this.inventoryCount = inventoryCount;
		this.dwdid = dwdid;
	}

	public int getInventoryid() {
		return inventoryid;
	}

	public void setInventoryid(int inventoryid) {
		this.inventoryid = inventoryid;
	}

	public int getDrugid() {
		return drugid;
	}

	public void setDrugid(int drugid) {
		this.drugid = drugid;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public int getDwdid() {
		return dwdid;
	}

	public void setDwdid(int dwdid) {
		this.dwdid = dwdid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PharmacyInventory [inventoryid=");
		builder.append(inventoryid);
		builder.append(", drugid=");
		builder.append(drugid);
		builder.append(", inventoryCount=");
		builder.append(inventoryCount);
		builder.append(", dwdid=");
		builder.append(dwdid);
		builder.append("]");
		return builder.toString();
	}
	
	
}
