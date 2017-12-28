import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/17/17.
 */
public class DrawerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void checkConsturctor() throws Exception{

        Drawer drawer = new Drawer(1,1,10,2,5,1,0,5,2,1);

        assertEquals(drawer.penny(),1);
        assertEquals(drawer.nickle(),1);
        assertEquals(drawer.dime(),10);
        assertEquals(drawer.quarter(),2);
        assertEquals(drawer.one(),5);
        assertEquals(drawer.five(),1);
        assertEquals(drawer.ten(),0);
        assertEquals(drawer.twenty(),5);
        assertEquals(drawer.fifty(),2);
        assertEquals(drawer.hundred(),1);


    }

    @Test
    public void drawerTotalInCents() throws Exception {

        CoinPack coinPack = new CoinPack(0,1,0,0);
        BillPack billPack = new BillPack(1,0,0,0,0,0);

        Drawer drawer= new Drawer(coinPack,billPack);
        assertEquals(drawer.drawerTotalInCents(),105);


    }

    @Test
    public void penny() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        Drawer drawer= new Drawer();
        drawer.depositChange(-3,0,0,1);
        assertEquals(drawer.drawerTotalInCents(),22);
        throw new IllegalArgumentException();
    }

//    @Test
//    public void nickle() throws Exception {
//    }
//
//    @Test
//    public void dime() throws Exception {
//    }
//
//    @Test
//    public void quarter() throws Exception {
//    }
//
    @Test
    public void one() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        Drawer drawer= new Drawer();
        drawer.depositBills(-3,0,1,0,1,0);
        assertEquals(drawer.drawerTotalInCents(),700);
        throw new IllegalArgumentException();
    }
//
//    @Test
//    public void five() throws Exception {
//    }
//
//    @Test
//    public void ten() throws Exception {
//    }
//
//    @Test
//    public void twenty() throws Exception {
//    }
//
//    @Test
//    public void fifty() throws Exception {
//    }
//
//    @Test
//    public void hundred() throws Exception {
//    }

    @Test
    public void coinPack() throws Exception {

        CoinPack coinPack = new CoinPack(0,5,0,0);
        BillPack billPack = new BillPack(0,0,0,1,0,1);
        Drawer drawer = new Drawer(coinPack,billPack);
        assertEquals(drawer.coinPack(), coinPack);
        assertEquals(drawer.coinPack().nickles(),5);
        assertEquals(drawer.billPack().twenties(),1);


    }

    @Test
    public void billPack() throws Exception {
        CoinPack coinPack = new CoinPack(0,5,0,0);
        BillPack billPack = new BillPack(0,0,0,1,5,1);
        Drawer drawer = new Drawer(coinPack,billPack);

    }

    @Test
    public void depositChange() throws Exception {

        CoinPack coinPack = new CoinPack(1,5,10,1);
        BillPack billPack = new BillPack(0,0,0,1,5,1);

        Drawer drawer = new Drawer();
        drawer.depositChange(coinPack);
        assertEquals(drawer.drawerTotalInCents(),151);


    }

    @Test
    public void depositChange1() throws Exception {

        Drawer drawer = new Drawer();
        drawer.depositChange(0,0,1,1);
        assertEquals(drawer.drawerTotalInCents(),35);
    }

    @Test
    public void depositBills() throws Exception {

        BillPack billPack = new BillPack(1,1,1,1,1,1);

        Drawer drawer = new Drawer();
        drawer.depositBills(billPack);
        assertEquals(drawer.drawerTotalInCents(),18600);
    }

    @Test
    public void depositBills1() throws Exception {

        Drawer drawer = new Drawer();
        drawer.depositBills(1,1,1,0,1,1);
        assertEquals(drawer.drawerTotalInCents(),16600);

    }

    @Test
    public void removeChange() throws Exception {

        Drawer drawer = new Drawer();
        drawer.depositChange(1,1,1,1);
        drawer.removeChange(0,2,1,1);
        assertEquals(drawer.drawerTotalInCents(),41);

    }

    @Test
    public void removeChange1() throws Exception {

        Drawer drawer = new Drawer();
        CoinPack coinPack = new CoinPack(0,0,1,1);
        drawer.depositChange(1,1,1,1);

        drawer.removeChange(coinPack);
        assertEquals(drawer.drawerTotalInCents(),6);

    }

    @Test
    public void removeBills() throws Exception {

        Drawer drawer = new Drawer();
        BillPack billPack  = new BillPack(0,0,1,1,1,1);
        drawer.depositBills(1,1,1,1,1,1);

        drawer.removeBills(0,0,1,1,1,1);
        assertEquals(drawer.drawerTotalInCents(),600);

    }

    @Test
    public void removeBills1() throws Exception {

        Drawer drawer = new Drawer();
        BillPack billPack  = new BillPack(2,0,1,1,1,1);
        drawer.depositBills(1,0,1,1,1,1);
        drawer.removeBills(billPack);
        assertEquals(drawer.drawerTotalInCents(),18100);
    }

    @Test
    public void centValueFromCoins() throws Exception {

        Drawer drawer = new Drawer();
        drawer.depositChange(1,1,1,0);
        CoinPack coinPack = new CoinPack(1,0,1,0);
        drawer.removeChange(coinPack);
        assertEquals(drawer.drawerTotalInCents(),5);

//        Drawer drawer1 = new Drawer();
//        drawer1.depositChange(1,0,0,0);
//        assertEquals(drawer1.penny(),1);

    }

    @Test
    public void centValueFromCoins1() throws Exception {
    }

    @Test
    public void centValueFromBills() throws Exception {

        Drawer drawer = new Drawer();
        drawer.depositBills(1, 1, 1, 0, 0, 0);
        BillPack billPack = new BillPack(0, 0, 1, 0, 0, 0);
        drawer.removeBills(billPack);
        assertEquals(drawer.drawerTotalInCents(), 600);
    }

//    @Test
//    public void centValueFromBills1() throws Exception {
//    }

}