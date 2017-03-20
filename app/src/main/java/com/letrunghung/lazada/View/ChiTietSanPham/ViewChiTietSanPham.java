package com.letrunghung.lazada.View.ChiTietSanPham;

import com.letrunghung.lazada.Model.ObjectClass.DanhGia;
import com.letrunghung.lazada.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by TED on 20/03/2017.
 */

public interface ViewChiTietSanPham {
    void HienChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGiohangThatBai();
}
