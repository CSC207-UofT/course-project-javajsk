package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import entities.Cart;
import entities.Cart;

import java.util.ArrayList;
import java.util.List;

public class RAMCartRepository implements Repository<Cart> {
    List<Cart> storage;
    public RAMCartRepository(){
        List<Cart> storage = new ArrayList<>();
        this.storage = storage;
    }

    @Override
    public Cart read(String id) {
        for (Cart cart : storage) {
            if (cart.getId().equals(id)) {
                return cart;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Cart item) {
        for (Cart cart : storage) {
            if (cart.getId().equals(id)) {
                storage.add(item);
                storage.remove(cart);
                return true;
            }
        }
        return false;

    }

    @Override
    public String create(Cart item) {
        for (Cart cart : storage) {
            if (item.getId().equals(cart.getId()))
                return "Cart already exists";
        }
        storage.add(item);
        return "Cart created";
    }

    @Override
    public List<Cart> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Cart findOneByFieldName(String fieldName, String needle) {
        for (Cart cart : storage) {
            if (cart.getId().equals(needle)) {
                return cart;
            }
        }
        return null;
    }
}
