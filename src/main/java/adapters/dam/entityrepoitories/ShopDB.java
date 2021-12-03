package adapters.dam.entityrepoitories;

import businessrules.dai.Repository;
import entities.Shop;

import java.util.List;

public class ShopDB implements Repository<Shop> {
    @Override
    public Shop read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Shop item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String create(Shop item) {
        return null;
    }

    @Override
    public List<Shop> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Shop findOneByFieldName(String fieldName, String needle) {
        return null;
    }
}
