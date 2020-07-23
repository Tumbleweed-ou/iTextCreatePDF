import com.itextpdf.text.DocumentException;
import utils.PDFTempletContract;
import vo.BulidingRoomVo;
import vo.RentContarctVo;
import vo.WuYeHandoverBillVo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author shkstart
 * @create 2020-07-22 14:49
 */
public class templetTest {

    public static void main(String[] args) throws IOException, DocumentException {

        PDFTempletContract temp = new PDFTempletContract();
        RentContarctVo vo = null;
        try {
            vo= test();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        File file = new File("F:\\石岩-线下公司租赁合同.pdf");
        temp.CreateTemplePdf(vo,file);


    }


    public static RentContarctVo test() throws ParseException {


        List<WuYeHandoverBillVo> billVoList = new ArrayList<WuYeHandoverBillVo>();
        WuYeHandoverBillVo wuyeVo = new WuYeHandoverBillVo();
        wuyeVo.setEquipmentName("电热水器");
        wuyeVo.setPrice(BigDecimal.valueOf(1000));
        wuyeVo.setUnit("件");
        wuyeVo.setPropertNum(234);
        wuyeVo.setStatus(0);
        for(int i=0;i<30;i++){
            billVoList.add(wuyeVo);
        }

        BulidingRoomVo roomVo = new BulidingRoomVo();
        roomVo.setBulidingNo("A");
        roomVo.setHouseNo("101");
        roomVo.setBillVoList(billVoList);

        RentContarctVo vo= new RentContarctVo();
        Date Bigdate;
        vo.setContractNo(UUID.randomUUID().toString().replace("-","").substring(0,9).toUpperCase());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Bigdate = date.parse("2030-09-30 12:00:00");
        vo.setEndDate(Bigdate);
        vo.setFirstMonRent(BigDecimal.valueOf(4000));
        vo.setFirstMonServiceFee(BigDecimal.valueOf(500));
        vo.setGuarantee(BigDecimal.valueOf(5000));
        vo.setRent(BigDecimal.valueOf(5000));
        vo.setServiceFee(BigDecimal.valueOf(600));
        vo.setSignDate(new Date());
        vo.setSocietyCreditNo(UUID.randomUUID().toString().replace("-","").substring(0,11).toUpperCase());
        Bigdate = date.parse("2020-07-23 12:00:00");
        vo.setStartDate(Bigdate);
        vo.setTenant("王美丽");
        vo.setTenantAddress("深圳市宝安区");
        vo.setTenantPhone("12311111111");
        vo.setTenantSignImg("src\\main\\resources\\img\\tenantSignImg.JPG");
        vo.setOwnerSignImg("src\\main\\resources\\img\\onwerSignImg.JPG");
        vo.setRoomVo(roomVo);

      return vo;
    }
}
