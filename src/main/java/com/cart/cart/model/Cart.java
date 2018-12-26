package com.cart.cart.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private HashMap<Long, Item> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Cart(HashMap<Long, Item> cartItems) {
        this.cartItems = cartItems;
    }

    public HashMap<Long, Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Long, Item> cartItems) {
        this.cartItems = cartItems;
    }

    public void doCart(Long key, Item item) {
        if (cartItems.get(key) != null) {
            int quantity_old = cartItems.get(key).getQuantity();
            item.setQuantity(quantity_old + item.getQuantity());
            cartItems.put(item.getProduct().getId(), item);
        } else {
            cartItems.put(item.getProduct().getId(), item);
        }
    }

    public void updateCart(Item item, int value) {
        item.setQuantity(value);
        cartItems.put(item.getProduct().getId(), item);
    }

    public void removeToCart(Long product) {
        boolean bln = cartItems.containsKey(product);
        if (bln) {
            cartItems.remove(product);
        }
    }

    public int countItem() {
        int count = 0;
        for (Item c : cartItems.values()) {
            count += c.getQuantity();
        }
        return count;
    }

    public double total() {
        int count = 0;
        for (Map.Entry<Long, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }
        return count;
    }
    public void change(Long key, int number){
        cartItems.get(key).setQuantity(cartItems.get(key).getQuantity() + number);
    }
}
