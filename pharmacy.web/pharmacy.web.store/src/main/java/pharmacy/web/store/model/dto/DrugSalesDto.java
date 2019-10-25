package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugSales;

/**
 *继承Drugsales的类
 * <p>Title : DrugSalesDto</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月31日 下午2:24:09
 * @version : 1.0.0
 */
public class DrugSalesDto extends DrugSales{

	private String drugName;

	public DrugSalesDto() {
		// TODO Auto-generated constructor stub
	}

	public DrugSalesDto(String drugName) {
		super();
		this.drugName = drugName;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugSalesDto [drugName=");
		builder.append(drugName);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
