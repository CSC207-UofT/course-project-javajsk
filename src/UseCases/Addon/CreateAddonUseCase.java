package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.RegularAddon;


public class CreateAddonUseCase implements CreateAddonInputBoundary {
    @Override
    public IAddon createAddon(String name, String id, String description, float price) {
        return new RegularAddon(name, id, description, price);

    }
}