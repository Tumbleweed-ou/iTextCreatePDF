package utils;

import com.itextpdf.text.DocumentException;
import vo.RentContarctVo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ozw
 * @create 2020-07-29 11:19
 */
public class CreateContract {

    public static final String SRC = "src\\main\\resources\\PDFfiles\\temp\\"+ UUID.randomUUID().toString()+".pdf";

    private static void fill(RentContarctVo rentContarctVo,File file){
        //填充pdf模板信息
        PDFTempletContract pdfTempletContract = new PDFTempletContract();
        try {
            pdfTempletContract.CreateTemplePdf(rentContarctVo,file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private static void sign(String desc){
        //添加电子签名
        SignPdfUtils signPdfUtils = new SignPdfUtils();
        signPdfUtils.signPdf(desc,SRC);
    }

    public void fillAndSign(RentContarctVo rentContarctVo,String desc){
        File file = new File(SRC);
        fill(rentContarctVo,file);
        sign(desc);
        file.delete();

    }



}
