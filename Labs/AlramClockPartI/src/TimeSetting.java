
/**
 * Created by Binod Bhandari on 10/10/17.
 */

/**
 * This class change the time setting, increase hour when min > 60 and resets minute back to zero and same
 * for second as well. Set time sets all the hour, min, and second.
 */

public class TimeSetting implements ClockInterface {

    private int hour;
    private int minute;
    private int second;
    private int vol = 3;
    private String timeConventionSetting;

    public void setTime(int hours, int min, int sec, String timeConvention ){
        hour = hours;
        minute = min;
        second = sec;
        timeConventionSetting = timeConvention;
    }

    public String initialTime(){
        return hour + ":" + minute + " "+ timeConventionSetting;
    }

    public void changeInitialTime(TimeSetting timeChange){
        hour = timeChange.hour;
        minute = timeChange.minute;
        second = timeChange.second;
        timeConventionSetting = timeChange.timeConventionSetting;
    }

    public void additionMin(int additionOfMin){
        minute += additionOfMin;
        if(minute >= 60){
            hour++;
            minute = 0;
        }
    }

    public void additionSec(int additionOfSecond){
        second += additionOfSecond;
        if(second >= 60){
            additionMin(1);
            second = 0;
        }
    }

    public String showTime(){
        return hour + ":" + String.valueOf(minute+1) + " " + timeConventionSetting;
    }


    public boolean sameTime(TimeSetting timeChange){
        if(hour == timeChange.hour && minute == timeChange.minute && second == timeChange.second && timeConventionSetting.equals(timeChange.timeConventionSetting)){
            return true;
        }
        return false;
    }

    public void volumeAddition() {
        if(vol > 3){
            vol += 1;
        }else{
            vol += 1;
            if (vol <= 0){
                vol = 0;
            }
        }
    }
    public int volume(){
        return vol;
    }
}
