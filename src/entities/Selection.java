package entities;

import java.util.HashMap;

/**
 * The type Selection.
 */
public class Selection {
    /**
     * The Singleton selection.
     */
    HashMap<Addon1, Integer> singletonSelection;

    /**
     * Gets singleton selection.
     *
     * @return the singleton selection
     */
    public HashMap<Addon1, Integer> getSingletonSelection() {
        return singletonSelection;
    }

    /**
     * Sets singleton selection.
     *
     * @param singletonSelection the singleton selection
     */
    public void setSingletonSelection(HashMap<Addon1, Integer> singletonSelection) {
        this.singletonSelection = singletonSelection;
    }
}
