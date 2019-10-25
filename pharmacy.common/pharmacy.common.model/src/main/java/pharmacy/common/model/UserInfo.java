package pharmacy.common.model;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private String uaccount;
	private String upwd;
	private String uname;
	private String ustate;
	private String utime;
	public UserInfo(int uid, String uaccount, String upwd, String uname, String ustate, String utime) {
		super();
		this.uid = uid;
		this.uaccount = uaccount;
		this.upwd = upwd;
		this.uname = uname;
		this.ustate = ustate;
		this.utime = utime;
	}
	public UserInfo() {
		super();
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUaccount() {
		return uaccount;
	}
	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String ustate) {
		this.ustate = ustate;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [uid=");
		builder.append(uid);
		builder.append(", uaccount=");
		builder.append(uaccount);
		builder.append(", upwd=");
		builder.append(upwd);
		builder.append(", uname=");
		builder.append(uname);
		builder.append(", ustate=");
		builder.append(ustate);
		builder.append(", utime=");
		builder.append(utime);
		builder.append("]");
		return builder.toString();
	}
	
}
