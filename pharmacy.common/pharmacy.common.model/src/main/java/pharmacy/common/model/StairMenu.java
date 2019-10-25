package pharmacy.common.model;

import java.io.Serializable;

public class StairMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuid;
	private String menuname;
	private String icon;
	private String pid;
	public StairMenu(String menuid, String menuname, String icon, String pid) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.icon = icon;
		this.pid = pid;
	}
	public StairMenu() {
		super();
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StairMenu [menuid=");
		builder.append(menuid);
		builder.append(", menuname=");
		builder.append(menuname);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", pid=");
		builder.append(pid);
		builder.append("]");
		return builder.toString();
	}
	
}
