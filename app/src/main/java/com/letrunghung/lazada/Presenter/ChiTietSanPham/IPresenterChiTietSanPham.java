package com.letrunghung.lazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.letrunghung.lazada.Model.ObjectClass.SanPham;

/**
 * Created by TED on 20/03/2017.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaTheoCuaSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
}
