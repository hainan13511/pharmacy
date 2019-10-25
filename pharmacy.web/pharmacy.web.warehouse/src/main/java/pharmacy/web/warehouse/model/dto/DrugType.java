package pharmacy.web.warehouse.model.dto;

public class DrugType {
	
	private int dtid;
	private String dtname;
	public DrugType(int dtid, String dtname) {
		super();
		this.dtid = dtid;
		this.dtname = dtname;
	}
	public DrugType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDtid() {
		return dtid;
	}
	public void setDtid(int dtid) {
		this.dtid = dtid;
	}
	public String getDtname() {
		return dtname;
	}
	public void setDtname(String dtname) {
		this.dtname = dtname;
	}


	

}
