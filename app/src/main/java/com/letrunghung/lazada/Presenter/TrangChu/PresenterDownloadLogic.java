package com.letrunghung.lazada.Presenter.TrangChu;

import com.letrunghung.lazada.Model.TrangChu.DownloadDuLieu;
import com.letrunghung.lazada.View.TrangChu.ViewDownloadImp;

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
        DownloadDuLieu downloadDuLieu = new DownloadDuLieu();
        downloadDuLieu.execute(duongdan,maloaicha);
        try {
            String dulieu = downloadDuLieu.get();
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
        }
    }
}
