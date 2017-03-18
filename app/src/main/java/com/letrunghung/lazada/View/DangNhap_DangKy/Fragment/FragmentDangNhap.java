package com.letrunghung.lazada.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.letrunghung.lazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.letrunghung.lazada.R;
import com.letrunghung.lazada.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

/**
 * Created by TED on 17/03/2017.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, GoogleApiClient.OnConnectionFailedListener  {
    Button btnDangNhapFacebook,btnDangNhapGoogle,btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 111;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edTenDangNhap,edMatKhau;
    TextInputLayout input_EmailDangNhap, input_MatKhauDangNhap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);

        //google login
        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(),this);

        //facebook login
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        // Add code to print out the key hash
//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                    "com.letrunghung.lazada",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edTenDangNhap = (EditText) view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDangNhap);
        input_EmailDangNhap = (TextInputLayout) view.findViewById(R.id.input_EmailDangNhap);
        edTenDangNhap.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        input_MatKhauDangNhap = (TextInputLayout) view.findViewById(R.id.input_MatKhauDangNhap);
        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;

            case R.id.btnDangNhapGoogle:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus,SIGN_IN_GOOGLE_PLUS);
                showProcessDialog();
                break;

            case R.id.btnDangNhap:
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                if(kiemtra){
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                }else{
                    Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }
                ;break;
        }

    }


    private void showProcessDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SIGN_IN_GOOGLE_PLUS){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edDiaChiEmailDangNhap:
                String email = ((EditText)v).getText().toString();
                if (!hasFocus){
                    if (email.trim().equals("")){
                        input_EmailDangNhap.setErrorEnabled(true);
                        input_EmailDangNhap.setError("Bạn chưa điền Email");
                    }else {
                        input_EmailDangNhap.setErrorEnabled(false);
                        input_EmailDangNhap.setError("");
                    }
                }
                break;
            case R.id.edMatKhauDangNhap:
                String matkhau = ((EditText)v).getText().toString();
                if (!hasFocus){
                    if (matkhau.trim().equals("")){
                        input_MatKhauDangNhap.setErrorEnabled(true);
                        input_MatKhauDangNhap.setError("Bạn chưa điền mật khẩu");
                    }else {
                        input_MatKhauDangNhap.setErrorEnabled(false);
                        input_MatKhauDangNhap.setError("");
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        progressDialog.cancel();
    }
}
