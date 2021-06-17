package com.example.covid_19application.ui.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19application.R;

import java.util.HashMap;
import java.util.Map;

public class MaskInsertActivity extends AppCompatActivity {
    EditText edProduct, edKind, edPrice;
    Button btn;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_insert);
        setTitle("마스크 등록");
        edProduct=findViewById(R.id.edProduct);
        edKind=findViewById(R.id.edKind);
        edPrice=findViewById(R.id.edPrice);
        btn=findViewById(R.id.btnInsert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product=edProduct.getText().toString();
                String kind=edKind.getText().toString();
                String price=edPrice.getText().toString();
                if(product.isEmpty()){
                    edProduct.setError("이름을 입력하세요!");
                    edProduct.requestFocus();
                    return;
                }
                else if(kind.isEmpty()){
                    edKind.setError("종류를 입력하세요!");
                    edKind.requestFocus();
                    return;
                }
                else if(price.isEmpty()){
                    edPrice.setError("가격을 입력하세요!");
                    edPrice.requestFocus();
                    return;
                }
                String url="http://220.84.28.198:9080/shop/androidRegister";
                StringRequest request=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "마스크 등록 성공",Toast.LENGTH_LONG).show();
                                Bundle bundle=new Bundle();
                                bundle.putString("product",product);
                                bundle.putString("kind",kind);
                                bundle.putString("price",price);
                                Fragment fragment=new Fragment();
                                fragment.setArguments(bundle);
                                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.fragment_shop, fragment);
                                ft.commit();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("product",edProduct.getText().toString());
                        params.put("kind",edKind.getText().toString());
                        params.put("price",edPrice.getText().toString());
                        return params;
                    }
                };
                request.setShouldCache(false);
                requestQueue.add(request);
            }
        });
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }
}