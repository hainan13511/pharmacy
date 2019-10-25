package pharmacy.common.model;

/**
 * 药品销售登记表（盘点表明细）model
 * <p>Title : DrugSales</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月31日 下午2:15:15
 * @version : 1.0.0
 */
public class DrugSales {

	private int salesId;
	private int drugId;
	private int salesCount;
	private int salesCostPrice;
	private int salesSellPrice;
	private String salesDate;
	private int uId;
	
	
	public DrugSales() {
		super();
	}
	public DrugSales(int salesId, int drugId, int salesCount, int salesCostPrice, int salesSellPrice, String salesDate,
			int uId) {
		super();
		this.salesId = salesId;
		this.drugId = drugId;
		this.salesCount = salesCount;
		this.salesCostPrice = salesCostPrice;
		this.salesSellPrice = salesSellPrice;
		this.salesDate = salesDate;
		this.uId = uId;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public int getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}
	public int getSalesCostPrice() {
		return salesCostPrice;
	}
	public void setSalesCostPrice(int salesCostPrice) {
		this.salesCostPrice = salesCostPrice;
	}
	public int getSalesSellPrice() {
		return salesSellPrice;
	}
	public void setSalesSellPrice(int salesSellPrice) {
		this.salesSellPrice = salesSellPrice;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugSales [salesId=");
		builder.append(salesId);
		builder.append(", drugId=");
		builder.append(drugId);
		builder.append(", salesCount=");
		builder.append(salesCount);
		builder.append(", salesCostPrice=");
		builder.append(salesCostPrice);
		builder.append(", salesSellPrice=");
		builder.append(salesSellPrice);
		builder.append(", salesDate=");
		builder.append(salesDate);
		builder.append(", uId=");
		builder.append(uId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
