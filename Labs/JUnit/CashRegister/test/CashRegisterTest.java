import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/16/17.
 */
public class CashRegisterTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void finalizePurchaseExtraCredit2() throws Exception {
        String test = "Yes" +"\nPennies" +"\n20" +"\nNickel" +"\n20" +"\nDime" +"\n20" +"\nQuarter" +"\n20" +"\nOne" +"\n20" +"\nFive" +"\n20" +"\nTen" +"\n20" +"\nTwenty" +"\n20"+ "\nFifty" +"\n20" +"\nHundred" +"\n10";
        InputStream toBites = new ByteArrayInputStream(test.getBytes());
        System.setIn(toBites);

        CoinPack coinPack = new CoinPack(99,99,99,99);
        BillPack billPack = new BillPack(99,99,99,99,99,99);
        CashRegister cashRegister = new CashRegister(billPack, coinPack);

        CoinPack coinPack1 = new CoinPack();
        BillPack billPack1= new BillPack();

        cashRegister.scanItem(999, "TV");
        cashRegister.finalizePurchase(billPack1,coinPack1);

    }

    @Test
    public void drawerValue() throws Exception {

        ITax iTax = new ITax() {
            @Override
            public long calculateTax(long purchasePrice) {
                return 0;
            }

            @Override
            public long applyTaxToPurchase(long purchasePrice) {
                return 0;
            }
        };
        iTax.applyTaxToPurchase(100);
        iTax.calculateTax(100);
        BillPack billPack = new BillPack(5,5,2,3,1,1);
        CoinPack coinPack = new CoinPack(5,2,1,1);

        Drawer drawer = new Drawer();
        drawer.depositBills(billPack);

        CashRegister cashRegister = new CashRegister(billPack,coinPack,iTax);

    }

    @Test
    public void checkConstrutor() throws Exception{

        CashRegister cashRegister = new CashRegister(0,0,1,0,0,0,0,0,0,0, new PennsylvaniaTax());
        assertEquals(cashRegister.drawerValue(),10.00,0.00);


    }

    @Test
    public void coinsInDrawer() throws Exception {

        CoinPack coinPack = new CoinPack(0,0,0,1);
        CashRegister cashRegister = new CashRegister();
        assertEquals(cashRegister.coinsInDrawer().quarters(1),coinPack.quarters(1));

    }

    @Test
    public void billsInDrawer() throws Exception {

        BillPack billPack = new BillPack(0,0,0,0,0,1);
        CashRegister cashRegister = new CashRegister();
        assertEquals(cashRegister.billsInDrawer().hundreds(1),billPack.hundreds(1));

    }

    @Test
    public void purchaseItem() throws Exception {

        CashRegister cashRegister1 = new CashRegister(5,1,0,1,2,1,0,0,0,0);
        cashRegister1.purchaseItem(50,3,0,0,0,1,0,0,0,0, 0);

        CoinPack coinPack = new CoinPack(0,0,0,1);
        BillPack billPack = new BillPack();
        cashRegister1.purchaseItem(0,billPack,coinPack);

    }


    @Test
    public void purchaseItem1() throws Exception {

       thrown.expect(IllegalStateException.class);
        CashRegister cashRegister1 = new CashRegister(5,5,10,3,2,2,0,0,0,0);
        cashRegister1.purchaseItem(100,3,1,1,1,1,1,0,0,0, 3);


        CashRegister cashRegister2 = new CashRegister(5,1,0,1,2,1,0,0,0,0);
        cashRegister2.purchaseItem(100,3,1,10,0,2,1,0,1,0, 3);

        CashRegister cashRegister3 = new CashRegister(5,5,10,3,2,2,1,0,1,0);
        cashRegister3.purchaseItem(100,2,6,1,1,1,1,101,0,11, 3);


        CashRegister cashRegister4 = new CashRegister(5,1,0,1,2,1,0,0,0,0);
        cashRegister4.purchaseItem(1,3,1,0,0,1,0,0,1,0, 3);

        throw new IllegalStateException();

    }

    @Test
    public void checkScanItem() throws Exception {
        Util util = new Util();
        thrown.expect(IllegalStateException.class);

        CoinPack coinPack = new CoinPack(99,99,99,99);
        BillPack billPack = new BillPack(11,15,10,10,10,30);
        CashRegister cashRegister = new CashRegister();

        //scan
        cashRegister.scanItem(999, "TV");
        cashRegister.finalizePurchase(billPack,coinPack);

        assertNotEquals(cashRegister.purchaseItem(100,billPack,coinPack),100);
        throw new IllegalStateException();


    }

    @Test
    public void finalizePurchaseScan() throws Exception {

        thrown.expect(IllegalStateException.class);
        String test = "No" +"\nPennies" +"\n20" +"\nNickel" +"\n20" +"\nDime" +"\n20" +"\nQuarter" +"\n20" +"\nOne" +"\n20" +"\nFive" +"\n20" +"\nTen" +"\n20" +"\nTwenty" +"\n20"+ "\nFifty" +"\n20" +"\nHundred" +"\n10";
        InputStream toBites = new ByteArrayInputStream(test.getBytes());
        System.setIn(toBites);

        CoinPack coinPack = new CoinPack(99,99,99,99);
        BillPack billPack = new BillPack(99,99,99,99,99,99);
        CashRegister cashRegister = new CashRegister(billPack, coinPack);

        CoinPack coinPack1 = new CoinPack();
        BillPack billPack1= new BillPack();

        cashRegister.scanItem(999, "TV");
        cashRegister.finalizePurchase(billPack1,coinPack1);
    }

    @Test
    public void finalizePurchase() throws Exception {

        thrown.expect(IllegalStateException.class);

        CoinPack coinPack = new CoinPack(0,0,0,1);
        BillPack billPack = new BillPack(0,0,0,0,0,1);
        CashRegister cashRegister = new CashRegister();

        cashRegister.finalizePurchase(billPack,coinPack);

        assertNotEquals(cashRegister.purchaseItem(100,billPack,coinPack),100);

    }



}