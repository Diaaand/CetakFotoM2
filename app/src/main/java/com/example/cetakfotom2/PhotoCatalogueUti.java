package com.example.cetakfotom2;

import java.util.ArrayList;
import java.util.List;

public class PhotoCatalogueUti {
    private static int[] ridArray = {
            R.drawable.antiquehaven,
            R.drawable.atlantictide,
            R.drawable.azureaegis,
            R.drawable.brassecho,
            R.drawable.ceruleanaegis,
            R.drawable.kingsphoenix,
            R.drawable.ornate,
            R.drawable.pleasantnimbus,
            R.drawable.vision,
            R.drawable.winteruniverse,
    };

    private static String[] filenameArray = {
            "antiquehaven",
            "atlantictide",
            "azureaegis",
            "brassecho",
            "ceruleanaegis",
            "kingsphoenix",
            "ornate",
            "pleasantnimbus",
            "vision",
            "winteruniverse",
    };

    private static List<PhotoCatalogue> photoCatalogueList;

    public static void init() {
        photoCatalogueList = new ArrayList<>();
        int nArray = ridArray.length;

        for(int i=0; i<nArray; i++){
            photoCatalogueList.add(new PhotoCatalogue(ridArray[i], filenameArray[i]));
        }
    }

    public static List<PhotoCatalogue> getPhotoCatalogueList() {
        return photoCatalogueList;
    }

    public static PhotoCatalogue getPhotoCatalogueAt(int i){
        return photoCatalogueList.get(i);
    }
}
