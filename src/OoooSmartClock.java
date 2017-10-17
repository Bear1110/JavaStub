import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Stub.OoooSetTimeZoneDialog;

class OoooSmartClock {
    private int timeZone = 0;

    public void setTimeZone() {
         // 當這個method 被呼叫的時候會有一個 dialog 跳出來
         // 讓使用者選一個時區。這個 dialog 由你的同事來實作
         // 大致上是讓使用者從成千上百的城市選取，但是你的同事
         // 要負責幫你轉換成標準時區
         // 事先約定好的呼叫的方式如下:
         //    int zoneindex = new OoooSetTimeZoneDialog() 
         // 全球的 time zone 共被劃分成 24個時區，所以正常
         // 結果 OoooSetTimeZoneDialog() 會回傳 0-23
         // 0 代表換日線的最開始時區
         // 如果使用者沒有選擇，則會有個 exception NODATA 從
         // OoooSetTimeZoneDialog() 丟出來
        try{
            //int zoneindex = new OoooSetTimeZoneDialog();
            //int zoneindex = 13-x;
        }catch (Exception ex){
            
        }
    }
    
    public void setTimeZone(int index) {
       // nothing to explain, a simple setter
        timeZone  = index;
    }
    public String getCurrentTimeStamp() {
       // 當這個 method 被呼叫的時候請回傳
       // YYYY-MM-DD HH:MI:Sec 形式的 format 
       // 例如 ”2009-09-22 16:47:08”
       // (HINT:use “Date” and “SimpleDateFormat” from Java
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date current = new Date();
        sdFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(sdFormat.format(current));
        return sdFormat.format(current);
      }
      
    
    public String getLocalCurrentTimeStamp() {
        // 這個 method 被呼叫的時候會回傳比較人性的字串
        // 1. 當時間介於 12:00:00-12:01:00 的時候會回傳
        //   “2009-09-22 NOON”
        // 2. 當時間介於 00:00:00-00:01:00 的時候會回傳
        //   “2009-09-22 MIDNIGHT
        // 3. 當日期是國慶日的時候回傳
        //    “2009-10-10 DOUBLE-TEN 16:37:08”
        // 4. 當日期是父親節的時候回傳
        //    “2009-08-08 FATHER’S DAY 16:37:08”
        // 5. 當日期是聖誕節時回傳
        //    “2009-12-25 X’MAS 16:37:08
        // 以上日期的變化與時間會互相結合
        // 所以 “2009-12-25 X’MAS NOON” 是正確的字串如果時間
        // 落在以上的條件的綜合
        SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        String time =" "+checkTimeForSpecial(current);
        String SpecialDate = checkDateForSpecial(current);
        
        System.out.println(sdate.format(current)+SpecialDate+time);
        return sdate.format(current)+SpecialDate+time;
    }
    
    private String checkTimeForSpecial(Date current){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
        String addition = parser.format(current);
        try {
            Date userDate = parser.parse(parser.format(current));
            if( userDate.after(parser.parse("11:59:59")) && userDate.before(parser.parse("12:01:01")) ){
                addition = "NOON";
            }else if (userDate.after(parser.parse("23:59:59")) && userDate.before(parser.parse("00:01:01"))){
                addition = "MIDNIGHT";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return addition;
    }
    private String checkDateForSpecial(Date current){
        SimpleDateFormat parser = new SimpleDateFormat("MM-dd");
        String addition = "";
        try {
            Date userDate = parser.parse(parser.format(current));
            if(userDate.compareTo(parser.parse("10-10"))==0){
                addition = " DOUBLE-TEN";
            }else if (userDate.compareTo(parser.parse("12-25"))==0){
                addition = " X’MAS";
            }else if (userDate.compareTo(parser.parse("08-08"))==0){
                addition = " FATHER’S DAY";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return addition;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new OoooSmartClock().getCurrentTimeStamp();
        new OoooSmartClock().getLocalCurrentTimeStamp();
    }
}

