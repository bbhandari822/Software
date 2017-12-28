import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/16/17.
 */
public class BillPackTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestImproprer() throws Exception{

        thrown.expect(IllegalArgumentException.class);

        BillPack billPack = new BillPack();
        long a = billPack.bills[0];
        long b = billPack.bills[1];
        long c = billPack.bills[2];
        long d = billPack.bills[3];
        long e = billPack.bills[4];
        long f = billPack.bills[5];
        new BillPack(a,b,c,d,e,f);

        long g = -1;
        new BillPack(a,b,c,d,e,g);
        throw new IllegalArgumentException();



    }

    @Test
    public void ones() throws Exception {
        BillPack billPack = new BillPack();
        assertEquals(billPack.bills[0],0);
        long a = 1;
        assertEquals(billPack.ones(a),true);

    }

    @Test
    public void fives() throws Exception {
        BillPack billPack = new BillPack();
        long a = 5;
        assertEquals(billPack.fives(a),true);

    }

    @Test
    public void tens() throws Exception {
        BillPack billPack = new BillPack();
        long a = 10;
        assertEquals(billPack.tens(a),true);
    }

    @Test
    public void twenties() throws Exception {

        BillPack billPack = new BillPack();
        long a = 20;
        assertEquals(billPack.twenties(a),true);
    }

    @Test
    public void fifties() throws Exception {
        BillPack billPack = new BillPack();
        long a = 50;
        assertEquals(billPack.fifties(a),true);
    }

    @Test
    public void hundreds() throws Exception {
        BillPack billPack = new BillPack();
        long a = 100;
        assertEquals(billPack.hundreds(a),true);
    }

    @Test
    public void ones1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[0] = 1;
        assertEquals(billPack.ones(),1);
    }

    @Test
    public void fives1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[1] = 5;
        assertEquals(billPack.fives(),5);
    }

    @Test
    public void tens1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[2] = 10;
        assertEquals(billPack.tens(),10);

    }

    @Test
    public void twenties1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[3] = 20;
        assertEquals(billPack.twenties(),20);
    }

    @Test
    public void fifties1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[4] = 50;
        assertEquals(billPack.fifties(),50);
    }


    @Test
    public void hundreds1() throws Exception {
        BillPack billPack = new BillPack();
        billPack.bills[5] = 100;
        assertEquals(billPack.hundreds(),100);
    }

}