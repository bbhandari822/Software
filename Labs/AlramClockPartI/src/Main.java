/**
 * Created by Binod Bhandari on 10/10/17.
 */
public class Main{
    public static void main(String[] args) {
        AlarmClock myClock = new AlarmClock(8, 0, 0, "AM", 8, 5,  0,"AM");
        AlarmClockAndRadio radio = new AlarmClockAndRadio(1060, "AM");
        System.out.print("Initial Time: " + myClock.initialTime() + " The radio was turned on and is playing "  + radio.getRadioStation() + " " + radio.getFreqency());
        for (int i = 0; i < 5; i++) {
            System.out.print(" " + myClock.showTime());
            for (int seconds = 0; seconds < 60; seconds++) {
                myClock.tick();
                myClock.checkAlarm();
            }
        }

        myClock.snooze();
        for (int i = 0; i < 9; i++) {
            System.out.print(myClock.showTime() + " ");
            for (int seconds = 0; seconds < 60; seconds++) {
                myClock.checkAlarm();
                myClock.tick();
            }
        }
        System.out.println();
        myClock.alarmOff();
    }

}