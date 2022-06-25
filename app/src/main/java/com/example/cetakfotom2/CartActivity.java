package com.example.cetakfotom2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class  CartActivity extends AppCompatActivity implements CartListener{

    private RecyclerView rvPhotoOrder;
    private PhotoOrderListAdapter adapter;
    private TextView tvTotal;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvPhotoOrder = findViewById(R.id.rv_photo_order);
        adapter = new PhotoOrderListAdapter(this);

        rvPhotoOrder.setAdapter(adapter);
        rvPhotoOrder.setLayoutManager(new LinearLayoutManager(this));

        PhotoOrderUti.addcListeners(this);

        tvTotal = findViewById(R.id.tv_total_price);
        tvEmpty = findViewById(R.id.tv_empty_cart);
        orderChange();
    }

    @Override
    public void orderChange() {
        if(PhotoOrderUti.getOrderCount() == 0) {
            rvPhotoOrder.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
        else {
            rvPhotoOrder.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }

        String totalStr = "Total Price : " + IdrFormatter.format(PhotoOrderUti.getTotalPrice());
        tvTotal.setText(totalStr);
    }
}