package com.example.cetakfotom2;

public class PhotoOrder {
    private PhotoCatalogue photoCatalogue;
    private String size;
    private int numOrder;
    private double subtotalprice;

    public PhotoOrder(PhotoCatalogue _photoCatalogue, int _numOrder, String _size, double _subtotalprice) {
        photoCatalogue = _photoCatalogue;
        setNumOrder(_numOrder);
        size = _size;
        setSubtotalprice(_subtotalprice);
    }

    public PhotoCatalogue getPhotoCatalogue() {
        return photoCatalogue;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getSubtotalprice() {
        return subtotalprice;
    }

    public void setSubtotalprice(double subtotalprice) {
        this.subtotalprice = subtotalprice;
    }
}
