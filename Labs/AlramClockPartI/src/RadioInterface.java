/**
 * Created by Binod Bhandari on 10/11/17.
 */
public interface RadioInterface {
    int getRadioStation();
    int setRadioStation(int r);
    String getFreqency();
    String setFreqency(String  frequencySetting);
    boolean getStatus();
    void getStateOn();
    void getStateOff();
}
