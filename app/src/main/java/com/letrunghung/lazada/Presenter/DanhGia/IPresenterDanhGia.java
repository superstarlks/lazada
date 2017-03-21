package com.letrunghung.lazada.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.letrunghung.lazada.Model.ObjectClass.DanhGia;

/**
 * Created by TED on 21/03/2017.
 */

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
