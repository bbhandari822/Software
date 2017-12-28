import java.sql.Time;

/**
 * Created by Binod Bhandari on 10/10/17.
 */

public interface ClockInterface {

    void setTime(int hours, int min, int sec, String timeConvention);
    String initialTime();
    String showTime();

}
