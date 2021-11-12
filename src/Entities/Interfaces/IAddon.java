package Entities.Interfaces;

public interface IAddon {
    public String getName();

    public String getDescription();

    public float getPrice();


    /* I am assuming that type will be something like "Pizza topping" or "Ice cream toppings", but this
     can be modified to be something like an integer that represents what type of topping it is.
     e.g. we can allocate 1 to be anything that can be on a pizza or 2 to be anything that can be added
     to a hot dog.

     I think the latter makes sense also because it allows us to be more fuzzy with the types e.g. 1 can represent
     all sorts of savory toppings or something.

     Here its an array because an ingredient may fall under multiple types. (Please change if you see fit.)

     TODO: What is the soloution?
    */

    public String[] getTypes();
}
