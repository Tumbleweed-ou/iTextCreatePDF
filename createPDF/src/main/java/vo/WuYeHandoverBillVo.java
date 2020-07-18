package vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ozw
 * @create 2020-07-17 9:34
 */
public class WuYeHandoverBillVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ID;

    private String equipmentName;

    private BigDecimal price;

    private String unit;

    private Integer propertNum;

    private Integer status;



    public Long getID() {
        return ID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getPropertNum() {
        return propertNum;
    }

    public Integer getStatus() {
        return status;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getUnit() {
        return unit;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPropertNum(Integer propertNum) {
        this.propertNum = propertNum;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
