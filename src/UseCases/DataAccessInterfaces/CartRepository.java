package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;

import java.util.HashMap;
import java.util.List;

public interface CartRepository {

    boolean createCart(String userId);

    ICart getCart(String cartId);

    boolean setCart(String cartId, ICart cart);

    boolean addToCart(String cartId, IFood item, List<HashMap<IAddon, Integer>> addons);

}
