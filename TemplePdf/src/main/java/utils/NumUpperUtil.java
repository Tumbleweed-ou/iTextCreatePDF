package utils;

/**
 * @author shkstart
 * @create 2020-07-21 17:35
 */
public class NumUpperUtil {

    public String numUpper(String input){
        String s1 = "零壹贰叁肆伍陆柒捌玖";
        String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
        String temp = "";
        String result = "";
        if (input == null) {
            return "输入字串不是数字串只能包括以下字符（´0´～´9´，´.´)，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        temp = input.trim();
        try {
        } catch (Exception e) {
            return "输入字串不是数字串只能包括以下字符（´0´～´9´，´.´)，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        int len = 0;
        if (temp.indexOf(".") == -1) {
            len = temp.length();
        } else {
            len = temp.indexOf(".");
        }
        if (len > s4.length() - 3) {
            return ("输入字串最大只能精确到仟亿，小数点只能两位！");
        }
        int n1;
        String num = "";
        String unit = "";
        for (int i = 0; i < temp.length(); i++) {
            if (i > len + 2) {
                break;
            }
            if (i == len) {
                continue;
            }
            n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
            num = s1.substring(n1, n1 + 1);
            n1 = len - i + 2;
            unit = s4.substring(n1, n1 + 1);
            result = result.concat(num).concat(unit);
        }
        if ((len == temp.length()) || (len == temp.length() - 1)) {
            result = result.concat("整");
        }
        if (len == temp.length() - 2) {
            result = result.concat("零分");
        }
        //return result;
        return repace_acc(result);
    }

    // 金额小写变大写中小数位
    /**
     * @param money
     *            金额数值
     *
     */
    public static String repace_acc(String money) {
        String outmoney = "";
        money = money.replace("零分", "");
        money = money.replace("零角", "零");
        outmoney = money;
        int yy = 0;
        while (true) {
            int lett = outmoney.length();
            // 例如：0.35 零元三角五分 对于这种数据 不需要替换
            if (!outmoney.startsWith("零元")) {
                outmoney = outmoney.replace("零元", "元");
            }
            if (!outmoney.startsWith("零万")) {
                outmoney = outmoney.replace("零万", "万");
            }
            if (!outmoney.startsWith("零亿")) {
                outmoney = outmoney.replace("零亿", "亿");
            }
            if (!outmoney.startsWith("零仟")) {
                outmoney = outmoney.replace("零仟", "零");
            }
            if (!outmoney.startsWith("零佰")) {
                outmoney = outmoney.replace("零佰", "零");
            }
            if (!outmoney.startsWith("零零")) {
                outmoney = outmoney.replace("零零", "零");
            }
            if (!outmoney.startsWith("零拾")) {
                outmoney = outmoney.replace("零拾", "零");
            }
            if (!outmoney.startsWith("亿万")) {
                outmoney = outmoney.replace("亿万", "亿零");
            }
            if (!outmoney.startsWith("万仟")) {
                outmoney = outmoney.replace("万仟", "万零");
            }
            if (!outmoney.startsWith("仟佰")) {
                outmoney = outmoney.replace("仟佰", "仟零");
            }
            yy = outmoney.length();
            if (yy == lett) {
                break;
            }
        }
        yy = outmoney.length();
        if (outmoney.charAt(yy - 1) == '零') {
            outmoney = outmoney.substring(0, yy - 1);
        }
        yy = outmoney.length();
        if (outmoney.charAt(yy - 1) == '元') {
            outmoney = outmoney + '整';
        }
        //System.out.println(outmoney);
        return outmoney;
    }

}
