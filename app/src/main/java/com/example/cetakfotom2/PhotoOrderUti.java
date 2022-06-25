package com.example.cetakfotom2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhotoOrderUti {
    private static List<CartListener> cListeners;

    private static List<PhotoOrder> photoOrderList;
    private static HashMap<String, Double> mapPrice;
    private static double totalPrice;

    public static void initmapPrice() {
        mapPrice = new HashMap<>();
        mapPrice.put("3R", 800.0);
        mapPrice.put("4R", 1000.0);
        mapPrice.put("8R", 2000.0);
        mapPrice.put("10R", 3000.0);
    }

    public static void init() {
        photoOrderList = new ArrayList<>();
        initmapPrice();
        cListeners = new ArrayList<>();
    }

    public static void addcListeners(CartListener listener) {
        cListeners.add(listener);
    }

    public static int getOrderCount() {
        return photoOrderList.size();
    }

    public static PhotoOrder getOrderAt(int i) {
        return photoOrderList.get(i);
    }

    private static void updateCart() {
        totalPrice = 0.0;

        for(PhotoOrder order:photoOrderList) {
            totalPrice += order.getSubtotalprice();
        }
        for(CartListener listener:cListeners){
            listener.orderChange();
        }
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    private static double getPrice(String size) {
        Double singleprice = mapPrice.get(size);

        if (singleprice == null) {
            singleprice = 0.0;
        }
        return singleprice;
    }

    public static void addOrder(PhotoCatalogue photo, String size) {
        photoOrderList.add(new PhotoOrder(photo, 1, size, getPrice(size)));
        updateCart();
    }

    public static void removeOrderAt(int i) {
        photoOrderList.remove(i);
        updateCart();
    }

    public static void addOnetoOrder(int i) {
       PhotoOrder order = photoOrderList.get(i);
       order.setNumOrder(order.getNumOrder() + 1);
       order.setSubtotalprice(order.getSubtotalprice() + getPrice(order.getSize()));
       updateCart();
    }

    public static boolean minusOnetoOrder(int i) {
        PhotoOrder order = photoOrderList.get(i);
        if (order.getNumOrder() == 1)
            return false;

        order.setNumOrder(order.getNumOrder() - 1);
        order.setSubtotalprice(order.getSubtotalprice() - getPrice(order.getSize()));
        updateCart();
        return true;
        }

}
