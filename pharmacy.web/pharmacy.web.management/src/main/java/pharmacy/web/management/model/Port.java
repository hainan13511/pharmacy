package pharmacy.web.management.model;

public class Port {
	/**
	 * 	属于药房端 还是药库端 或是管理员端
	 */
	private int pid; //端口ID
	private String pname; //端口名
	
	public Port() {
		// TODO Auto-generated constructor stub
	}

	public Port(int pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Port [pid=");
		builder.append(pid);
		builder.append(", pname=");
		builder.append(pname);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
