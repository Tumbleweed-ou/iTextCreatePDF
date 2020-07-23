package vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ozw
 * @create 2020-07-17 9:36
 */
public class BulidingRoomVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ID;

    private String bulidingNo;

    private String houseNo;



    public final static String EUPIMENTSORT = "场\n\n地\n\n内\n\n设\n\n备\n\n设\n\n施";

    List<WuYeHandoverBillVo> billVoList;

    public Long getID() {
        return ID;
    }

    public String getBulidingNo() {
        return bulidingNo;
    }

    public List<WuYeHandoverBillVo> getBillVoList() {
        return billVoList;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setBulidingNo(String bulidingNo) {
        this.bulidingNo = bulidingNo;
    }

    public void setBillVoList(List<WuYeHandoverBillVo> billVoList) {
        this.billVoList = billVoList;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
}
