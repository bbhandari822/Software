import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

public class Response implements Serializable {

    protected String[] response;

    public String[] getResponse() {
        return response;
    }

    public void setResponse(String[] s) {

        response = s;
    }
    public boolean compare(Response res) {
        Arrays.sort(this.response);
        String[] responseB = res.getResponse();
        Arrays.sort(responseB);
        for (int i = 0; i < this.response.length; i++)
        {
            if (!(this.response[i].toLowerCase().equals(responseB[i].toLowerCase()))) {
                return false;
            }
        }
        return true;
    }

    public void displayResponse() {
        for (String aResponse : response) {
            System.out.println(aResponse);
        }
    }

}
