package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import entities.Shop;

import java.util.ArrayList;
import java.util.List;

public class RAMShopRepository implements Repository<Shop> {
    List<Shop> shops;
    public RAMShopRepository(Shop shop){
        List<Shop> shops = new ArrayList<>();
        shops.add(shop);
        this.shops = shops;
    }

    @Override
    public Shop read(String id) {
        for (Shop shop : shops){
            if (shop.getId().equals(id)){
                return shop;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Shop item) {
        for (Shop shop : shops){
            if (shop.getId().equals(id)){
                shops.add(item);
                shops.remove(shop);
                return true;
            }
        }
        return false;
    }

    @Override
    public String create(Shop item) {
        for (Shop shop : shops){
            if (item.getId().equals(shop.getId()))
                return "Shop already exists";
        }
        shops.add(item);
        return "Shop created";
    }

    @Override
    public List<Shop> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Shop findOneByFieldName(String fieldName, String needle) {
        for(Shop shop : shops){
            if(shop.getName().equals(needle)){
                return shop;
            }
        }
        return null;
    }
}
