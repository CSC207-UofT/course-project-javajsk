package UseCases;
import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;
import Entities.Menu;

import java.util.HashMap;

public class MenuInteractor {

    public Menu createMenu(HashMap<IFood, ISingleton> menu){
        return new Menu(menu);
    }

    public boolean deleteMenu(){
        //TODO: Implement after we figure out the database stuff
        return false;
    }

    public void readMenu(Menu menu){
        //TODO: Need a nice pretty way to display items in menu.
        System.out.println(menu);
    }

    public void updateMenu(Menu menu, HashMap<IFood, ISingleton> new_menu){
        menu.setMenu(new_menu);
    }
}
