package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;
import UseCases.DataAccessInterfaces.CartRepository;

import java.util.HashMap;
import java.util.List;

public class CartDB implements CartRepository {
    @Override
    public boolean createCart(ICart cart) {
        return false;
    }

    @Override
    public ICart getCart(String cartId) {
        return null;
    }

    @Override
    public boolean save(ICart cart) {
        return false;
    }

    @Override
    public boolean addToCart(String cartId, IFood item, List<HashMap<IAddon, Integer>> addons) {
        return false;
    }
}
