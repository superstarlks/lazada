package com.letrunghung.lazada.Presenter.TrangChu;

import android.util.Log;

import com.letrunghung.lazada.Model.TrangChu.DownloadDuLieu;
import com.letrunghung.lazada.View.TrangChu.ViewDownloadImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by TED on 15/03/2017.
 */

public class PresenterDownloadLogic implements PresenterDownloadImp {
    ViewDownloadImp viewDownloadImp;
    String duongdan ="";
    String maloaicha ="";
    public PresenterDownloadLogic(ViewDownloadImp viewDownloadImp, String duongdan, String maloaicha){
        this.viewDownloadImp = viewDownloadImp;
        this.duongdan = duongdan;
        this.maloaicha = maloaicha;
    }

    @Override
    public void downloaddulieu() {
        DownloadDuLieu TaiDuLieu = new DownloadDuLieu();
        TaiDuLieu.execute(duongdan,maloaicha);

        try {
            String dulieuPaser =TaiDuLieu.get();
            JSONObject jsonObject = new JSONObject(dulieuPaser);
            JSONArray jsLoaiSanPham = jsonObject.getJSONArray("LOAISANPHAM");
            for (int i=0 ; i<jsLoaiSanPham.length() ; i++)
            {
                JSONObject dulieu = jsLoaiSanPham.getJSONObject(i);
                String tensanpham = dulieu.getString("TENLOAISP");
                Log.d("JSON", tensanpham);
            }
            String dulieu = TaiDuLieu.get();
            if (dulieu==null || dulieu.equals("")){
                dulieu = "Kiểm tra lại kết nối";
                viewDownloadImp.downloadthatbai(dulieu);
            }else{
                viewDownloadImp.downloadthanhcong(dulieu);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
