package com.letrunghung.lazada.Model.TrangChu;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TED on 15/03/2017.
 */

public class DownloadDuLieu extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        //lay ra duong dan
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //neu du lieu hien thi dang json thi dùng
//            connection.setRequestProperty("Content-Type" , "text/json; charset=utf-8");
            //end json

            connection.setRequestMethod("POST");
            //mo output stream va nhan du lieu tra ve
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //json thi dung 2 dong nay và ko can uri builder
//            JSONObject jMaLoaiCha = new JSONObject();
//            jMaLoaiCha.put("maloaicha", strings[1]);
            //end
            Uri.Builder uri = new Uri.Builder();
            uri.appendQueryParameter("maloaicha", strings[1]) ; //post maloaicha o vi tri 1
            String dulieupost = uri.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);

            writer.write(dulieupost);
            writer.flush();
            writer.close(); //dong luong lai

            outputStreamWriter.close();
            outputStream.close();
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
