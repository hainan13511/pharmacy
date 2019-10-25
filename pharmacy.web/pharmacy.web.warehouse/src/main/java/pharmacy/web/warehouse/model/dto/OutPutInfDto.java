package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.OutPutInf;

public class OutPutInfDto extends OutPutInf{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drugName;
	private String uname;
	public OutPutInfDto(String drugName, String uname) {
		super();
		this.drugName = drugName;
		this.uname = uname;
	}
	public OutPutInfDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
