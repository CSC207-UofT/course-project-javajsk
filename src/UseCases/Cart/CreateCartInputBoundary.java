package UseCases.Cart;

public interface CreateCartInputBoundary {
    boolean createCart(String userToken, String cartType);
}
