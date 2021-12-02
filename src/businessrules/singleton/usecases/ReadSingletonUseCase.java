package businessrules.singleton.usecases;

import businessrules.dai.SingletonRepository;
import businessrules.loaders.SingletonLoader;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.SingletonModel;
import businessrules.singleton.inputboundaries.ReadSingletonInputBoundary;
import entities.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadSingletonUseCase implements ReadSingletonInputBoundary {
    SingletonRepository singletonRepository;
    SingletonModel singletonModel;
    ErrorModel errorHandler;

    @Override
    public JSONObject readSingleton(String id) {

        JSONObject data = singletonRepository.readSingleton(id);

        Singleton singleton;
        try {
            singleton = SingletonLoader.loadSingleton(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }

        return singleton.jsonify();
    }
}
