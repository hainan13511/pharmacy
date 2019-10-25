package pharmacy.common.model;

public class Role {

	private String roleid;
	private String rolename;
	public Role(String roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	public Role() {
		super();
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [roleid=");
		builder.append(roleid);
		builder.append(", rolename=");
		builder.append(rolename);
		builder.append("]");
		return builder.toString();
	}
	
}
