package com.event.cs.csevent.callback;

import com.event.cs.csevent.model.ProductItem;

import java.util.ArrayList;

public interface ProductLoadCallback {
    void callback(ArrayList<ProductItem> productInfo);
}
