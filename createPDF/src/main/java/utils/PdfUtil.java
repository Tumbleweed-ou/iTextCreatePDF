package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import vo.BulidingRoomVo;
import vo.WuYeHandoverBillVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author ozw
 * @create 2020-07-17 10:28
 */
public class PdfUtil {


   private final static String PATH = "d:/";

   private final static String fileName = "合同附件模板-附件1.pdf";

   public static void createPdf(List<BulidingRoomVo> billList) {

      File file = new File(PATH + fileName);
      FileOutputStream out = null;

      try {
         out = new FileOutputStream(file);

         Document document = new Document(PageSize.A4, 3, 3, 50, 50);
         PdfWriter writer = PdfWriter.getInstance(document, out);
         document.open();

         //设置字体
         BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
         Font font = new Font(baseFont, 9, Font.NORMAL, BaseColor.BLACK);
         Font p1font = new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK);
         Font p2font = new Font(baseFont, 25, Font.BOLD, BaseColor.BLACK);
         Font p3font = new Font(baseFont, 13, Font.BOLD, BaseColor.BLACK);
         Font p4font = new Font(baseFont, 11, Font.BOLD, BaseColor.BLACK);

         /*Paragraph p1 = new Paragraph("附件1：", p1font);
         p1.setIndentationLeft(70);
         p1.setLeading(10f);
         document.add(p1);

         Paragraph p2 = new Paragraph("物业移交清单", p2font);
         p2.setAlignment(1);
         p2.setSpacingAfter(10f);
         document.add(p2);*/

         //绘制表格
         for (BulidingRoomVo vo : billList) {

            Paragraph p1 = new Paragraph("附件1：", p1font);
            p1.setIndentationLeft(70);
            p1.setLeading(10f);
            document.add(p1);

            Paragraph p2 = new Paragraph("物业移交清单", p2font);
            p2.setAlignment(1);
            p2.setSpacingAfter(10f);
            document.add(p2);


            Paragraph p3 = new Paragraph(vo.getBulidingNo() + "栋" + vo.getHouseNo() + "房:", p3font);
            p3.setSpacingAfter(10f);
            p3.setIndentationLeft(60);
            document.add(p3);
            PdfPTable table = createTable(vo.getBillVoList(), font);
            document.add(table);
            document.add(new Phrase("\n"));

            String str = "以上设备设施如认为损坏，须请照原价赔偿，若部分损坏请按甲方依据实际情况给出的评估" +
                    "价格标准，进行赔偿 ";
            Paragraph p4 = new Paragraph(str, p4font);
            p4.setIndentationLeft(70);
            p4.setIndentationRight(60);
            document.add(p4);
            //document.add(new Phrase("\n"));
            document.newPage();
         }

         /*String str = "以上设备设施如认为损坏，须请照原价赔偿，若部分损坏请按甲方依据实际情况给出的评估" +
                 "价格标准，进行赔偿 ";
         Paragraph p4 = new Paragraph(str, p4font);
         p4.setIndentationLeft(70);
         p4.setIndentationRight(70);
         document.add(p4);*/

         document.close();

      } catch (DocumentException e) {
         e.printStackTrace();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (out != null) {
            try {
               //关闭输出文件流
               out.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }

      }

   }


   private static PdfPTable createTable(List<WuYeHandoverBillVo> billVoList, Font font) throws DocumentException {

      int colNums = 3;
      PdfPTable MainTable = new PdfPTable(colNums);
      int[] widths = {40, 110, 110};
      MainTable.setWidths(widths);
      MainTable.setTotalWidth(300);
      MainTable.getDefaultCell().setBorderColor(BaseColor.BLACK);
      MainTable.getDefaultCell().setBorderWidth(1.0f);
      MainTable.getDefaultCell().setPadding(0);
      MainTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      MainTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BASELINE);

      float lineHeight = 20.0f;
      PdfPCell cell;

      try {
         //设置第一列
         PdfPTable table1 = new PdfPTable(1);
         table1.setTotalWidth(40);
         //设置第一列表头
         PdfPCell T1cell = new PdfPCell(new Paragraph("设备类别", font));
         T1cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         T1cell.setVerticalAlignment(Element.ALIGN_CENTER);
         T1cell.setFixedHeight(lineHeight);
         table1.addCell(T1cell);
         //设置第一列内容
         T1cell = mergeRow(BulidingRoomVo.EUPIMENTSORT, font, 1);
         T1cell.setFixedHeight(lineHeight);
         table1.addCell(T1cell);

         cell = new PdfPCell(table1);
         cell.setPadding(0);
         MainTable.addCell(cell);


         //填充内容
         PdfPTable table2 = addContent(billVoList, font, lineHeight);
         cell = new PdfPCell(table2);
         cell.setPadding(0);
         MainTable.addCell(cell);

         PdfPTable table3 = addContent(billVoList, font, lineHeight);
         cell = new PdfPCell(table3);
         cell.setPadding(0);
         MainTable.addCell(cell);


      } catch (Exception e) {
         e.printStackTrace();
      }
      return MainTable;
   }

   //合并行的静态函数
   public static PdfPCell mergeRow(String str, Font font, int i) {

      //创建单元格对象，将内容及字体传入
      PdfPCell cell = new PdfPCell(new Paragraph(str, font));
      //设置单元格内容居中
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      //将该单元格所在列包括该单元格在内的i行单元格合并为一个单元格
      cell.setRowspan(i);

      return cell;
   }

   //填充内容
   private static PdfPTable addContent(List<WuYeHandoverBillVo> billVoList, Font font, float lineHeight) throws DocumentException {
      PdfPTable table = new PdfPTable(4);
      int[] widths = {40, 40, 35, 35};
      table.setWidths(widths);
      table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BASELINE);
      table.getDefaultCell().setFixedHeight(lineHeight);
      //设置表头
      table.addCell(new Paragraph("设施名称", font));
      table.addCell(new Paragraph("赔偿价格", font));
      table.addCell(new Paragraph("数量", font));
      table.addCell(new Paragraph("状况", font));

      int count = 1;
      Iterator<WuYeHandoverBillVo> iterator = billVoList.iterator();
      if (iterator.hasNext()) {
         while (iterator.hasNext()) {
            if (count > 20) {
               break;
            }
            WuYeHandoverBillVo vo = iterator.next();
            table.addCell(new Paragraph(vo.getEquipmentName(), font));
            table.addCell(new Paragraph(vo.getPrice() + "元/" + vo.getUnit(), font));
            ;
            table.addCell(new Paragraph(String.valueOf(vo.getPropertNum()), font));
            if (vo.getStatus() == 0) {
               table.addCell(new Paragraph("正常", font));
            } else {
               table.addCell(new Paragraph("异常", font));
            }
            iterator.remove();
            count++;
         }
      }

      if (count < 20) {
         while (count <= 20) {
            table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(""));
            count++;
         }
      }


      return table;
   }

}
