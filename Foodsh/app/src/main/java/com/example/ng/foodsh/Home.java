package com.example.ng.foodsh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    String name, password, email,id, Err;
    TextView nameTV, emailTV, passwordTV,idTV, err;
    String urlGetdata = "https://thanhdang.000webhostapp.com/Ltud2/getdata.php";
    String urlDelete = "https://thanhdang.000webhostapp.com/Ltud2/delete.php";
    ListView lvdiadiem;
    ArrayList<DiaDiem> arrayDiaDiem;
    DiaDiemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        nameTV = (TextView) findViewById(R.id.home_name);
//        emailTV = (TextView) findViewById(R.id.home_email);
//        passwordTV = (TextView) findViewById(R.id.home_password);
//        idTV = (TextView)findViewById(R.id.home_id);
//        err = (TextView) findViewById(R.id.err);
//
        name = getIntent().getStringExtra("Id_User");



        Toast.makeText(Home.this, name, Toast.LENGTH_SHORT).show();
//        nameTV.setText("Welcome "+name);
//        passwordTV.setText("Your password is "+password);
//        emailTV.setText("Your email is "+email);
//        idTV.setText("id                                                "+id);
//        err.setText(Err);
        lvdiadiem = (ListView)findViewById(R.id.listViewDiadiem);
        arrayDiaDiem = new ArrayList<>();
        adapter = new DiaDiemAdapter(this, R.layout.dong_dia_diem,arrayDiaDiem);
        lvdiadiem.setAdapter(adapter);
        getData(urlGetdata);
    }

    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayDiaDiem.clear();
//                        Toast.makeText(MainActivity.this, response.toString(),Toast.LENGTH_SHORT).show();
                        for (int i = 0 ; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayDiaDiem.add(new DiaDiem(
                                        object.getInt("id_Dd"),
                                        object.getString("TenDd"),
                                        object.getString("LienHe"),
                                        object.getString("GioHoatDong"),
                                        object.getString("DiaChi"),
                                        object.getInt("GiaCaoNhat"),
                                        object.getInt("GiaThapNhat"),
                                        object.getInt("Id_Vitri"),
                                        object.getInt("Id_Us"),
                                        object.getInt("Id_DanhGia"),
                                        object.getString("image")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this, "Lỗi",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
     public void DeleteDiadiem(final int iddd){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                                Toast.makeText(Home.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            getData(urlGetdata);
                        }else {
                            Toast.makeText(Home.this, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_dd",String.valueOf(iddd));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public String id_user(){
        return name;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    Context ctx=this;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:
                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
            case R.id.menuAdd:
                Intent b = new Intent(ctx, AddDiadiemActivity.class);
                b.putExtra("id_user",name);
                startActivity(b);
                break;

        }
        return true;
    }
    public String getMyData() {
        return name;
    }
}
