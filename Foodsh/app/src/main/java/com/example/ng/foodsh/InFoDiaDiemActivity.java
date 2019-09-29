package com.example.ng.foodsh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InFoDiaDiemActivity extends AppCompatActivity {
    EditText btendd,blienhe,bgiohoatdong,bdiachi,bgiacaonhat,bgiathapnhat;
    Button bcancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_fo_dia_diem);
        Intent intent = getIntent();
        DiaDiem diaDiem = (DiaDiem) intent.getSerializableExtra("dataDiadiem");

        anhxa();



//        Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        btendd.setText(diaDiem.getTendd());
        blienhe.setText(diaDiem.getLienhe());
        bgiohoatdong.setText(diaDiem.getGiohoatdong());
        bdiachi.setText(diaDiem.getDiachi());
        bgiacaonhat.setText(String.valueOf(diaDiem.getGiacaonhat()));
        bgiathapnhat.setText(String.valueOf(diaDiem.getGiathapnhat()));
        final Context ctx=this;
        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ctx, Home.class);
                startActivity(i);
            }
        });
    }
    private void anhxa(){
        btendd = (EditText)findViewById(R.id.et_tendd);
        blienhe = (EditText)findViewById(R.id.et_Lienhe);bgiohoatdong = (EditText)findViewById(R.id.et_giohoatdong);
        bdiachi = (EditText)findViewById(R.id.et_diachi);
        bgiacaonhat = (EditText)findViewById(R.id.et_giacaonhat);
        bgiathapnhat = (EditText)findViewById(R.id.et_giathapnha);


        bcancel = (Button)findViewById(R.id.btn_cancel);
    }
}
