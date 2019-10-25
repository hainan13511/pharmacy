package pharmacy.common.model;

/**
 * 药品报损明细bean
 * <p>Title : DrugBreakage</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月29日 上午11:49:54
 * @version : 1.0.0
 */
public class DrugBreakage {

	private int breakageId;
	private String breakageContent;
	private int uId;
	private String breakageDate;
	private int breakageCount;
	public DrugBreakage(int breakageId, String breakageContent, int uId, String breakageDate, int breakageCount) {
		super();
		this.breakageId = breakageId;
		this.breakageContent = breakageContent;
		this.uId = uId;
		this.breakageDate = breakageDate;
		this.breakageCount = breakageCount;
	}
	public DrugBreakage() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugBreakage [breakageId=");
		builder.append(breakageId);
		builder.append(", breakageContent=");
		builder.append(breakageContent);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", breakageDate=");
		builder.append(breakageDate);
		builder.append(", breakageCount=");
		builder.append(breakageCount);
		builder.append("]");
		return builder.toString();
	}
	public int getBreakageId() {
		return breakageId;
	}
	public void setBreakageId(int breakageId) {
		this.breakageId = breakageId;
	}
	public String getBreakageContent() {
		return breakageContent;
	}
	public void setBreakageContent(String breakageContent) {
		this.breakageContent = breakageContent;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getBreakageDate() {
		return breakageDate;
	}
	public void setBreakageDate(String breakageDate) {
		this.breakageDate = breakageDate;
	}
	public int getBreakageCount() {
		return breakageCount;
	}
	public void setBreakageCount(int breakageCount) {
		this.breakageCount = breakageCount;
	}
	
}
