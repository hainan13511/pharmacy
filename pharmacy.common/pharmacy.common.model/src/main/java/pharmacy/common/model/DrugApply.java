package pharmacy.common.model;

/**
 * 
 * <p>Title : DrugApply</p>
 * <p>Description : 药品申请表</p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : macOS Sierra 10.12.1</p>
 * <p>Company : org.yhn</p>
 * @date : 2019年8月27日 上午11:58:07
 * @version : 1.0.0
 */
public class DrugApply {

    private Integer applyId;
    private String drugName;
    private Integer userId;
    private String applyTime;
    private int applyNum;
    private int checkState;
    public DrugApply() {
        super();
    }
	public DrugApply(Integer applyId, String drugName, Integer userId, String applyTime, int applyNum, int checkState) {
		super();
		this.applyId = applyId;
		this.drugName = drugName;
		this.userId = userId;
		this.applyTime = applyTime;
		this.applyNum = applyNum;
		this.checkState = checkState;
	}
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public int getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	public int getCheckState() {
		return checkState;
	}
	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugApply [applyId=");
		builder.append(applyId);
		builder.append(", drugName=");
		builder.append(drugName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", applyTime=");
		builder.append(applyTime);
		builder.append(", applyNum=");
		builder.append(applyNum);
		builder.append(", checkState=");
		builder.append(checkState);
		builder.append("]");
		return builder.toString();
	}

    
}
