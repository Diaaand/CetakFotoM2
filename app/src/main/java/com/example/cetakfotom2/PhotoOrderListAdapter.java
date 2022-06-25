package com.example.cetakfotom2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.ConnectException;

public class PhotoOrderListAdapter extends RecyclerView.Adapter<PhotoOrderListAdapter.PhotoOrderViewHolder> {

    private LayoutInflater mInflater;
    public PhotoOrderListAdapter(Context context){

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PhotoOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  itemview = mInflater.inflate(R.layout.photo_order_item, parent, false);
        return new PhotoOrderViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoOrderViewHolder holder, int position) {
        PhotoOrder order = PhotoOrderUti.getOrderAt(position);
        PhotoCatalogue photoCatalogue = order.getPhotoCatalogue();

        holder.ivPhotoOrder.setImageResource(photoCatalogue.getResID());
        holder.tvFilenamePhotoOrder.setText(photoCatalogue.getFilename());
        holder.tvNumOrder.setText(order.getNumOrder() + "");
        holder.tvSize.setText(order.getSize());
        holder.tvSubTotal.setText(IdrFormatter.format(order.getSubtotalprice()));
    }

    @Override
    public int getItemCount() {
        return PhotoOrderUti.getOrderCount();
    }

    class PhotoOrderViewHolder extends RecyclerView.ViewHolder {
        final TextView tvFilenamePhotoOrder, tvSize, tvNumOrder, tvSubTotal;
        final Button btnDel, btnInc, btnDec;
        final ImageView ivPhotoOrder;

        PhotoOrderListAdapter mAdapter;

        public PhotoOrderViewHolder(@NonNull View itemView, PhotoOrderListAdapter _mAdapter) {
            super(itemView);
            mAdapter = _mAdapter;

            tvFilenamePhotoOrder = itemView.findViewById(R.id.tv_photo_filename);
            tvSize = itemView.findViewById(R.id.tv_photo_order_size);
            tvNumOrder = itemView.findViewById(R.id.tv_photo_num);
            tvSubTotal = itemView.findViewById(R.id.tv_sub_price);

            btnDel = itemView.findViewById(R.id.btn_del_order);
            btnDel.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                showDialogue(itemView.getContext(), tvFilenamePhotoOrder.getContext().toString(), itemPos);
            });

            btnInc = itemView.findViewById(R.id.btn_inc_order);
            btnInc.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                PhotoOrderUti.addOnetoOrder(itemPos);
                notifyItemChanged(itemPos);
            });

            btnDec = itemView.findViewById(R.id.btn_dec_order);
            btnDec.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                if(!PhotoOrderUti.minusOnetoOrder(itemPos))
                showDialogue(itemView.getContext(), tvFilenamePhotoOrder.getContext().toString(), itemPos);
                notifyItemChanged(itemPos);
            });;

            ivPhotoOrder = itemView.findViewById(R.id.iv_photo_order);
        }
    }

    private void showDialogue(Context context, String filename, int itemPos) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete Order");
        alertDialog.setMessage("Are you sure you want to delete order " + filename + "?");

        alertDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
            if(i==DialogInterface.BUTTON_POSITIVE) {
                PhotoOrderUti.removeOrderAt(itemPos);
                notifyItemRemoved(itemPos);
            }
        });
        alertDialog.setNegativeButton("No", null);
    }
}
