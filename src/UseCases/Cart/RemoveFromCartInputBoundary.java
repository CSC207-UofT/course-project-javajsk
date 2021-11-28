package UseCases.Cart;

public interface RemoveFromCartInputBoundary {
    boolean removeFromCart(String cartId, String foodId, int index, String token);
}
