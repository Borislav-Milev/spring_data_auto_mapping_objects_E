package map.app.service.contract;

import map.app.domain.dto.UserDto;

public interface ShoppingCart {
    void setAnUser(UserDto userDto);

    void addAnItemToCart(String title);

    void removeItemFromCart(String title);

    void buyItemsFromCart();

    void clearShoppingCart();
}
