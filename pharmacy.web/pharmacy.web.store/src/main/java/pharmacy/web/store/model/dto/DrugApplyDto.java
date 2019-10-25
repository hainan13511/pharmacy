package pharmacy.web.store.model.dto;

import java.util.List;
/**
 * 
 * <p>Title : DrugApplyDto</p>
 * <p>Description : 药品申请表集合与总数</p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : windows 10</p>
 * <p>Company : org.yhn</p>
 * @author : Yhn
 * @date : 2019年8月27日 下午12:15:54
 * @version : 1.0.0
 */
public class DrugApplyDto{

    private List<DrugApplyDetailDto> list;
    private Integer count;
    public DrugApplyDto() {
        super();
    }
    public DrugApplyDto(List<DrugApplyDetailDto> list, Integer count) {
        super();
        this.list = list;
        this.count = count;
    }
    public List<DrugApplyDetailDto> getList() {
        return list;
    }
    public void setList(List<DrugApplyDetailDto> list) {
        this.list = list;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugApplyDto [list=");
        builder.append(list);
        builder.append(", count=");
        builder.append(count);
        builder.append("]");
        return builder.toString();
    }
    
}
