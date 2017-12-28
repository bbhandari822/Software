import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/19/17.
 */
public class NewJerseyTaxTest {
    @Test
    public void calculateTax() throws Exception {

        NewJerseyTax newJerseyTax = new NewJerseyTax();
        newJerseyTax.taxValue = 8;

        assertEquals(newJerseyTax.calculateTax(200),16);
    }

    @Test
    public void applyTaxToPurchase() throws Exception {

        NewJerseyTax newJerseyTax = new NewJerseyTax();
        newJerseyTax.taxValue = 8;

        assertEquals(newJerseyTax.applyTaxToPurchase(200),216);
    }

}