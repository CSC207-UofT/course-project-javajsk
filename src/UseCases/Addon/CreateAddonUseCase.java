package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.RegularAddon;

import java.util.Scanner;
import java.util.UUID;



public class CreateAddonUseCase implements CreateAddonInputBoundary {
    public IAddon createAddon(String VendorToken) {
        Scanner AddonName = new Scanner(System.in);
        Scanner AddonDescr = new Scanner(System.in);
        Scanner AddonPrice = new Scanner(System.in);
        AddonFactory af = new AddonFactory();
        IVendor vendor = VendorRepository.getAuthenticationToken(VendorToken);
        if (vendor != null) {
            String AddonId = UUID.randomUUID().toString();
            System.out.println("Enter Addon name");
            String name = AddonName.nextLine();
            System.out.println("Enter Addon description");
            String description = AddonDescr.nextLine();
            System.out.println("Enter Addon Price");
            float price = AddonPrice.nextFloat();
            RegularAddon addon = new RegularAddon(name, AddonId, description, price);
            return AddonRepository.setAddon(AddonId, addon);
        }
        return null;
    }
}