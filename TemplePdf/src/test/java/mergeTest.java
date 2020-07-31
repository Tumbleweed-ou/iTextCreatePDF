import utils.mergePdfUtil;

/**
 * @author shkstart
 * @create 2020-07-31 17:09
 */
public class mergeTest {



    public static void main(String[] args){
        mergePdfUtil pdfUtil = new mergePdfUtil();
        String[] files = {"F:\\ppd\\demo.pdf","F:\\ppd\\report.pdf"};
        String path1="F:\\ppd\\merge1.pdf";
        String path2="F:\\ppd\\merge2.pdf";
       // pdfUtil.mergePdfByCopy(files,path1);
        pdfUtil.mergePdfByWrite(files,path2);
    }


}
