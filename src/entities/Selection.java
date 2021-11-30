package entities;

import java.util.HashMap;

/**
 * The type Selection.
 */
public class Selection {
    /**
     * The Singleton selection.
     */
    HashMap<Addon, Integer> singletonSelection;

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
}
