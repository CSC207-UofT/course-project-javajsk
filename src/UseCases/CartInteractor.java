package UseCases;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;
import Entities.Interfaces.IShop;

import java.util.HashMap;
import java.util.List;

public class CartInteractor {
    protected ICart cart;
    protected IShop selected_shop;

    public CartInteractor(ICart cart, IShop shop){
        assert cart.isEmpty();
        this.cart = cart;
        this.selected_shop = shop;
    }

    public void addToCart(IFood item, List<HashMap<IAddon, Integer>> addons){
        if(this.selected_shop.isFoodAvailable(item) && item.isValidAddons(addons)){
            cart.addItem(item, addons);
        }
    }

    public boolean removeFromCart(IFood item, int index){
        if(cart.getQuantity(item) > index){
            cart.removeItem(item, index);
            return true;
        }
        return false;
    }

    public boolean setAddons(IFood item, int index, List<HashMap<IAddon, Integer>> addons){
        if(item.isValidAddons(addons) && cart.getQuantity(item) > index){
            cart.setAddons(item, index,addons);
            return true;
        }
        return false;
    }

    public void resetCart(ICart cart, IShop shop){
        assert cart.isEmpty();
        this.cart = cart;
        this.selected_shop = shop;
    }

    public ICart getCart() {
        return cart;
    }

}
