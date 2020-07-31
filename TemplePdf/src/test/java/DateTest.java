import utils.DateSwitchUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author shkstart
 * @create 2020-07-21 15:35
 */
public class DateTest {


    public static void main(String[] args) throws ParseException {
        DateSwitchUtil d = new DateSwitchUtil();

        Date date = new Date();
        System.out.println(date);
        System.out.println(d.DateUpper(date));
        System.out.println(d.DateAdd(date));
        System.out.println(d.DateLower(date));


        String srt = "abcdsdf";
        String str = srt.substring(3);
        System.out.println(str);

        System.out.println(UUID.randomUUID().toString().replace("-","").substring(0,9).toUpperCase());

        Date Bigdate;
        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Bigdate = date1.parse("2030-09-30 12:00:00");
        System.out.println(Bigdate);
    }
}
