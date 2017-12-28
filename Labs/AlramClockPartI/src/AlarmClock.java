/**
 * Created by Binod Bhandari on 10/10/17.
 */

public class AlarmClock extends TimeSetting{

    private static TimeSetting initialTime = new TimeSetting();
    private static TimeSetting alarmStartTime = new TimeSetting();
    private static boolean status;
    private static TimeSetting vol = new TimeSetting();

    public AlarmClock(int hour, int min, int second, String timeConvention, int endHour, int endMin, int endSec, String endTimeConvention) {
        status = true;
        initialTime.setTime(hour, min, second, timeConvention);
        alarmStartTime.setTime(endHour, endMin, endSec, endTimeConvention);
    }

    public String initialTime() {
        return initialTime.initialTime();
    }

    public static boolean checkAlarm() {
        if (initialTime.sameTime(alarmStartTime)) {
            System.out.println();
            System.out.println("Buzz Buzz Buzz");
            status = false;
            return true;
        } else {
            return false;
        }
    }

    public static void snooze() {
        System.out.println("Snooze was pressed");
        status = true;
        initialTime.changeInitialTime(alarmStartTime);
        alarmStartTime.additionMin(9);
    }

    public void tick() {
        initialTime.additionSec(1);
    }

    public String showTime() {
        return initialTime.showTime();
    }

    public int volumeChange(){
        return vol.volume();
    }


    public static void alarmOff() {
        status = false;
        System.out.println("Buzz Buzz Buzz");
        System.out.println("The alarm was shut off");
    }

}
