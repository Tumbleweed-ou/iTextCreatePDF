package vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ozw
 * @create 2020-07-20 16:38
 */
public class RentContarctVo implements Serializable {

    private String contractNo;

    //承租方
    private String tenant;

    private String societyCreditNo;

    private String tenantAddress;

    private String tenantPhone;

    private Date startDate;

    private Date endDate;

    private BulidingRoomVo roomVo;

    private BigDecimal rent;

    private BigDecimal serviceFee;

    private BigDecimal firstMonRent;

    private BigDecimal firstMonServiceFee;

    //保证金
    private BigDecimal guarantee;

    private String ownerSignImg;

    private String tenantSignImg;

    private Date SignDate;


    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getSocietyCreditNo() {
        return societyCreditNo;
    }

    public void setSocietyCreditNo(String societyCreditNo) {
        this.societyCreditNo = societyCreditNo;
    }

    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BulidingRoomVo getRoomVo() {
        return roomVo;
    }

    public void setRoomVo(BulidingRoomVo roomVo) {
        this.roomVo = roomVo;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getFirstMonRent() {
        return firstMonRent;
    }

    public void setFirstMonRent(BigDecimal firstMonRent) {
        this.firstMonRent = firstMonRent;
    }

    public BigDecimal getFirstMonServiceFee() {
        return firstMonServiceFee;
    }

    public void setFirstMonServiceFee(BigDecimal firstMonServiceFee) {
        this.firstMonServiceFee = firstMonServiceFee;
    }

    public BigDecimal getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(BigDecimal guarantee) {
        this.guarantee = guarantee;
    }

    public String getOwnerSignImg() {
        return ownerSignImg;
    }

    public void setOwnerSignImg(String ownerSignImg) {
        this.ownerSignImg = ownerSignImg;
    }

    public String getTenantSignImg() {
        return tenantSignImg;
    }

    public void setTenantSignImg(String tenantSignImg) {
        this.tenantSignImg = tenantSignImg;
    }

    public Date getSignDate() {
        return SignDate;
    }

    public void setSignDate(Date signDate) {
        SignDate = signDate;
    }
}


