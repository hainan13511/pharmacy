package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugBreakage;
/**
 * DrugBreakage的属性添加bean
 * <p>Title : DrugBreakageDto</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月29日 上午11:57:44
 * @version : 1.0.0
 */
public class DrugBreakageDto  extends DrugBreakage{

	private String uname;

	

	public DrugBreakageDto(int breakageId, String breakageContent, int uId, String breakageDate, int breakageCount,
			String uname) {
		super(breakageId, breakageContent, uId, breakageDate, breakageCount);
		this.uname = uname;
	}

	public DrugBreakageDto() {
		
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugBreakageDto [uname=");
		builder.append(uname);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
