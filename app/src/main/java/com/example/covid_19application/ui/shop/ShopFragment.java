package com.example.covid_19application.ui.shop;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.covid_19application.R;
import com.google.gson.Gson;

public class ShopFragment extends Fragment {
    RecyclerView rv;
    MaskAdapter adapter=new MaskAdapter();
    GridLayoutManager gridLayoutManager;

    @Override
    public void onStart() {
        super.onStart();
        review();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        rv = root.findViewById(R.id.rv);
        Button btnInsert=root.findViewById(R.id.btnInsert);
//        gridLayoutManager=new GridLayoutManager(getContext(),2);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(getActivity(), MaskInsertActivity.class);
                startActivity(intent);
            }
        });

        adapter=new MaskAdapter();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnMaskItemClickListener() {
            @Override
            public void onItemClick(MaskAdapter.ViewHolder holder, View view, int position) {
                Mask mask= adapter.getItem(position);
                Intent intent = new Intent(getActivity(), MaskViewActivity.class);
                intent.putExtra("product",mask.getProduct());
                intent.putExtra("kind",mask.getKind());
                intent.putExtra("price",mask.getPrice());
                intent.putExtra("quantity",mask.getQuantity());
                startActivity(intent);
            }
        });

        return root;
    }
    private void processResponse(String response) {
        Gson gson = new Gson();
        Mask[] items = gson.fromJson(response, Mask[].class);

        for (int i = 0; i < items.length; i++) {
            //maskAdapeter.items.add(items[i]);
            adapter.addItem(items[i]);
        }
        Log.i("text",adapter.getItemCount()+"");
    }
    public void review(){

        String url="http://220.84.28.198:9080/shop/listBody";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                processResponse(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("text","error = "+error.toString());
            }

        });

        request.setShouldCache(false);

        MySingleTon.getInstance(getContext()).addToRequestQueue(request);
    }
}