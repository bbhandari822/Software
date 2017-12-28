/**
 * Created by Binod Bhandari on 10/11/17.
 */
public interface RadioInterface {
    int getRadioStation();
    int setRadioStation(int r);
    String getFreqency();
    String setFreqency(String  frequencySetting);
    String getStatus();
    String  setRadioStatus(String status);
    String printMessage();
}
