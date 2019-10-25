package pharmacy.web.store.model.dto;

import pharmacy.common.model.DrugInf;

public class DrugBreakageOperationDto extends DrugInf{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer hSum;
    private Integer pSum;
    private String drugTypeName;
    private Integer dwdId;
    public DrugBreakageOperationDto() {
        super();
    }
    public DrugBreakageOperationDto(Integer hSum, Integer pSum, String drugTypeName, Integer dwdId) {
        super();
        this.hSum = hSum;
        this.pSum = pSum;
        this.drugTypeName = drugTypeName;
        this.dwdId = dwdId;
    }
    public Integer gethSum() {
        return hSum;
    }
    public void sethSum(Integer hSum) {
        this.hSum = hSum;
    }
    public Integer getpSum() {
        return pSum;
    }
    public void setpSum(Integer pSum) {
        this.pSum = pSum;
    }
    public String getDrugTypeName() {
        return drugTypeName;
    }
    public void setDrugTypeName(String drugTypeName) {
        this.drugTypeName = drugTypeName;
    }
    public Integer getDwdId() {
        return dwdId;
    }
    public void setDwdId(Integer dwdId) {
        this.dwdId = dwdId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DrugBreakageOperationDto [hSum=");
        builder.append(hSum);
        builder.append(", pSum=");
        builder.append(pSum);
        builder.append(", drugTypeName=");
        builder.append(drugTypeName);
        builder.append(", dwdId=");
        builder.append(dwdId);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
    
}
