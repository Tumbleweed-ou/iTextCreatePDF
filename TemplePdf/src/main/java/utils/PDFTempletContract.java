package utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import vo.RentContarctVo;
import vo.WuYeHandoverBillVo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ozw
 * @create 2020-07-21 15:14
 */
public class PDFTempletContract {

    private static final String templatePdfPath = "src\\main\\resources\\PDFfiles\\contract.pdf";

    public void CreateTemplePdf(RentContarctVo rentContarctVo, File file) throws IOException, DocumentException {

        BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        Font font = new Font(baseFont);
        int yearIndex,monIndex,dayIndex;

        //时间大写格式转换
        DateSwitchUtils dsu = new DateSwitchUtils();
        //金额大写格式转换
        NumUpperUtils nu = new NumUpperUtils();
        //创建一个pdf读取对象
        PdfReader reader = new PdfReader(templatePdfPath);
        //创建一个输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //创建pdf模板，参数reader  bos
        PdfStamper ps = new PdfStamper(reader, bos);
        //封装数据
        AcroFields s = ps.getAcroFields();

        s.setField("contractNo",rentContarctVo.getContractNo());
        s.setField("tenant",rentContarctVo.getTenant());
        s.setField("societyCreditNo",rentContarctVo.getSocietyCreditNo());
        s.setField("tenantAddress",rentContarctVo.getTenantAddress());
        s.setField("tenantPhone",rentContarctVo.getTenantPhone());
        s.setField("bulidingNo",rentContarctVo.getRoomVo().getBulidingNo());
        s.setField("houseNo",rentContarctVo.getRoomVo().getHouseNo());
        //设置合同开始日期格式
        String startDate = dsu.DateUpper(rentContarctVo.getStartDate());
        s.setField("startDateYear",startDate.substring(2,4));
        yearIndex = subDate(dsu.DateUpper(rentContarctVo.getStartDate()),"年");
        monIndex = subDate(dsu.DateUpper(rentContarctVo.getStartDate()),"月");
        dayIndex = subDate(dsu.DateUpper(rentContarctVo.getStartDate()),"日");
        s.setField("startDateMon",startDate.substring(yearIndex+1,monIndex));
        s.setField("startDateDay",startDate.substring(monIndex+1,dayIndex));
        //设置合同结束日期格式
        String endDate = dsu.DateUpper(rentContarctVo.getEndDate());
        s.setField("endDateYear",endDate.substring(2,4));
        yearIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"年");
        monIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"月");
        dayIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"日");
        s.setField("endDateMon",endDate.substring(yearIndex+1,monIndex));
        s.setField("endDateDay",endDate.substring(monIndex+1,dayIndex));
        //设置首月结束日期格式
        String endFirstDate = dsu.DateAdd(rentContarctVo.getStartDate());
        s.setField("endFirstMonRentYear",endFirstDate.substring(2,4));
        yearIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"年");
        monIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"月");
        dayIndex = subDate(dsu.DateUpper(rentContarctVo.getEndDate()),"日");
        s.setField("endFirstMonRentMon",endFirstDate.substring(yearIndex+1,monIndex));
        s.setField("endFirstMonRentDay",endFirstDate.substring(monIndex+1,dayIndex));
        s.setField("firstMonTotalDay","30");
        //设置金额
        String money = null;
        money=nu.numUpper(String.valueOf(rentContarctVo.getRent()));
        s.setField("rentUpper",money);
        s.setField("rentLower", String.valueOf(rentContarctVo.getRent()));
        money=nu.numUpper(String.valueOf(rentContarctVo.getServiceFee()));
        s.setField("serviceFeeUpper",money);
        s.setField("serviceFeeLower",String.valueOf(rentContarctVo.getServiceFee()));
        money=nu.numUpper(String.valueOf(rentContarctVo.getFirstMonRent()));
        s.setField("firstMonRentUpper",money);
        s.setField("firstMonRentLower",String.valueOf(rentContarctVo.getFirstMonRent()));
        money=nu.numUpper(String.valueOf(rentContarctVo.getFirstMonServiceFee()));
        s.setField("firstMonServiceFeeUpper",money);
        s.setField("firstMonServiceFeeLower",String.valueOf(rentContarctVo.getFirstMonServiceFee()));
        money=nu.numUpper(String.valueOf(rentContarctVo.getGuarantee()));
        s.setField("guaranteeUpper",money);
        s.setField("guaranteeLower",String.valueOf(rentContarctVo.getGuarantee()));
        //设置签署日期
        String signDate = dsu.DateLower(rentContarctVo.getSignDate());
        s.setField("signDateYear",signDate.substring(0,4));
        s.setField("signDateMon",signDate.substring(5,7));
        s.setField("signDateDay",signDate.substring(8));

        //设置图片域
       /* int pageNo;
        Rectangle signRect;
        float x,y;
        Image image;
        PdfContentByte under;

         // 设置甲方签名
        pageNo = s.getFieldPositions("ownerSignImg").get(0).page;
        signRect = s.getFieldPositions("ownerSignImg").get(0).position;
        x = signRect.getLeft();
        y = signRect.getBottom();
        // 根据路径读取图片
        image = Image.getInstance(rentContarctVo.getOwnerSignImg());
        // 获取图片页面
        under = ps.getOverContent(pageNo);
        // 图片大小自适应
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);

         //设置乙方签名
        pageNo = s.getFieldPositions("tenantSignImg").get(0).page;
        signRect = s.getFieldPositions("tenantSignImg").get(0).position;
        x = signRect.getLeft();
        y = signRect.getBottom();
        // 根据路径读取图片
        image = Image.getInstance(rentContarctVo.getTenantSignImg());
        // 获取图片页面
        under = ps.getOverContent(pageNo);
        // 图片大小自适应
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);
*/

        //填充表格
        List<WuYeHandoverBillVo> wuYelist = rentContarctVo.getRoomVo().getBillVoList();
        int i = 1;
        for(WuYeHandoverBillVo vo:wuYelist){
            s.setField("eq_"+i,vo.getEquipmentName());
            s.setField("p_"+i,vo.getPrice()+"元/"+vo.getUnit());
            s.setField("Num_"+i, String.valueOf(vo.getPropertNum()));
            if(vo.getStatus()==0){
                s.setField("st_"+i,"正常");
            }else{
                s.setField("st_"+i,"异常");
            }
            i++;
        }

        ps.setFormFlattening(true);//这里true表示pdf不可编辑
        ps.close();//关闭PdfStamper
        FileOutputStream fos = new FileOutputStream(file);//创建文件输出流
        fos.write(bos.toByteArray());//写入数据
        fos.close();//关闭输出流
    }

    //获取日期某个字符串的下标
    private static int subDate(String date,String char1){
        int indexOf = date.indexOf(char1);
        return indexOf;
    }


}
