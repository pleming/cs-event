package com.event.cs.csevent.util;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;

import org.json.JSONObject;

import java.util.Map;

public class AjaxManager {
    private static final String apiHost = "http://45.77.14.234:8080/api";
    private static AQuery aq;

    public static void init(Context context) {
        aq = new AQuery(context);
    }

    public static void ajax(String apiPath, Map<String, Object> params, AjaxCallback<JSONObject> callback) {
        if (params == null)
            aq.ajax(apiHost + apiPath, JSONObject.class, callback);
        else
            aq.ajax(apiHost + apiPath, params, JSONObject.class, callback);
    }
}
