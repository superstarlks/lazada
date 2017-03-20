package com.letrunghung.lazada.View.TrangChu;

import com.letrunghung.lazada.Model.ObjectClass.DienTu;
import com.letrunghung.lazada.Model.ObjectClass.SanPham;
import com.letrunghung.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by TED on 20/03/2017.
 */

public interface ViewDienTu {
    void HienThiDanhSach (List<DienTu> dienTus);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);
}
