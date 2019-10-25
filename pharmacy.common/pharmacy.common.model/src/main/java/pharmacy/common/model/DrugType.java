package pharmacy.common.model;

public class DrugType {

    private Integer dtId;
    private String dtName;
    private Integer fId;
    public DrugType() {
        super();
    }
    public DrugType(Integer dtId, String dtName, Integer fId) {
        super();
        this.dtId = dtId;
        this.dtName = dtName;
        this.fId = fId;
    }
    public Integer getDtId() {
        return dtId;
    }
    public void setDtId(Integer dtId) {
        this.dtId = dtId;
    }
    public String getDtName() {
        return dtName;
    }
    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
    public Integer getfId() {
        return fId;
    }
    public void setfId(Integer fId) {
        this.fId = fId;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugType [dtId=");
        builder.append(dtId);
        builder.append(", dtName=");
        builder.append(dtName);
        builder.append(", fId=");
        builder.append(fId);
        builder.append("]");
        return builder.toString();
    }
    
    
}
