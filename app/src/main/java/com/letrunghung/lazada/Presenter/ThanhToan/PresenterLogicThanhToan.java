package com.letrunghung.lazada.Presenter.ThanhToan;

import android.content.Context;
import android.util.Log;

import com.letrunghung.lazada.Model.GioHang.ModelGioHang;
import com.letrunghung.lazada.Model.ObjectClass.HoaDon;
import com.letrunghung.lazada.Model.ObjectClass.SanPham;
import com.letrunghung.lazada.Model.ThanhToan.ModelThanhToan;
import com.letrunghung.lazada.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        Log.d("MTV Them hoa don",String.valueOf(kiemtra));
        if(kiemtra){
            Log.d("MTV", "dat hang thanh cong");
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();
            for(int i = 0; i<dem ;i++){
                modelGioHang.XoaSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }

        }else{
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {

        sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
