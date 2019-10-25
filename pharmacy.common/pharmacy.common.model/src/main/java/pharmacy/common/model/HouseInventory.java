package pharmacy.common.model;

import java.util.Date;

public class HouseInventory {

    private Integer houseId;
    private Integer drugId;
    private Integer houseCount;
    private Integer dwdId;
    private Date houseDate;
    public HouseInventory() {
        super();
    }
    public HouseInventory(Integer houseId, Integer drugId, Integer houseCount, Integer dwdId, Date houseDate) {
        super();
        this.houseId = houseId;
        this.drugId = drugId;
        this.houseCount = houseCount;
        this.dwdId = dwdId;
        this.houseDate = houseDate;
    }
    public Integer getHouseId() {
        return houseId;
    }
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
    public Integer getDrugId() {
        return drugId;
    }
    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }
    public Integer getHouseCount() {
        return houseCount;
    }
    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }
    public Integer getDwdId() {
        return dwdId;
    }
    public void setDwdId(Integer dwdId) {
        this.dwdId = dwdId;
    }
    public Date getHouseDate() {
        return houseDate;
    }
    public void setHouseDate(Date houseDate) {
        this.houseDate = houseDate;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HouseInventory [houseId=");
        builder.append(houseId);
        builder.append(", drugId=");
        builder.append(drugId);
        builder.append(", houseCount=");
        builder.append(houseCount);
        builder.append(", dwdId=");
        builder.append(dwdId);
        builder.append(", houseDate=");
        builder.append(houseDate);
        builder.append("]");
        return builder.toString();
    }
    
}
