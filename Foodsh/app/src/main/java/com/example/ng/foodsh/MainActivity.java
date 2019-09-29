package com.example.ng.foodsh;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    String Name, Password;
    Context ctx=this;
    String NAME=null, PASSWORD=null, EMAIL=null,ID_USER=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);
    }
    public void main_register(View v){
        startActivity(new Intent(this,Register.class));
    }
    public void main_login(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Name, Password);
    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("https://thanhdang.000webhostapp.com/Ltud2/login.php");
                String urlParams = "name="+name+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("User");
                NAME = user_data.getString("UserName");
                PASSWORD = user_data.getString("PassWord");
                EMAIL = user_data.getString("Email");
                ID_USER = user_data.getString("Id_User");

                Intent i = new Intent(ctx, Home.class);

                i.putExtra("UserName", NAME);
                i.putExtra("PassWord", PASSWORD);
                i.putExtra("Email", EMAIL);
                i.putExtra("Id_User",ID_USER);


                startActivity(i);


            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
                Toast.makeText(MainActivity.this,"Sai mật khẩu hoặc password",Toast.LENGTH_LONG).show();
            }
        }
    }
}
