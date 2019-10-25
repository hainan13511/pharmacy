package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugApply;

public class DrugApplyDetailDto extends DrugApply{

    private String userName;

    
    public DrugApplyDetailDto() {
        super();
    }


    public DrugApplyDetailDto(String userName) {
        super();
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugApplyDetailDto [userName=");
        builder.append(userName);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
    
}
