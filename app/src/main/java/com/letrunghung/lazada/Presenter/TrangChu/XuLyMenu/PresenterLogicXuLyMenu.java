package com.letrunghung.lazada.Presenter.TrangChu.XuLyMenu;

import com.facebook.AccessToken;
import com.letrunghung.lazada.ConnectInternet.DownloadJSON;
import com.letrunghung.lazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.letrunghung.lazada.Model.ObjectClass.LoaiSanPham;
import com.letrunghung.lazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.letrunghung.lazada.View.TrangChu.TrangChuActivity;
import com.letrunghung.lazada.View.TrangChu.ViewXuLyMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by TED on 17/03/2017.
 */

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;
    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        //Lấy bằng phương thức get
//        String duongdan = "http://192.168.1.9/weblazada/loaisanpham.php?maloaicha=0";

//        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        // end phương thức get

        //Lấy bằng phương thức post
        String duongdan = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String,String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha","0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken LayTokenDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();
        return accessToken;
    }
}
