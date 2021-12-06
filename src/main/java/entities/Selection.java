package entities;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Set;

/**
 * The type Selection.
 */
public class Selection {
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

    public Set<Addon> getSelectedAddons() {
        return this.singletonSelection.keySet();
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        HashMap<Addon, Integer> singletonSelection = this.singletonSelection;
        for (Addon addon : this.singletonSelection.keySet()) {
            jsonObject.put(addon.getId(), singletonSelection.get(addon));
        }
        return jsonObject.toString();
    }

}
