import utils.PdfUtil;
import vo.BulidingRoomVo;
import vo.WuYeHandoverBillVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-07-17 10:27
 */
public class Test {
    public static void main(String[] args) {
        List<BulidingRoomVo> roomList = add();
        PdfUtil.createPdf(roomList);

    }


    private static List<BulidingRoomVo> add(){

        List<BulidingRoomVo> roomList = new ArrayList<BulidingRoomVo>();
        List<WuYeHandoverBillVo> billVoList = new ArrayList<WuYeHandoverBillVo>();
        List<WuYeHandoverBillVo> billVoList1 = new ArrayList<WuYeHandoverBillVo>();

        WuYeHandoverBillVo wuyeVo = new WuYeHandoverBillVo();
        wuyeVo.setEquipmentName("电热水器");
        wuyeVo.setPrice(BigDecimal.valueOf(1000));
        wuyeVo.setUnit("件");
        wuyeVo.setPropertNum(234);
        wuyeVo.setStatus(0);
        for(int i=0;i<30;i++){
            billVoList.add(wuyeVo);
            billVoList1.add(wuyeVo);
        }


        BulidingRoomVo roomVo = new BulidingRoomVo();
        roomVo.setBulidingNo("A");
        roomVo.setHouseNo("101");
        roomVo.setBillVoList(billVoList);
        roomList.add(roomVo);

        BulidingRoomVo roomVo1 = new BulidingRoomVo();
        roomVo1.setBulidingNo("B");
        roomVo1.setHouseNo("102");
        roomVo1.setBillVoList(billVoList1);
        roomList.add(roomVo1);






        return roomList;
    }

}



