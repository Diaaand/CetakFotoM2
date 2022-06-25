package com.example.cetakfotom2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoCatalogueListAdapter extends RecyclerView.Adapter<PhotoCatalogueListAdapter.PhotoCatalogueViewHolder> {

    private LayoutInflater mInflater;
    public PhotoCatalogueListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public PhotoCatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  itemview = mInflater.inflate(R.layout.photo_cat_item, parent, false);
        return new PhotoCatalogueViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoCatalogueViewHolder holder, int position) {
        PhotoCatalogue photoCatalogue = PhotoCatalogueUti.getPhotoCatalogueAt(position);

        holder.ivPhotoCatalogue.setImageResource(photoCatalogue.getResID());
        holder.tvFilenamePhotoCatalogue.setText(photoCatalogue.getFilename());
    }

    @Override
    public int getItemCount() {
        return PhotoCatalogueUti.getPhotoCatalogueList().size();
    }


    class PhotoCatalogueViewHolder extends RecyclerView.ViewHolder {
        final Button btn_print;
        final Button[] btn_sizeArray;
        final ImageView ivPhotoCatalogue;
        final TextView tvFilenamePhotoCatalogue;
        private PhotoCatalogueListAdapter mAdapter;

        private int[] resBtnSizeArray = {
                R.id.btn_size_3r,
                R.id.btn_size_4r,
                R.id.btn_size_8r,
                R.id.btn_size_10r,
        };

        public PhotoCatalogueViewHolder(@NonNull View itemView, PhotoCatalogueListAdapter _mAdapter) {
            super(itemView);
            mAdapter = _mAdapter;

            btn_print = itemView.findViewById(R.id.btn_print);
            ivPhotoCatalogue = itemView.findViewById(R.id.iv_photo_catalogue);
            tvFilenamePhotoCatalogue = itemView.findViewById(R.id.tv_filename_catalogue);

            btn_sizeArray = new Button[resBtnSizeArray.length];

            for(int i=0; i<resBtnSizeArray.length; i++) {
                btn_sizeArray[i] = itemView.findViewById(resBtnSizeArray[i]);

                btn_sizeArray[i].setOnClickListener(view -> {
                    Button btn = (Button) view;
                    int itemPos = getLayoutPosition();

                    PhotoOrderUti.addOrder(PhotoCatalogueUti.getPhotoCatalogueAt(itemPos), btn.getText().toString());

                });
            }
        }
    }
}
