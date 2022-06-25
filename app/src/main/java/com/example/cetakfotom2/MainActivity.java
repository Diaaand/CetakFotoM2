package com.example.cetakfotom2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements CartListener{

    private RecyclerView rvPhotoCatalogue;
    private PhotoCatalogueListAdapter adapter;
    private Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoCatalogueUti.init();
        PhotoOrderUti.init();

        rvPhotoCatalogue = findViewById(R.id.rv_photo_cat);
        adapter = new PhotoCatalogueListAdapter(this);

        rvPhotoCatalogue.setAdapter(adapter);
        rvPhotoCatalogue.setLayoutManager(new LinearLayoutManager(this));

        btnCart = findViewById(R.id.btn_cart);
        orderChange();

        btnCart.setOnClickListener(view -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
        PhotoOrderUti.addcListeners(this);
    }

    @Override
    public void orderChange() {
        String cCountStr = "Cart: " + PhotoOrderUti.getOrderCount();
        btnCart.setText(cCountStr);
    }
}