package com.example.ng.foodsh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class UpdateDiaDiemActivity extends AppCompatActivity {
    EditText btendd,blienhe,bgiohoatdong,bdiachi,bgiacaonhat,bgiathapnhat;
    Button btao,bcancel;
    int id = 0;
    String urlu = "https://thanhdang.000webhostapp.com/Ltud2/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dia_diem);
        Intent intent = getIntent();
        DiaDiem diaDiem = (DiaDiem) intent.getSerializableExtra("dataDiadiem");

        anhxa();

        id=diaDiem.getId_dd();

//        Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        btendd.setText(diaDiem.getTendd());
        blienhe.setText(diaDiem.getLienhe());
        bgiohoatdong.setText(diaDiem.getGiohoatdong());
        bdiachi.setText(diaDiem.getDiachi());
        bgiacaonhat.setText(String.valueOf(diaDiem.getGiacaonhat()));
        bgiathapnhat.setText(String.valueOf(diaDiem.getGiathapnhat()));
        final Context ctx=this;
        btao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhapDiadiem(urlu);
            }
        });

        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ctx, Home.class);
                startActivity(i);
            }
        });

   }
   private void CapNhapDiadiem(String url){
       final RequestQueue requestQueue = Volley.newRequestQueue(this);
       StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(UpdateDiaDiemActivity.this,"Cập nhập thành công!",Toast.LENGTH_LONG ).show();
                            startActivity(new Intent(UpdateDiaDiemActivity.this, Home.class));
                        }else {
                            Toast.makeText(UpdateDiaDiemActivity.this, "Lỗi Cập nhập", Toast.LENGTH_SHORT).show();
                        }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(UpdateDiaDiemActivity.this, "Xảy ra lỗi, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                   }
               }
       ){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("id_dd",String.valueOf(id));
               params.put("tendiadiem",btendd.getText().toString().trim());
               params.put("lienhe",blienhe.getText().toString().trim());
               params.put("giohoatdong",bgiohoatdong.getText().toString().trim());
               params.put("diachi",bdiachi.getText().toString().trim());
               params.put("giacaonhat",bgiacaonhat.getText().toString().trim());
               params.put("giathapnhat",bgiathapnhat.getText().toString().trim());

               return params;
           }
       };
       requestQueue.add(stringRequest);
   }
  private void anhxa(){
        btendd = (EditText)findViewById(R.id.et_tendd);
        blienhe = (EditText)findViewById(R.id.et_Lienhe);bgiohoatdong = (EditText)findViewById(R.id.et_giohoatdong);
  bdiachi = (EditText)findViewById(R.id.et_diachi);
        bgiacaonhat = (EditText)findViewById(R.id.et_giacaonhat);
        bgiathapnhat = (EditText)findViewById(R.id.et_giathapnha);

        btao = (Button)findViewById(R.id.btn_taodd);
        bcancel = (Button)findViewById(R.id.btn_cancel);
       }
}
