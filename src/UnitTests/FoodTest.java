package UnitTests;

import entities.Addon;
import entities.Food;
import entities.Singleton;
import org.junit.Test;

import java.security.cert.CertificateParsingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class FoodTest {
    Food set_burger_combo(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        type.add(2);
        ArrayList<Addon> default_type = new ArrayList<Addon>();
        Addon addon = new Addon("101", "Ketchup", 12,type, true);
        default_type.add(addon);
        Singleton burger = new Singleton("burger1", 15, "burger", "a regular burger", type, default_type, true);
        ArrayList<Singleton> components = new ArrayList<Singleton>();
        components.add(burger);
        float price = 0;
        for (Singleton component : components){
            price = price + component.getPrice();
        }
        return new Food("123", "burger combo", "A burger combo", price, set_burger_combo().getComponents() );

    }

    @Test
    public void getId() {
        Food burger = set_burger_combo();
        assertSame("123", burger.id);
    }

    @Test
    public void getName() {
        Food burger = set_burger_combo();
        assertSame("burger combo", burger.getName());
    }

    @Test
    public void getPrice() {
        Food burger = set_burger_combo();
        assertEquals(15, burger.getPrice(), 0.0);

    }

    @Test
    public void getComponents() {


    }
}