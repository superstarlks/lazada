package com.letrunghung.lazada.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.letrunghung.lazada.Model.ObjectClass.LoaiSanPham;
import com.letrunghung.lazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.letrunghung.lazada.R;

import java.util.List;

/**
 * Created by TED on 17/03/2017.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<LoaiSanPham> loaiSanPhams;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context = context;
        // Android
        this.loaiSanPhams = loaiSanPhams;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();

        int count = loaiSanPhams.size();
        for (int i=0;i<count;i++){
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if(loaiSanPhams.get(vitriGroupCha).getListCon().size() != 0){
            return 1;
        }else{
            return 0;
        }

    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView hinhMenu;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;
        if(viewGroupCha == null){
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);

            viewHolderMenu.txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSP);
            viewHolderMenu.hinhMenu = (ImageView) viewGroupCha.findViewById(R.id.imMenuPlus);

            viewGroupCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());

        int demsanphamcon = loaiSanPhams.get(vitriGroupCha).getListCon().size();

        if(demsanphamcon > 0){
            viewHolderMenu.hinhMenu.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.hinhMenu.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_remove_black_24dp);
//            viewGroupCha.setBackgroundResource(R.color.colorGray);
        }else{
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_add_black_24dp);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                HienThiSanPhamTheoDanhMucActivity hienThiSanPhamTheoDanhMucActivity = new HienThiSanPhamTheoDanhMucActivity();
//
//                Bundle bundle = new Bundle();
//
//                bundle.putInt("MALOAI",loaiSanPhams.get(vitriGroupCha).getMALOAISP());
//                bundle.putBoolean("KIEMTRA",false);
//                bundle.putString("TENLOAI",loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
//
//                hienThiSanPhamTheoDanhMucActivity.setArguments(bundle);
//                fragmentTransaction.addToBackStack("TrangChuActivity");
//                fragmentTransaction.replace(R.id.themFragment,hienThiSanPhamTheoDanhMucActivity);
//                fragmentTransaction.commit();

                return false;
            }
        });

        return viewGroupCha;
    }


    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context,loaiSanPhams.get(vitriGroupCha).getListCon());
        secondExpanable.setAdapter(secondAdapter);

        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
            Log.d("size",width + " - " + height);

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}

