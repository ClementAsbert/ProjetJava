package test.java;
import main.java.Chambre;
import main.java.ChambreFactory;
import main.java.ConcreteChambreFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Objects;
@Ignore
public class ChambreFactoryTest {


    @Test
    public void testCreateChambreSimple() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.SIMPLE,1);
        Assert.assertEquals(50, chambre.getDetail().getValeur());
        Assert.assertEquals("Simple", chambre.getDetail().getLit());
        Assert.assertEquals("Normale", chambre.getDetail().getType());
        //Assert.assertNotNull(chambre.getReservations());
    }

    @Test
    public void testCreateChambreDouble() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.DOUBLE,11);
        Assert.assertEquals(70, chambre.getDetail().getValeur());
        Assert.assertEquals("Double", chambre.getDetail().getLit());
        Assert.assertEquals("Normale", chambre.getDetail().getType());
        //Assert.assertNotNull(chambre.getReservations());
    }
    @Test
    public void testCreateChambreDoubleLuxe() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.LUXDOUBLE,11);
        Assert.assertEquals(170, chambre.getDetail().getValeur());
        Assert.assertEquals("Double", chambre.getDetail().getLit());
        Assert.assertEquals("Luxe", chambre.getDetail().getType());
       // Assert.assertNotNull(chambre.getReservations());
    }
    @Test
    public void testCreateChambreSimpleLuxe() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.LUXESIMPLE,11);
        Assert.assertEquals(150, chambre.getDetail().getValeur());
        Assert.assertEquals("Simple", chambre.getDetail().getLit());
        Assert.assertEquals("Luxe", chambre.getDetail().getType());
       // Assert.assertNotNull(chambre.getReservations());
    }

    @Test(expected = Throwable.class)
    public void testCreateChambreError() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        factory.build(5,11);
    }

}
