package pharmacy.web.store.model.dto;

import java.util.List;

/**
 * 
 * <p>Title : ListDrugInfDto</p>
 * <p>Description : 药品表集合与总数</p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : windows 10</p>
 * <p>Company : org.yhn</p>
 * @author : Yhn
 * @date : 2019年8月29日 上午11:52:50
 * @version : 1.0.0
 */
public class ListDrugInfDto {

    private List<DrugInfDto> list;
    private Integer count;
    public ListDrugInfDto() {
        super();
    }
    public ListDrugInfDto(List<DrugInfDto> list, Integer count) {
        super();
        this.list = list;
        this.count = count;
    }
    public List<DrugInfDto> getList() {
        return list;
    }
    public void setList(List<DrugInfDto> list) {
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
        builder.append("ListDrugInfDto [list=");
        builder.append(list);
        builder.append(", count=");
        builder.append(count);
        builder.append("]");
        return builder.toString();
    }
    
    
}
