package entities;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * The type Selection.
 */
public class Selection implements JSONable{
    /**
     * The Singleton selection.
     */
    HashMap<Addon, Integer> singletonSelection;

    /**
     * Instantiates a new Selection.
     *
     * @param singletonSelection the singleton selection
     */
    public Selection(HashMap<Addon, Integer> singletonSelection) {
        this.singletonSelection = singletonSelection;
    }

    /**
     * Gets singleton selection.
     *
     * @return the singleton selection
     */
    public HashMap<Addon, Integer> getSingletonSelection() {
        return singletonSelection;
    }

    /**
     * Sets singleton selection.
     *
     * @param singletonSelection the singleton selection
     */
    public void setSingletonSelection(HashMap<Addon, Integer> singletonSelection) {
        this.singletonSelection = singletonSelection;
    }


    @Override
    public JSONObject jsonify() {
        JSONObject rawData = new JSONObject();
        for(Addon addon: this.singletonSelection.keySet()){
            rawData.put(addon.getId(), this.singletonSelection.get(addon));
        }
        return rawData;
    }
}
