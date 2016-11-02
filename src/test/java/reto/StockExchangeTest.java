package reto;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by rk on 02.11.16.
 */
public class StockExchangeTest {
    @Test
    public void TestBuyWithRealObjects_HasDependencies_() {
        // keine Constructor Injection, es werden in StockExchange die defaults, also die realen Klassen verwendet
        StockExchange stex = new StockExchange("GOOGLE");
        stex.buy();
        Assert.assertTrue(true);
    }

    @Test
    public void TestBuyWithFakeObjects__() {
        FakeDatabase db = new FakeDatabase();
        FakeWebService ws = new FakeWebService();
        // constructor injection mit 2 Fake-Objekten (Subklassen)
        StockExchange stex = new StockExchange(db,ws,"GOOGLE");
        stex.buy();
        Assert.assertTrue(true);
    }

    // Mockito, NO ARTIfiCiAL FAKE OBJECTS
    @Test
    public void TestBuyWithMockito__() {

        WebService ws = Mockito.mock(WebService.class);
        when(ws.getCurrentPrice()).thenReturn(200.00);
        // der clou: ws.getCurrentPrice wird dann erst innerhalb von stex.buy() aufgerufen
        // wir koennen es aber von hier "aussen" mitgeben!

        Database db = Mockito.mock(Database.class);
        // db hat nur eine methode mit void, somit koennen wir hier kein thenReturn verwenden
        // dafuer pruefen wir am schluss, ob die db.save() einmal aufgerufen wurde

        // constructor injection mit 2 Mockito Objekten
        StockExchange stex = new StockExchange(db,ws,"GOOGLE");

        stex.buy();

        // hier noch pruefen ob die db.save() methode 1x aufgerufen wurde (innerhalb von stex.buy(), das sehen wir ja hier aussen nicht)
        // hierbei muessen die parameter natuerlich passen, also nicht 201.00
        Mockito.verify(db, times(1)).save("GOOGLE",200.00);

    }
}