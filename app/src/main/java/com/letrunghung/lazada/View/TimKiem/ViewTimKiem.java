package com.letrunghung.lazada.View.TimKiem;

import com.letrunghung.lazada.Model.ObjectClass.SanPham;

import java.util.List;


public interface ViewTimKiem {
    void TimKiemThanhCong(List<SanPham> sanPhamList);
    void TimKiemThatBai();
}
