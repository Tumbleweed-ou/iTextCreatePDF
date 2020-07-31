package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ozw
 * @create 2020-07-21 14:22
 */
public class DateSwitchUtil {


    /**
     * 日期转换为大写
     * @param date
     * @return
     */
    public String DateUpper(Date date){

        LocalDateTime ldt = asLocalDate(date);
        String dateStr = ldt.toString();
        int T_Index = dateStr.indexOf("T");
        String cutStr = dateStr.substring(0,T_Index);
        String dateStrUpper = dataToUpper(cutStr);

        return dateStrUpper;
    }

    /**
     * 日期递增
     * @param date
     * @return
     */
    public String DateAdd(Date date){

        LocalDateTime localDateTime = asLocalDate(date);
        LocalDateTime AddDate = localDateTime.plusDays(30);
        String dateAddAfter = AddDate.toString();
        int T_Index = dateAddAfter.indexOf("T");
        String cutStr = dateAddAfter.substring(0,T_Index);
        String dateAddAfterUpper = dataToUpper(cutStr);

        return dateAddAfterUpper;
    }


    /**
     * 转换Date为LocalDateTime格式
     * @param date
     * @return
     */
    private static LocalDateTime asLocalDate(Date date){

        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return ldt;
    }

    /**
     * 转换小写格式yyyy-mm-dd
     * @param date
     * @return
     */
    public String DateLower(Date date){
        LocalDateTime ldt = asLocalDate(date);
        String dateStr = ldt.toString();
        int T_Index = dateStr.indexOf("T");
        String cutStr = dateStr.substring(0,T_Index);
        return cutStr;
    }



    // 日期转化为大小写
    private static String dataToUpper(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(dateTime);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";
    }

    // 将数字转化为大写（字体格式可自己定义）
    private static String numToUpper(int num) {
        //String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String u[] = {"〇","一","二","三","四","五","六","七","八","九"};
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }

    // 月转化为大写
    private static String monthToUppder(int month) {
        if(month < 10) {

            return numToUpper(month);
        } else if(month == 10){
            return "十";
        } else {
            return "十" + numToUpper(month - 10);
        }
    }

    // 日转化为大写
    private static String dayToUppder(int day) {
        if (day < 20) {
           monthToUppder(day);
            return  monthToUppder(day);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十";
            } else {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));
            }
        }

    }

}
