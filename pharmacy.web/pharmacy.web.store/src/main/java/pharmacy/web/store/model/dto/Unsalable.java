package pharmacy.web.store.model.dto;

public class Unsalable {

	private String did;
	private String dname;
	private String etime;
	private String ptime;
	private String number;
	private String dwdid;
	
	public Unsalable(String did, String dname, String etime, String ptime, String number, String dwdid) {
		super();
		this.did = did;
		this.dname = dname;
		this.etime = etime;
		this.ptime = ptime;
		this.number = number;
		this.dwdid = dwdid;
	}
	public Unsalable() {
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
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getDwdid() {
		return dwdid;
	}
	public void setDwdid(String dwdid) {
		this.dwdid = dwdid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Unsalable [did=");
		builder.append(did);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", etime=");
		builder.append(etime);
		builder.append(", ptime=");
		builder.append(ptime);
		builder.append(", number=");
		builder.append(number);
		builder.append(", dwdid=");
		builder.append(dwdid);
		builder.append("]");
		return builder.toString();
	}
	
	
}
