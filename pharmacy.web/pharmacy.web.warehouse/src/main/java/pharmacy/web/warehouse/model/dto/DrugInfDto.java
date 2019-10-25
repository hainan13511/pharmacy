package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugInf;

public class DrugInfDto extends DrugInf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dtname;
	private String fname;

	public DrugInfDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DrugInfDto(String dtname, String fname) {
		super();
		this.dtname = dtname;
		this.fname = fname;
	}

	public String getDtname() {
		return dtname;
	}
	public void setDtname(String dtname) {
		this.dtname = dtname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
