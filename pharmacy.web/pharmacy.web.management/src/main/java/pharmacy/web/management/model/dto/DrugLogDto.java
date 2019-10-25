package pharmacy.web.management.model.dto;

import pharmacy.common.model.DrugLog;

public class DrugLogDto extends DrugLog {

	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public DrugLogDto(int logId, String logContent, String uId, String logDate, String uname) {
		super(logId, logContent, uId, logDate);
		this.uname = uname;
	}

	public DrugLogDto() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugLogDto [uname=");
		builder.append(uname);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
