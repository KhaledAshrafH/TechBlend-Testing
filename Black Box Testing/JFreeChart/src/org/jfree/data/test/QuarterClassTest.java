package org.jfree.data.test;

import org.jfree.data.Range;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.junit.Test;

import javax.management.DescriptorKey;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange() {
        quarter = new Quarter();
    }

    /*
    *
    * Test Constructor()
    *
    * */

    // public Quarter()
    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    // public Quarter(int quarter,int year)
    @Test
    public void testQuarterCtorWithTwoParamsInRange() {
        quarter = new Quarter(3, 7000);
        assertEquals(7000, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithTwoParamsEdgeCase1() {
        quarter = new Quarter(1, 1900);
        assertEquals(1900, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithTwoParamsEdgeCase2() {
        quarter = new Quarter(4, 9999);
        assertEquals(9999, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithTwoParamsIfQuarterLessThanOne() {
        /*
        * Expected IllegalArgumentException, Because Quarter Value less than 1 (not handled)
        * */
        quarter = new Quarter(0, 2000);
        assertEquals(2000, quarter.getYear().getYear());
        assertEquals(0, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithTwoParamsIfQuarterGreaterThanFour() {
        /*
         * Expected IllegalArgumentException, Because Quarter Value greater than 4 (not handled)
         * */
        quarter = new Quarter(6, 9000);
        assertEquals(9000, quarter.getYear().getYear());
        assertEquals(6, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithTwoParamsOutYearRange1() {
        /*
         * Miss-Information in Documentation with Expected IllegalArgumentException, Because Year Value less than 1900 (Not Clear)
         * */
        quarter = new Quarter(1, 1000);
        assertEquals(1000, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithTwoParamsOutYearRange2() {
        /*
         * Miss-Information in Documentation with Expected IllegalArgumentException, Because Year Value greater than 9999 (Not Clear)
         * */
        quarter = new Quarter(4, 10000);
        assertEquals(10000, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    // public Quarter(int quarter,Year year)
    @Test
    public void testQuarterCtorWithTwoParamsIncludesYearObject() {
        quarter = new Quarter(4, new Year(9000));
        assertEquals(9000, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    // public Quarter(java.util.Date time)
    @Test
    public void testQuarterCtorThatTakesDateObject() {
        /*
        * This Constructor that takes Date Object, didn't work correct
        * ex:- Expected :2024, Actual :3924
        * */
        quarter = new Quarter(new Date(2024, 4, 7));
        assertEquals(2024, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    //public Quarter(java.util.Date time,java.util.TimeZone zone)
    @Test
    public void testQuarterCtorThatTakesDateObjectAndTimezoneObject() {
        /*
         * This Constructor that takes Date Object and Timezone Object, didn't work correct
         * ex:- Expected :2021, Actual :3921
         * */
        quarter = new Quarter(new Date(2021, 4, 7), TimeZone.getTimeZone("UTC"));
        assertEquals(2021, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    // public int getQuarter()
    @Test
    public void testGetQuarterWithInRangeValue() {
        quarter = new Quarter(2, new Year(5000));
        assertEquals(2, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetQuarterWithOutOfRangeValue() {
        /*
         * Expected IllegalArgumentException, Because Quarter Value greater than 4
         * */
        quarter = new Quarter(6, new Year(5000));
        assertEquals(6, quarter.getQuarter());
    }

    @Test
    public void testGetQuarterWithSpecificDate() {
        quarter = new Quarter(new Date(2025, 8, 15));
        assertEquals(3, quarter.getQuarter());
    }

    // public Year getYear()
    @Test
    public void testGetYearWithInRangeValue() {
        quarter = new Quarter(2, new Year(5000));
        assertEquals(5000, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetYearWithOutOfRangeValue() {
        /*
         * Miss-Information in Documentation with Expected IllegalArgumentException Depend on constructor
         * */
        quarter = new Quarter(2, new Year(1000));
        assertEquals(1000, quarter.getYear().getYear());
    }

    @Test
    public void testGetYearWithSpecificDate() {
        /*
         * Get Year Method in case constructor takes date object, didn't work correct
         * ex:- Expected :2018, Actual :3918
         * */
        quarter = new Quarter(new Date(2018, 8, 15));
        assertEquals(2018, quarter.getYear().getYear());
    }

    // public RegularTimePeriod previous()
    @Test
    public void testPreviousWithAnyQuarterExceptOne() {
        quarter = new Quarter(3, 2555);
        assertEquals(quarter.previous(), new Quarter(2, 2555));
    }

    @Test
    public void testPreviousWithQuarterOneAnd1900() {
        quarter = new Quarter(1, 1900);
        assertNull(quarter.previous());
    }

    @Test
    public void testPreviousWithQuarterOneAndInRangeYear() {
        quarter = new Quarter(1, 2000);
        assertNotNull(quarter = (Quarter) quarter.previous());
        assertEquals(1999, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    // public RegularTimePeriod next()
    @Test
    public void testNextWithAnyQuarterExceptFour() {
        quarter = new Quarter(2, 2555);
        assertEquals(quarter.next(), new Quarter(3, 2555));
    }

    @Test
    public void testNextWithQuarterFourAnd9999() {
        quarter = new Quarter(4, 9999);
        assertNull(quarter.next());
    }

    @Test
    public void testNextWithQuarterFourAndInRangeYear() {
        quarter = new Quarter(4, 2000);
        assertNotNull(quarter = (Quarter) quarter.next());
        assertEquals(2001, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    // public long getSerialIndex()
    @Test
    public void testGetSerialIndex() {
        /*
         * Miss Information in getSerialIndex Description in the Documentation
         * */
        quarter = new Quarter(3, 2000);
        int year = ((quarter.getYear().getYear())*4)+ quarter.getQuarter();
        assertEquals("Miss Information in getSerialIndex Description in the Documentation", year,
                quarter.getSerialIndex());
    }

    // public boolean equals(java.lang.Object obj)
    @Test
    public void testEqualsWithCorrectCase() {
        Object obj;
        quarter = new Quarter(3, 2000);
        assertNotNull(obj = new Quarter(3, 2000));
        assertTrue(quarter.equals(obj));
    }

    @Test
    public void testEqualsWithInCorrectCase() {
        Object obj;
        quarter = new Quarter(3, 2000);
        assertNotNull(obj = new Quarter(5, 2005));
        assertFalse(quarter.equals(obj));
    }

    @Test
    public void testEqualsWithNullCase() {
        Object obj = null;
        quarter = new Quarter(3, 2000);
        assertFalse(quarter.equals(obj));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        Object obj = new Range(1, 6);
        quarter = new Quarter(3, 2000);
        assertFalse(quarter.equals(obj));
    }

    // public int compareTo(java.lang.Object o1)
    @Test
    public void testCompareToWithAfterCaseAndDifferentYear() {
        Object obj = new Quarter(2, 1989);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) > 0);
    }

    @Test
    public void testCompareToWithAfterCaseAndSameYear() {
        Object obj = new Quarter(1, 2000);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) > 0);
    }

    @Test
    public void testCompareToWithSameCase() {
        Object obj = new Quarter(3, 2000);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) == 0);
    }

    @Test
    public void testCompareToWithBeforeCaseAndDifferentYear() {
        Object obj = new Quarter(2, 2014);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) < 0);
    }

    @Test
    public void testCompareToWithBeforeCaseAndSameYear() {
        Object obj = new Quarter(4, 2000);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) < 0);
    }

    @Test
    public void testCompareToWithNullCase() {
        Object obj = null;
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) > 0);
    }

    @Test
    public void testCompareToWithDifferentObject() {
        /*
         * Miss-Information in Documentation in case compare with different object, which value is output (-1|1)?
         * */
        Object obj = new Range(1, 3);
        quarter = new Quarter(3, 2000);
        assertTrue(quarter.compareTo(obj) != 0);
    }

    // public java.lang.String toString()
    @Test
    public void testToStringWithCorrectOutput() {
        quarter = new Quarter(2, 2001);
        assertEquals("Q2/2001", quarter.toString());
    }

    @Test
    public void testToStringWithInCorrectOutput() {
        quarter = new Quarter(2, 2001);
        assertNotNull("2001/Q2", quarter.toString());
    }

    // public long getFirstMillisecond(java.util.Calendar calendar)
    @Test
    public void testGetFirstMillisecond() {
        /*
         * Miss-Information in Documentation for this method, you can provide more details for calculation for example
         * when using expected Calendar, this method doesn't work correctly
         * */
        quarter = new Quarter();
        Calendar calendar = Calendar.getInstance();

        Date expected = calendar.getTime();
        long actual = quarter.getFirstMillisecond(calendar);
        assertEquals(expected.getTime(), actual);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFirstMillisecondWithNullCase() {
        /*
         * Mis Information in the documentation to handle this case with NullPointerException
         */
        quarter = new Quarter();
        Calendar calendar = null;
        Date expected = calendar.getTime();
        long actual = quarter.getFirstMillisecond(calendar);
    }

    // public long getLastMillisecond(java.util.Calendar calendar)
    @Test
    public void testGetLastMillisecond() {
        /*
         * Miss-Information in Documentation for this method, you can provide more details for calculation for example
         * when using expected Calendar, this method doesn't work correctly
         * */
        quarter = new Quarter();
        Calendar calendar = Calendar.getInstance();

        Date expected = calendar.getTime();
        long actual = quarter.getLastMillisecond(calendar);
        assertEquals(expected.getTime(), actual);
    }

    @Test(expected = NullPointerException.class)
    public void testGetLastMillisecondWithNullCase() {
        /*
         * Mis Information in the documentation to handle this case with NullPointerException
         */
        quarter = new Quarter();
        Calendar calendar = null;
        Date expected = calendar.getTime();
        long actual = quarter.getLastMillisecond(calendar);
    }

    // public static Quarter parseQuarter(java.lang.String s)
    @Test
    public void testParseQuarterValidCaseWithFormat1() {
        /*
         * YYYY-QN
         */
        Quarter res = Quarter.parseQuarter("2025-Q3");
        assertEquals(2025, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat2() {
        /*
         * QN-YYYY
         */
        Quarter res = Quarter.parseQuarter("Q3-2025");
        assertEquals(2025, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat3() {
        /*
         * QN YYYY
         */
        Quarter res = Quarter.parseQuarter("Q4 2022");
        assertEquals(2022, res.getYear().getYear());
        assertEquals(4, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat4() {
        /*
         * YYYY QN
         */
        Quarter res = Quarter.parseQuarter("2028 Q1");
        assertEquals(2028, res.getYear().getYear());
        assertEquals(1, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat5() {
        /*
         * QN/YYYY
         */
        Quarter res = Quarter.parseQuarter("Q4/2022");
        assertEquals(2022, res.getYear().getYear());
        assertEquals(4, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat6() {
        /*
         * YYYY/QN
         */
        Quarter res = Quarter.parseQuarter("2028/Q1");
        assertEquals(2028, res.getYear().getYear());
        assertEquals(1, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat7() {
        /*
         * QN,YYYY
         */
        Quarter res = Quarter.parseQuarter("Q1,2027");
        assertEquals(2027, res.getYear().getYear());
        assertEquals(1, res.getQuarter());
    }

    @Test
    public void testParseQuarterValidCaseWithFormat8() {
        /*
         * YYYY,QN
         */
        Quarter res = Quarter.parseQuarter("2024,Q3");
        assertEquals(2024, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test(expected = org.jfree.data.time.TimePeriodFormatException.class)
    public void testParseQuarterInValidCase1() {
        /*
         * Missing Q Exception Handling Missing Information in the Documentation in case string doesn't contain Q ever and (handled)
         */
        Quarter res = Quarter.parseQuarter("2024,3");
        assertEquals(2024, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test(expected = org.jfree.data.time.TimePeriodFormatException.class)
    public void testParseQuarterInValidCase2() {
        /*
         * Missing Q Exception Missing in Documentation in case string does contain q not Q, and (handled)
         */
        Quarter res = Quarter.parseQuarter("2024,q3");
        assertEquals(2024, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test(expected = org.jfree.data.time.TimePeriodFormatException.class)
    public void testParseQuarterInValidCase3() {
        /*
         * Q found at end of string Exception Missing information in the Documentation, and (handled)
         */
        Quarter res = Quarter.parseQuarter("2024,Q");
        assertEquals(2024, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

    @Test(expected = org.jfree.data.time.TimePeriodFormatException.class)
    public void testParseQuarterInValidCase4() {
        /*
         * Q found at start of string and not follow with number - Exception Missing information in the Documentation, and (not handled)
         */
        Quarter res = Quarter.parseQuarter("Q,2024");
        assertEquals(2024, res.getYear().getYear());
        assertEquals(3, res.getQuarter());
    }

}
