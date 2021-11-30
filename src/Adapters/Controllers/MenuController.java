package Adapters.Controllers;
import UseCases.Menu.createMenuInputBoundary;

public class MenuController {
    createMenuInputBoundary createMenuInputBoundary;

    public MenuController(createMenuInputBoundary createMenuInputBoundary){
        this.createMenuInputBoundary = createMenuInputBoundary;
    }

    public void runAddMenuItem(){

    }
}
