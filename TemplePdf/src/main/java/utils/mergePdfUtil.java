package utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-07-31 16:40
 */
public class mergePdfUtil {

    /**
     * 使用pdfCopy合并
     * @param files
     * @param newFiles
     * @return
     */
    public boolean mergePdfByCopy(String[] files,String newFiles){
        boolean flag = false;
        Document document = null;

        try {
            document = new Document();
            PdfCopy copy = new PdfCopy(document,new FileOutputStream(newFiles));
            document.open();

            for(int i = 0;i < files.length;i++){   //几个pdf文件循环
                PdfReader reader = new PdfReader(files[i]);
                int pageNum = reader.getNumberOfPages();
                for(int j = 1; j <= pageNum;j++){  //一个文件有多少页
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader,j);
                    copy.addPage(page);
                }
            }
          flag = true;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
        return flag;
    }

    /**
     * 使用PdfWriter合并
     * @param files
     * @param newFiles
     * @return
     */
    public boolean mergePdfByWrite(String[] files,String newFiles){
        boolean flag = false;
        Document document = null;

        try {
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(newFiles));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent(); //得到层
            for(int i = 0;i < files.length;i++){
                PdfReader reader = new PdfReader(files[i]);
                int pageNum = reader.getNumberOfPages();
                for(int j = 1;j <= pageNum;j++){
                    document.newPage();
                    PdfImportedPage page = writer.getImportedPage(reader,j);
                    contentByte.addTemplate(page,0,0); // 使用writer需要使用pdf的层,然后后添加
                }
            }
            flag = true;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
        return flag;
    }

}
