package pharmacy.web.store.model.dto;

public class NpdsEnterWarehouse {

	private String did;
	private String dname;
	private String dtime;
	private String number;
	private String money;
	private String supplier;
	private String aname;
	public NpdsEnterWarehouse(String did, String dname, String dtime, String number, String money, String supplier,
			String aname) {
		super();
		this.did = did;
		this.dname = dname;
		this.dtime = dtime;
		this.number = number;
		this.money = money;
		this.supplier = supplier;
		this.aname = aname;
	}
	public NpdsEnterWarehouse() {
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
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NpdsEnterWarehouse [did=");
		builder.append(did);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", dtime=");
		builder.append(dtime);
		builder.append(", number=");
		builder.append(number);
		builder.append(", money=");
		builder.append(money);
		builder.append(", supplier=");
		builder.append(supplier);
		builder.append(", aname=");
		builder.append(aname);
		builder.append("]");
		return builder.toString();
	}
	
}
