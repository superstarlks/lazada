package com.letrunghung.lazada.View.TrangChu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letrunghung.lazada.Presenter.TrangChu.PresenterDownloadLogic;
import com.letrunghung.lazada.R;

public class TrangChuActivity extends AppCompatActivity implements ViewDownloadImp, View.OnClickListener {

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
        String duongdan = "http://192.168.0.25:8181/weblazada/loaisanpham.php";
        PresenterDownloadLogic presenterDownloadLogic = new PresenterDownloadLogic(TrangChuActivity.this,duongdan,maloaicha);
        presenterDownloadLogic.downloaddulieu();


    }

    @Override
    public void downloadthanhcong(String dulieu) {
        Toast.makeText(this, dulieu, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void downloadthatbai(String dulieu) {
        Toast.makeText(this, dulieu, Toast.LENGTH_SHORT).show();
    }
}
