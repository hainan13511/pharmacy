package pharmacy.web.store.model.dto;

/**
 * 低限表的model（统计）
 * <p>Title : MinimumDto</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年9月7日 上午9:58:08
 * @version : 1.0.0
 */
public class MinimumDto {

	private String DrugName;
	private String MinimumVal;
	public MinimumDto(String drugName, String minimumVal) {
		super();
		DrugName = drugName;
		MinimumVal = minimumVal;
	}
	public MinimumDto() {
		super();
	}
	public String getDrugName() {
		return DrugName;
	}
	public void setDrugName(String drugName) {
		DrugName = drugName;
	}
	public String getMinimumVal() {
		return MinimumVal;
	}
	public void setMinimumVal(String minimumVal) {
		MinimumVal = minimumVal;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MinimumDto [DrugName=");
		builder.append(DrugName);
		builder.append(", MinimumVal=");
		builder.append(MinimumVal);
		builder.append("]");
		return builder.toString();
	}
	
	
}
