package com.letrunghung.lazada.View.DanhGia;

import com.letrunghung.lazada.Model.ObjectClass.DanhGia;

import java.util.List;

/**
 * Created by TED on 21/03/2017.
 */

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList);
}
