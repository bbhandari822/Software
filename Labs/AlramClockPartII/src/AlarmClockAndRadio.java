/**
 * Created by Binod Bhandari on 10/10/17.
 */

/**
 * Alarm Clock sets the radio station and can change the type of radio frequency. Also deals with radio on off status.
 */

public class AlarmClockAndRadio implements RadioInterface{

    private int currentRadioStation;
    private String frequencyRad;
    private String checkStatus;


    public AlarmClockAndRadio(int currentRadioStation, String frequencyRad, String checkStatus) {
        currentRadioStation = setRadioStation(1060);
        frequencyRad = setFreqency("AM");
        checkStatus = setRadioStatus("ON"); //This is where radio can be switch on and off. It will
                                            // change the console reader.
    }


    public int setRadioStation(int r) {
        currentRadioStation = r;
        return r;
    }


    public int getRadioStation() {
        return currentRadioStation;
    }

    public String setFreqency(String  frequencySetting) {
        frequencyRad = frequencySetting;
        return frequencySetting;
    }

    public String getFreqency() {
        return frequencyRad;
    }

    public String getStatus() {
        return checkStatus;
    }

    public String setRadioStatus(String status) {
        checkStatus = status;
        return status;
    }
    public String printMessage(){
        return "Hello";
    }
}
