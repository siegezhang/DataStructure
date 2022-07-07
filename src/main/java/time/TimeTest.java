package time;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

public class TimeTest {
  // https://stackoverflow.com/questions/61448647/time-valueof-method-returning-wrong-value
  @Test
  public void testTime() {
    System.out.println(TimeZone.getDefault());
    Time time1 = Time.valueOf("08:00" + ":00");
    System.out.println(time1);
    System.out.println(LocalTime.parse("09:00:00"));
    LocalTime time = LocalTime.parse("08:00:00");
    int a = time.get(ChronoField.MILLI_OF_DAY);
    long b = time1.getTime();
    System.out.println(a);
  }
}
