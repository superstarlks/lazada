package com.letrunghung.lazada.Presenter.DangKy;

import android.content.Context;

import com.letrunghung.lazada.Model.DangNhap_DangKy.ModelDangKy;
import com.letrunghung.lazada.Model.ObjectClass.NhanVien;
import com.letrunghung.lazada.View.DangNhap_DangKy.ViewDangKy;

/**
 * Created by TED on 18/03/2017.
 */

public class PresenterLogicDangKy implements IPresenterDangKy  {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;
    Context context;
    public PresenterLogicDangKy(ViewDangKy viewDangKy, Context context) {
        this.viewDangKy = viewDangKy;
        this.context = context;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanvien) {
        boolean kiemtra = modelDangKy.DangKyThanhVien(context,nhanvien);
        if(kiemtra){
            viewDangKy.DangKyThangCong();
        }else{
            viewDangKy.DangKyThatBai();
        }
    }
}
