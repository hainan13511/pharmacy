package pharmacy.common.model;

import java.io.Serializable;

public class Limit  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String limitid;
	private String limitname;
	private String url;
	private String menuid;
	public Limit(String limitid, String limitname, String url, String menuid) {
		super();
		this.limitid = limitid;
		this.limitname = limitname;
		this.url = url;
		this.menuid = menuid;
	}
	public Limit() {
		super();
	}
	public String getLimitid() {
		return limitid;
	}
	public void setLimitid(String limitid) {
		this.limitid = limitid;
	}
	public String getLimitname() {
		return limitname;
	}
	public void setLimitname(String limitname) {
		this.limitname = limitname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Limit [limitid=");
		builder.append(limitid);
		builder.append(", limitname=");
		builder.append(limitname);
		builder.append(", url=");
		builder.append(url);
		builder.append(", menuid=");
		builder.append(menuid);
		builder.append("]");
		return builder.toString();
	}
	
}
