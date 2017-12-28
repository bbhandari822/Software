/**
 * Created by Binod Bhandari on 11/19/17.
 */
public class MarylandTax implements ITax{
    long taxValue = 10;

    @Override
    public long calculateTax(long purchasePrice) {
        return purchasePrice * this.taxValue / 100;
    }

    @Override
    public long applyTaxToPurchase(long purchasePrice) {
        return purchasePrice + calculateTax(purchasePrice);
    }
}
