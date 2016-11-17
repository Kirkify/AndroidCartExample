package com.example.hsport.catalog;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by kdavies on 11/17/2016.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    private List<Product> products;

    public ProductListAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Product product = products.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(product.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(product.getPrice());
        TextView priceText = (TextView) convertView.findViewById(R.id.priceText);
        priceText.setText(price);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        imageView.setImageBitmap(bitmap);
        return convertView;
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getContext().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
