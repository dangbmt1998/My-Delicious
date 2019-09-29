package com.example.ng.foodsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AddDiadiemActivity extends AppCompatActivity {
    EditText atendd,alienhe,agiohoatdong,adiachi,agiacaonhat,agiathapnhat,achamdiem,abinhluan;
    Button atao,acancel;
    String id_user ;


    String urlInsert = "https://thanhdang.000webhostapp.com/Ltud2/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diadiem);
        anhxa();

        id_user = getIntent().getStringExtra("id_user");
        Toast.makeText(AddDiadiemActivity.this, id_user, Toast.LENGTH_SHORT).show();
        atao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themdiadiem(urlInsert);
            }
        });
        acancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void themdiadiem(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("successsuccess")){
                            Toast.makeText(AddDiadiemActivity.this, "Thêm Thành công", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddDiadiemActivity.this,Home.class));
                        }else {
                            Toast.makeText(AddDiadiemActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddDiadiemActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d( "AAA","Lỗi!\n" + error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("tendiadiem",atendd.getText().toString().trim());
                params.put("binhluan",abinhluan.getText().toString().trim());
                params.put("sosao",achamdiem.getText().toString().trim());
                params.put("diachi",adiachi.getText().toString().trim());
                params.put("giacaonhat",agiacaonhat.getText().toString().trim());
                params.put("giathapnhat",agiathapnhat.getText().toString().trim());
                params.put("giohoatdong",agiohoatdong.getText().toString().trim());
                params.put("lienhe",alienhe.getText().toString().trim());
                params.put("id_user",id_user.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void anhxa(){
        atendd = (EditText)findViewById(R.id.et_tendd);
        alienhe = (EditText)findViewById(R.id.et_Lienhe);
        agiohoatdong = (EditText)findViewById(R.id.et_giohoatdong);
        adiachi = (EditText)findViewById(R.id.et_diachi);
        agiacaonhat = (EditText)findViewById(R.id.et_giacaonhat);
        agiathapnhat = (EditText)findViewById(R.id.et_giathapnha);
        achamdiem = (EditText)findViewById(R.id.et_chamdiem);
        abinhluan = (EditText)findViewById(R.id.et_Binhluan);
        atao = (Button)findViewById(R.id.btn_taodd);
        acancel = (Button)findViewById(R.id.btn_cancel);
    }
}
