package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugApply;
/**
 * drugid 药品ID
 * uname 用户名
 * @author ZQH
 *
 */
public class AuditInfDto extends DrugApply{

	private String drugid;
	private String uname;
	
	public AuditInfDto() {
		
	}

	public AuditInfDto(String drugid, String uname) {
		super();
		this.drugid = drugid;
		this.uname = uname;
	}

	public String getDrugid() {
		return drugid;
	}

	public void setDrugid(String drugid) {
		this.drugid = drugid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditInfDto [drugid=");
		builder.append(drugid);
		builder.append(", uname=");
		builder.append(uname);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
}
