package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugWarehouseDetail;

public class DwdDto extends DrugWarehouseDetail{
	
	private int drugid;
	private int amount;
	public DwdDto(int drugid, int amount) {
		super();
		this.drugid = drugid;
		this.amount = amount;
	}
	public DwdDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDrugid() {
		return drugid;
	}
	public void setDrugid(int drugid) {
		this.drugid = drugid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
