import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Binod Bhandari on 11/16/17.
 */
public class CoinPackTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestImproprer() throws Exception{

        thrown.expect(IllegalArgumentException.class);
        CoinPack coinPack = new CoinPack();

        long a = coinPack.cents[0];
        long b = coinPack.cents[1];
        long c = coinPack.cents[2];
        long d = coinPack.cents[3];

        new CoinPack(a,b,c,d);

        long e = -1;
        new CoinPack(a,b,c,e);

        throw new IllegalArgumentException();



    }

    @Test
    public void pennies() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.pennies(2);
        assertEquals(coinPack.cents[0],2);
    }

    @Test
    public void nickles() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.nickles(21);
        assertEquals(coinPack.cents[1],21);
    }

    @Test
    public void dimes() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.dimes(10);
        assertEquals(coinPack.cents[2],10);
    }

    @Test
    public void quarters() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.quarters(1);
        assertEquals(coinPack.cents[3],1);
    }

    @Test
    public void pennies1() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.cents[0] = 1;
        assertEquals(coinPack.pennies(),1);
    }

    @Test
    public void nickles1() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.cents[1] = 5;
        assertEquals(coinPack.nickles(),5);
    }

    @Test
    public void dimes1() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.cents[2] = 10;
        assertEquals(coinPack.dimes(),10);
    }

    @Test
    public void quarters1() throws Exception {
        CoinPack coinPack = new CoinPack();
        coinPack.cents[3] = 25;
        assertEquals(coinPack.quarters(),25);
    }

}