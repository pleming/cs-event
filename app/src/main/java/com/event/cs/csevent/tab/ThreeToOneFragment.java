package com.event.cs.csevent.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.event.cs.csevent.R;
import com.event.cs.csevent.adapter.ProductAdapter;
import com.event.cs.csevent.callback.ProductLoadCallback;
import com.event.cs.csevent.model.ProductItem;
import com.event.cs.csevent.service.CustomProgressBar;
import com.event.cs.csevent.service.ProductService;
import com.event.cs.csevent.util.AjaxManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreeToOneFragment extends Fragment {
    private Context context;
    private ProductService productService;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<ProductItem> productInfo = new ArrayList<ProductItem>();
    private Activity mainActivity;
    private boolean lastItemVisibleFlag = false;
    private static int totalItemCount = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_three_to_one, container, false);

        GridView gridProduct = (GridView) linearLayout.findViewById(R.id.gridProduct);

        ProductAdapter productAdapter = new ProductAdapter(context);

        for (int i = 0; i < productInfo.size(); i++)
            productAdapter.addItem(productInfo.get(i));

        gridProduct.setAdapter(productAdapter);

        gridProduct.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag) {
                    CustomProgressBar.getInstance().progressON(mainActivity, "Loading...");

                    HashMap<String, Object> param = new HashMap<String, Object>();

                    param.put("csType", productService.getCsType());
                    param.put("eventType", 3);
                    param.put("searchTxt", productService.getSearchTxt());

                    AjaxManager.ajax("/loadCountProductInfo", param, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            if (json == null) {
                                Toast.makeText(context, "상품 목록 개수 불러오기를 실패하였습니다. 관리자에게 문의해주세요.", Toast.LENGTH_LONG).show();
                                Log.d("AJAX", "Fragment.loadCountProductInfo() Error.(" + status.getCode() + ")");
                                return;
                            }

                            try {
                                JSONObject res = json.getJSONObject("contents");
                                int count = res.getInt("count");

                                if (totalItemCount == count)
                                    return;

                                productService.loadProduct(productService.getCsType(), 3, productService.getSearchTxt(), totalItemCount, 10, new ProductLoadCallback() {
                                    @Override
                                    public void callback(ArrayList<ProductItem> _productInfo) {
                                        for (int i = 0; i < _productInfo.size(); i++)
                                            productInfo.add(_productInfo.get(i));

                                        mSectionsPagerAdapter.notifyDataSetChanged();
                                        CustomProgressBar.getInstance().progressOFF();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int _totalItemCount) {
                lastItemVisibleFlag = (_totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= _totalItemCount);

                if (lastItemVisibleFlag == true)
                    totalItemCount = _totalItemCount;
            }
        });

        return linearLayout;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setmSectionsPagerAdapter(SectionsPagerAdapter mSectionsPagerAdapter) {
        this.mSectionsPagerAdapter = mSectionsPagerAdapter;
    }

    public void setMainActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ArrayList<ProductItem> getProductInfo() {
        return this.productInfo;
    }
}
