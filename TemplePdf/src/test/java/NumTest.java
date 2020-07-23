import utils.NumUpperUtils;

import java.math.BigDecimal;

/**
 * @author shkstart
 * @create 2020-07-21 17:39
 */
public class NumTest {

    public static void main(String[] args) {

        NumUpperUtils numUpperUtils = new NumUpperUtils();

        /**************** 测试金额人民币大写转换begin ******************************/
       // numUpperUtils.numUpper("15866.36");
        BigDecimal num = BigDecimal.valueOf(5000);
        System.out.println(numUpperUtils.numUpper(String.valueOf(num)));
        /**************** 测试金额人民币大写转换end *********************************/

    }
}
