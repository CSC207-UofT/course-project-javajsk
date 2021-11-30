package Adapters.Controllers;
import UseCases.Menu.addMenuItemInputBoundary;
import UseCases.Menu.createMenuInputBoundary;
import UseCases.Menu.editMenuItemInputBoundary;
import UseCases.Menu.removeMenuItemInputBoundary;

public class MenuController {
    addMenuItemInputBoundary addMenuItemInputBoundary;
    createMenuInputBoundary createMenuInputBoundary;
    editMenuItemInputBoundary editMenuItemInputBoundary;
    removeMenuItemInputBoundary removeMenuItemInputBoundary;

    public MenuController(addMenuItemInputBoundary addMenuItemInputBoundary,
                          createMenuInputBoundary createMenuInputBoundary,
                          editMenuItemInputBoundary editMenuItemInputBoundary,
                          removeMenuItemInputBoundary removeMenuItemInputBoundary){
        this.addMenuItemInputBoundary = addMenuItemInputBoundary;
        this.createMenuInputBoundary = createMenuInputBoundary;
        this.editMenuItemInputBoundary = editMenuItemInputBoundary;
        this.removeMenuItemInputBoundary = removeMenuItemInputBoundary;
    }

    public void runAddMenuItem(){

    }
}
