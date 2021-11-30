package UseCases.Addon;

/**
 * Input boundary for DeleteAddonUseCase indicates parameters needed to delete an addon
 */
public interface DeleteAddonInputBoundary {
    /**
     * A method that deleted a given Addon
     * @param userToken token of current user
     * @param id id of addon to delete
     * @return whether addon was successfully deleted
     */
    boolean deleteAddon(String userToken, String id);

}
