import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Stub.OoooSetTimeZoneDialog;

public class OoooSmartClockTest {
    OoooSmartClock oSmartClock;
    SimpleDateFormat parser;
    Date testDate;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {
        oSmartClock = new OoooSmartClock(new OoooSetTimeZoneDialog());
        parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @After
    public void tearDown() throws Exception {
        oSmartClock = null;
        parser = null;
    }

    @Test
    public void checkTimeForSpecialTest() {  
        String specialTime = "";
        try {
            /*---------------------------NOON-------------------------------*/
            testDate = parser.parse("2017-01-01 12:00:00");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("NOON",specialTime);
            
            testDate = parser.parse("2017-01-01 12:01:00");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("NOON",specialTime);
            
            testDate = parser.parse("2017-01-01 11:59:59");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("11:59:59",specialTime);
            
            testDate = parser.parse("2017-01-01 12:01:01");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("12:01:01",specialTime);
            /*------------------------MIDNIGHT-------------------------------*/
            testDate = parser.parse("2017-01-01 00:00:00");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("MIDNIGHT",specialTime);
            
            testDate = parser.parse("2017-01-01 00:01:00");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("MIDNIGHT",specialTime);
            
            testDate = parser.parse("2017-01-01 23:59:59");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("23:59:59",specialTime);
            
            testDate = parser.parse("2017-01-01 00:01:01");
            specialTime = oSmartClock.checkTimeForSpecial(testDate);
            assertEquals("00:01:01",specialTime);
            
        } catch (ParseException e) {
            e.printStackTrace();
            fail("ParseException");
        }
        
        
    }
    @Test
    public void checkDateForSpecialTest() {
        String specialDate = "";
        try {
            testDate = parser.parse("2017-10-10 13:13:13");
            specialDate = oSmartClock.checkDateForSpecial(testDate);
            assertEquals(" DOUBLE-TEN",specialDate);
            
            testDate = parser.parse("2017-12-25 13:13:13");
            specialDate = oSmartClock.checkDateForSpecial(testDate);
            assertEquals(" X’MAS",specialDate);
            
            testDate = parser.parse("2017-08-08 13:13:13");
            specialDate = oSmartClock.checkDateForSpecial(testDate);
            assertEquals(" FATHER’S DAY",specialDate);
            
            testDate = parser.parse("2017-08-09 13:13:13");
            specialDate = oSmartClock.checkDateForSpecial(testDate);
            assertEquals("",specialDate);
        } catch (ParseException e) {
            e.printStackTrace();
            fail("ParseException");
        }
    }
    @Test
    public void setTimeZoneTest() {
        for(int i = -2 ; i < 26 ; i++){
            int should = i < 0 || i > 23 ? oSmartClock.getTimeZone() : i; 
            oSmartClock.setTimeZone(i);
            assertEquals(should,oSmartClock.getTimeZone());
        }        
    }

}
