package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;
import Entities.Interfaces.ISelection;
import Entities.Regular.RegularCart;
import UseCases.IFactory;

import java.util.HashMap;
import java.util.List;

public class CartFactory implements IFactory<ICart> {
    public ICart get(String type){
        HashMap<IFood, List<ISelection>> contents = new HashMap<>();
        switch (type){
            // Switch used in case many new types of carts are introduced.
            case "Regular":
                return new RegularCart(contents);
        }
        return null;
    }
}
