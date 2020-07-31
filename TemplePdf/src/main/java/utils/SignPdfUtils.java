package utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;
import vo.SignatureVo;

import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author shkstart
 * @create 2020-07-29 15:44
 */
public class SignPdfUtils {

    public static final char[] PASSWORD = "111111".toCharArray();//keystory密码

    /**
     * 单多次签章通用
     * @param src
     * @param
     * @param
     * @throws
     * @throws IOException
     * @throws
     */
    private void sign(String dest, String src, SignatureVo...signatureVos){
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PdfStamper stamper = null;
        try {
           inputStream = new FileInputStream(src);
           for (SignatureVo signatureVo : signatureVos) {
               ByteArrayOutputStream tempArrayOutputStream = new ByteArrayOutputStream();
               PdfReader reader = new PdfReader(inputStream);
               //创建签章工具PdfStamper ，最后一个boolean参数是否允许被追加签名
               stamper = PdfStamper.createSignature(reader, tempArrayOutputStream, '\0', null, true);
               // 获取数字签章属性对象
               PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
               appearance.setReason(signatureVo.getReason());
               appearance.setLocation(signatureVo.getLocation());
              // appearance.setAcro6Layers();
               //设置签名的签名域名称，多次追加签名的时候，签名预名称不能一样，图片大小受表单域大小影响（过小导致压缩）
               //appearance.setVisibleSignature(signatureVo.getFieldName());
               //读取图章图片

               //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
               //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
               //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
               /*appearance.setVisibleSignature(new Rectangle(200, 130, 270, 200), 7, "d");
               Image image = Image.getInstance(signatureVo.getImagePath());
               appearance.setSignatureGraphic(image);*/
               appearance.setCertificationLevel(signatureVo.getCertificationLevel());
               //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
               appearance.setRenderingMode(signatureVo.getRenderingMode());
               // 摘要算法
               ExternalDigest digest = new BouncyCastleDigest();
               // 签名算法
               ExternalSignature signature = new PrivateKeySignature(signatureVo.getPk(), signatureVo.getDigestAlgorithm(), null);
               // 调用itext签名方法完成pdf签章
               MakeSignature.signDetached(appearance, digest, signature, signatureVo.getChain(), null, null, null, 0, signatureVo.getSubfilter());
               //定义输入流为生成的输出流内容，以完成多次签章的过程
               inputStream = new ByteArrayInputStream(tempArrayOutputStream.toByteArray());
               result = tempArrayOutputStream;
           }
            outputStream = new FileOutputStream(new File(dest));
            outputStream.write(result.toByteArray());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null!=outputStream){
                    outputStream.close();
                }
                if(null!=inputStream){
                    inputStream.close();
                }
                if(null!=result){
                    result.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void signPdf(String dest,String src) {
        try {
            SignPdfUtils app = new SignPdfUtils();
            //将证书文件放入指定路径，并读取keystore ，获得私钥和证书链
            String pkPath = app.getClass().getResource("/lib/server.p12").getPath();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(pkPath), PASSWORD);
            String alias = ks.aliases().nextElement();
            PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
            Certificate[] chain = ks.getCertificateChain(alias);
            //String src = app.getClass().getResource("/template/check.pdf").getPath();
            //封装签章信息
            SignatureVo info = new SignatureVo();
            info.setReason("理由");
            info.setLocation("位置");
            info.setPk(pk);
            info.setChain(chain);
            info.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            info.setDigestAlgorithm(DigestAlgorithms.SHA1);
            //info.setFieldName("ownerSignature");
            info.setImagePath(app.getClass().getResource("/img/onwerSignImg.JPG").getPath());
            info.setRenderingMode(PdfSignatureAppearance.RenderingMode.DESCRIPTION);

            /*SignatureInfo info1 = new SignatureInfo();
            info1.setReason("理由1");
            info1.setLocation("位置1");
            info1.setPk(pk);
            info1.setChain(chain);
            info1.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            info1.setDigestAlgorithm(DigestAlgorithms.SHA1);
            info1.setFieldName("sig2");
            info1.setImagePath(app.getClass().getResource("/template/yinzhang.png").getPath());
            info1.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);*/

            app.sign(dest,src,info);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
