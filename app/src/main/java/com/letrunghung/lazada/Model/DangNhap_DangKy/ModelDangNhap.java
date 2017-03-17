package com.letrunghung.lazada.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

/**
 * Created by TED on 17/03/2017.
 */

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    public AccessToken LayTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;

    }
    public String LayCachedDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");

        return tennv;
    }
    public void CapNhatCachedDangNhap(Context context,String tenv){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv",tenv);

        editor.commit();
    }
    public void HuyTokenTracker(){
        accessTokenTracker.stopTracking();
    }
}
