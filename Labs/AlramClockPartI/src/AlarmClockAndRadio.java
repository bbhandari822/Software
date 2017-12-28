/**
 * Created by Binod Bhandari on 10/10/17.
 */

/**
 * Alarm Clock sets the radio station and can change the type of radio frequency. Also deals with radio on off status.
 */

public class AlarmClockAndRadio implements RadioInterface{

    private int currentRadioStation;
    private String frequencyRad;
    private boolean checkStatus;


    public AlarmClockAndRadio(int currentRadioStation, String frequencyRad) {
        currentRadioStation = setRadioStation(1060);
        frequencyRad = setFreqency("AM");
        checkStatus = getStatus();
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

    public boolean getStatus() {
        return checkStatus;
    }

    public void getStateOn() {
        this.checkStatus = true;
    }

    public void getStateOff() {
        this.checkStatus = false;
    }
}
