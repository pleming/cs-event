package com.event.cs.csevent.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.event.cs.csevent.callback.ProductLoadCallback;
import com.event.cs.csevent.model.ProductItem;
import com.event.cs.csevent.util.AjaxManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductService {
    private Context context;
    private int csType;
    private String searchTxt;

    public ProductService(Context context) {
        this.context = context;
    }

    public void loadProduct(int csType, int eventType, String searchTxt, int startIdx, int count, final ProductLoadCallback callback) {
        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put("csType", csType);
        param.put("eventType", eventType);
        param.put("searchTxt", searchTxt);
        param.put("startIdx", startIdx);
        param.put("count", count);

        AjaxManager.ajax("/loadProductInfo", param, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json == null) {
                    Toast.makeText(context, "상품 목록 불러오기를 실패하였습니다. 관리자에게 문의해주세요.", Toast.LENGTH_LONG).show();
                    Log.d("AJAX", "ProductService.loadProduct() Error.(" + status.getCode() + ")");
                    callback.callback(new ArrayList<ProductItem>());
                    return;
                }

                try {
                    JSONArray res = json.getJSONArray("contents");

                    ArrayList<ProductItem> productInfo = new ArrayList<ProductItem>();

                    for (int i = 0; i < res.length(); i++) {
                        JSONObject productJson = (JSONObject) res.get(i);
                        ProductItem productItem = new ProductItem();

                        productItem.setCsName(productJson.getString("csName"));
                        productItem.setEventType(productJson.getString("eventName"));
                        productItem.setProductName(productJson.getString("productName"));
                        productItem.setPrice(String.valueOf(productJson.getInt("price")));

                        Object imageJson = productJson.get("image");

                        if (imageJson.toString().equals("null"))
                            productItem.setProductImage(null);
                        else {
                            JSONArray imageJsonArr = productJson.getJSONObject("image").getJSONArray("data");
                            byte[] imageBytes = new byte[imageJsonArr.length()];

                            for (int j = 0; j < imageJsonArr.length(); j++)
                                imageBytes[j] = (byte) (((int) imageJsonArr.get(j)) & 0xFF);

                            productItem.setProductImage(imageBytes);
                        }

                        productInfo.add(productItem);
                    }

                    callback.callback(productInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getCsType() {
        return csType;
    }

    public void setCsType(int csType) {
        this.csType = csType;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
}
