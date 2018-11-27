package com.event.cs.csevent.tab;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;

import com.event.cs.csevent.model.ProductItem;
import com.event.cs.csevent.service.ProductService;

import java.util.ArrayList;

public interface ICsFragment {
    void setContext(Context context);

    void setProductService(ProductService productService);

    void setmSectionsPagerAdapter(SectionsPagerAdapter mSectionsPagerAdapter);

    void setMainActivity(Activity mainActivity);

    ArrayList<ProductItem> getProductInfo();

    GridView getGridProduct();
}
