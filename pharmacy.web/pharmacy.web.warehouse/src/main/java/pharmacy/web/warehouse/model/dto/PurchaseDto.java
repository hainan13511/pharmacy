package pharmacy.web.warehouse.model.dto;
/**
 * 采购统计表的model
 * <p>Title : PurchaseDto</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年9月2日 下午10:30:11
 * @version : 1.0.0
 */
public class PurchaseDto {

	private String uid;
	private String name;
	private int count;
	public PurchaseDto(String uid, String name, int count) {
		super();
		this.uid = uid;
		this.name = name;
		this.count = count;
	}
	public PurchaseDto() {
		super();
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
