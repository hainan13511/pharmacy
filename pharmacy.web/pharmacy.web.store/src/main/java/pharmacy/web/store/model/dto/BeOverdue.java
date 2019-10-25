package pharmacy.web.store.model.dto;

public class BeOverdue {

	private String did;
	private String dname;
	private String dtime;
	private String number;
	public BeOverdue(String did, String dname, String dtime, String number) {
		super();
		this.did = did;
		this.dname = dname;
		this.dtime = dtime;
		this.number = number;
	}
	public BeOverdue() {
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeOverdue [did=");
		builder.append(did);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", dtime=");
		builder.append(dtime);
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}
	
}
