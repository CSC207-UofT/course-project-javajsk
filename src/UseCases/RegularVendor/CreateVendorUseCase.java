package UseCases.RegularVendor;

import Entities.FoodTruck;
import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.VendorModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateVendorUseCase implements CreateVendorInputBoundary{
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;
    VendorModel vendorModel;

    public CreateVendorUseCase(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public RegularVendor createVendor(String id, ArrayList<IShop> shops) {
        RegularVendor new_vendor = new RegularVendor(shops, id);
        vendorModel.displayVendor(new_vendor);
        vendorRepository.save(new_vendor);
        return new_vendor;
    }
}
