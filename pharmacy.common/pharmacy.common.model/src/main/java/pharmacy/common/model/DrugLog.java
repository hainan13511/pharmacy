package pharmacy.common.model;

public class DrugLog {

	private int logId;
	private String logContent;
	private String uId;
	private String logDate;
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public DrugLog(int logId, String logContent, String uId, String logDate) {
		super();
		this.logId = logId;
		this.logContent = logContent;
		this.uId = uId;
		this.logDate = logDate;
	}
	public DrugLog() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugLog [logId=");
		builder.append(logId);
		builder.append(", logContent=");
		builder.append(logContent);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", logDate=");
		builder.append(logDate);
		builder.append("]");
		return builder.toString();
	}
	
}
