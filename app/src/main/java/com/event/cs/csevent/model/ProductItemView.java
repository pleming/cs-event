package com.event.cs.csevent.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.event.cs.csevent.R;

public class ProductItemView extends LinearLayout {
    private TextView txtCSName;
    private ImageView imgProduct;
    private TextView txtProductName;
    private TextView txtEventType;
    private TextView txtPrice;

    public ProductItemView(Context context) {
        super(context);
        init(context);
    }

    public ProductItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.product_item, this, true);

        txtCSName = (TextView) findViewById(R.id.txtCSName);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        txtProductName = (TextView) findViewById(R.id.txtProductName);
        txtEventType = (TextView) findViewById(R.id.txtEventType);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
    }

    public void setCsName(String csName) {
        this.txtCSName.setText(csName);
    }

    public void setProductImage(byte[] productImage) {
        if (productImage == null)
            imgProduct.setImageResource(R.drawable.no_image);
        else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
            imgProduct.setImageBitmap(bitmap);
        }
    }

    public void setProductName(String productName) {
        this.txtProductName.setText(productName);
    }

    public void setEventType(String eventType) {
        this.txtEventType.setText(eventType);
    }

    public void setPrice(String price) {
        this.txtPrice.setText(price);
    }
}
