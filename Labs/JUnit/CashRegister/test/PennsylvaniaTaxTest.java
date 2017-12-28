import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/18/17.
 */
public class PennsylvaniaTaxTest {
    @Test
    public void calculateTax() throws Exception {

        PennsylvaniaTax pennsylvaniaTax = new PennsylvaniaTax();
        pennsylvaniaTax.taxValue = 6;

        assertEquals(pennsylvaniaTax.calculateTax(200),12);
    }

    @Test
    public void applyTaxToPurchase() throws Exception {

        PennsylvaniaTax pennsylvaniaTax = new PennsylvaniaTax();
        pennsylvaniaTax.taxValue = 6;

        assertEquals(pennsylvaniaTax.applyTaxToPurchase(200),212);
    }

}