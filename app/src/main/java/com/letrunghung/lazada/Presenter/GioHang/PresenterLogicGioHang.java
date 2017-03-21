package com.letrunghung.lazada.Presenter.GioHang;

import android.content.Context;

import com.letrunghung.lazada.Model.GioHang.ModelGioHang;
import com.letrunghung.lazada.Model.ObjectClass.SanPham;
import com.letrunghung.lazada.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by TED on 21/03/2017.
 */

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}