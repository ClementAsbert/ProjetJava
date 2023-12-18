package test.java;
import main.java.Chambre;
import main.java.ChambreFactory;
import main.java.ConcreteChambreFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class ChambreFactoryTest {


    @Test
    public void testCreateChambreSimple() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.SIMPLE);
        Assert.assertEquals(50, chambre.getPrix());
        Assert.assertEquals("Simple", chambre.getLit());
        Assert.assertEquals("Normale", chambre.getType());
    }

    @Test
    public void testCreateChambreDouble() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.DOUBLE);
        Assert.assertEquals(70, chambre.getPrix());
        Assert.assertEquals("Double", chambre.getLit());
        Assert.assertEquals("Normale", chambre.getType());
    }
    @Test
    public void testCreateChambreDoubleLuxe() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.LUXDOUBLE);
        Assert.assertEquals(170, chambre.getPrix());
        Assert.assertEquals("Double", chambre.getLit());
        Assert.assertEquals("Luxe", chambre.getType());
    }
    @Test
    public void testCreateChambreSimpleLuxe() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(ChambreFactory.LUXESIMPLE);
        Assert.assertEquals(150, chambre.getPrix());
        Assert.assertEquals("Simple", chambre.getLit());
        Assert.assertEquals("Luxe", chambre.getType());
    }

    @Test(expected = Throwable.class)
    public void testCreateChambreError() throws Throwable {
        ChambreFactory factory = new ConcreteChambreFactory();
        Chambre chambre = factory.build(5);
    }

}
