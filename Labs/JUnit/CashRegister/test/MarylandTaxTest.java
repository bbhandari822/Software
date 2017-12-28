import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/19/17.
 */
public class MarylandTaxTest {
    @Test
    public void calculateTax() throws Exception {

        MarylandTax marylandTax = new MarylandTax();
        marylandTax.taxValue = 10;

        assertEquals(marylandTax.calculateTax(200),20);
    }

    @Test
    public void applyTaxToPurchase() throws Exception {

        MarylandTax marylandTax = new MarylandTax();
        marylandTax.taxValue = 10;

        assertEquals(marylandTax.applyTaxToPurchase(200), 220);

    }
}