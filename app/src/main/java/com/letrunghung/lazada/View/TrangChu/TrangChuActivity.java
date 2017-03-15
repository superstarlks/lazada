package com.letrunghung.lazada.View.TrangChu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.letrunghung.lazada.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edMaLoaiCha;
    Button btnLayDuLieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        edMaLoaiCha = (EditText) findViewById(R.id.edMaLoaiCha);
        btnLayDuLieu = (Button) findViewById(R.id.btnLayDuLieu);
        btnLayDuLieu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String maloaicha = edMaLoaiCha.getText().toString(); //ko phan biet kieu du lieu nen string hay int deu dc, ko can parse
        //dia chi local genemotion: 10.0.3.2
        // may ao android: 127.0.0.1
        //test tren máy thật thì fai dùng chung 1 mạng thì mới connect dc. 192.168.0.25
        String duongdan = "http://192.168.0.25:8181/weblazada/loaisanpham.php?maloaicha=" + maloaicha;
        Log.d("kiemtra",duongdan);
        DownloadJSON downloadJSON = new DownloadJSON();
        downloadJSON.execute(duongdan);
    }

    //viet cứng, sau này tách ra mo hình mvc
    public class DownloadJSON extends AsyncTask<String , Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            //lay ra duong dan
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                Log.d("kiemtra","connect");
                //lay du lieu luong
                inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader); //bo nho tam duyet reader
                String line = "";
                StringBuilder dulieu = new StringBuilder();
                //duyet line tren bo nho tam de lay data
                while ((line = bufferedReader.readLine()) !=null){
                    dulieu.append(line);
                }
                data = dulieu.toString();
                Log.d("kiemtra", data);
                bufferedReader.close();
                reader.close();
                inputStream.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }

            return data;
        }
    }
}
