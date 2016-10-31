package com.acer.acer.android_version;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Model> list_android;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adp_android;


    RequestQueue requestQueue;
    public static final String url = "https://api.learn2crack.com/android/jsonandroid/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list_android = new ArrayList<Model>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getData();
        adp_android = new Adapter_android(list_android, this);
        recyclerView.setAdapter(adp_android);
    }

    public void getData() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, "https://api.learn2crack.com/android/jsonandroid/",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray android_ver = response.getJSONArray("android");

                            for (int a = 0; a < android_ver.length(); a++) {
                                Model item_android = new Model();
                                JSONObject json = android_ver.getJSONObject(a);
                                item_android.setVersi(json.getString("ver"));
                                item_android.setNama(json.getString("name"));
                                item_android.setApi(json.getString("api"));

                                list_android.add(item_android);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("ini kesalahannya " + e.getMessage());
                        }
                        adp_android.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ini kesalahannya",error.toString());
                        System.out.println("ini kesalahannya " + error.getMessage());
                    }
                });

        requestQueue.add(jsonRequest);
    }
}