package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Addon;
import entities.Interfaces.Cart;
import entities.Interfaces.Food;
import usecases.DataAccessInterfaces.CartRepository;

import java.util.HashMap;
import java.util.List;

public class CartDB implements CartRepository {
    @Override
    public boolean createCart(Cart cart) {
        return false;
    }

    @Override
    public Cart getCart(String cartId) {
        return null;
    }

    @Override
    public boolean save(Cart cart) {
        return false;
    }

    @Override
    public boolean addToCart(String cartId, Food item, List<HashMap<Addon, Integer>> addons) {
        return false;
    }
}
