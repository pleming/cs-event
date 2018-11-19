package com.event.cs.csevent.service;

import com.event.cs.csevent.adapter.ProductAdapter;
import com.event.cs.csevent.model.ProductItem;

import java.util.ArrayList;

public class ProductService {
    private ProductAdapter productAdapter;

    public void loadProduct(int csType, int eventType, ArrayList<ProductItem> productInfo) {
        // Load product information from DB.
        String csName = null;
        String eventTypeName = null;

        // Test
        if (csType == 1) {
            csName = "CU";
        } else if (csType == 2) {
            csName = "GS25";
        } else if (csType == 3) {
            csName = "7-ELEVEN";
        }

        if (eventType == 1) {
            eventTypeName = "1+1";
        } else if (eventType == 2) {
            eventTypeName = "2+1";
        } else if (eventType == 3) {
            eventTypeName = "3+1";
        }

        productInfo.add(new ProductItem(csName, new byte[16], "롯데)게토레이레몬캔240ml", eventTypeName, "12,345원"));
        productInfo.add(new ProductItem(csName, new byte[16], "케라시스)미니세트", eventTypeName, "45,678원"));
        productInfo.add(new ProductItem(csName, new byte[16], "해피바스로즈바디워시", eventTypeName, "98,765원"));
    }
}
