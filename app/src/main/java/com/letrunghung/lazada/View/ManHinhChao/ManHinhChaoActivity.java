package com.letrunghung.lazada.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.letrunghung.lazada.R;
import com.letrunghung.lazada.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // ngu 3s
                }catch (Exception e)
                {

                }finally {
                    Intent iTrangchu = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(iTrangchu);
                }
            }
        });
        thread.start();
    }
}
