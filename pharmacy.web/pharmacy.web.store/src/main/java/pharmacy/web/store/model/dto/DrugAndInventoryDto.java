package pharmacy.web.store.model.dto;

import java.util.List;

public class DrugAndInventoryDto {

    private List<DrugBreakageOperationDto> list;
    private int count;
    public DrugAndInventoryDto() {
        super();
    }
    public DrugAndInventoryDto(List<DrugBreakageOperationDto> list, int count) {
        super();
        this.list = list;
        this.count = count;
    }
    public List<DrugBreakageOperationDto> getList() {
        return list;
    }
    public void setList(List<DrugBreakageOperationDto> list) {
        this.list = list;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugAndInventoryDto [list=");
        builder.append(list);
        builder.append(", count=");
        builder.append(count);
        builder.append("]");
        return builder.toString();
    }
    
    
}
