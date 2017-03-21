package com.letrunghung.lazada.View.ThanhToan;


import com.letrunghung.lazada.Model.ObjectClass.SanPham;

import java.util.List;


public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
