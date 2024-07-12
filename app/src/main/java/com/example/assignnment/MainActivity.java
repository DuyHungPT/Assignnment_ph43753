package com.example.assignnment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.assignnment.dao.ThuThuDAO;
import com.example.assignnment.fragment.CartFragment;
import com.example.assignnment.fragment.ChangePassFragment;
import com.example.assignnment.fragment.LoaiSachFragment;
import com.example.assignnment.fragment.QLSachFragment;
import com.example.assignnment.fragment.QLThanhVienFragment;
import com.example.assignnment.fragment.QuanLyPhieuMuonFragment;


import com.example.assignnment.fragment.ThongKeDoanhThuFragment;
import com.example.assignnment.fragment.ThongKeTop10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private static final int FRAGMENT_SACH = 0;
    private static final int FRAGMENT_GIOHANG = 1;
    private static final int FRAGMENT_THANHVIEN = 2;
    private static final int FRAGMENT_DOIMK = 3;
    private static final int FRAGMENT_PHIEUMUON = 4;
    private static final int FRAGMENT_LOAISACH = 5;
    private static final int FRAGMENT_TOP10 = 6;
    private static final int FRAGMENT_DOANHTHU = 7;

    private int mCurrentFragment = FRAGMENT_PHIEUMUON;

    private DrawerLayout mDrawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new QuanLyPhieuMuonFragment());
        navigationView.getMenu().findItem(R.id.nav_PhieuMuon).setChecked(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

        if (id== R.id.nav_PhieuMuon){
            if (mCurrentFragment != FRAGMENT_PHIEUMUON){
                replaceFragment(new QuanLyPhieuMuonFragment());
                mCurrentFragment = FRAGMENT_PHIEUMUON;
            }

       }else if (id == R.id.nav_Sach){
           if (mCurrentFragment != FRAGMENT_SACH){
               replaceFragment(new QLSachFragment());
               mCurrentFragment = FRAGMENT_SACH;
           }



       }else if (id == R.id.nav_Top10){
            if (mCurrentFragment != FRAGMENT_TOP10){
                replaceFragment(new ThongKeTop10Fragment());
                mCurrentFragment = FRAGMENT_TOP10;
            }

        }
        else if (id == R.id.nav_LoaiSach){
           if (mCurrentFragment != FRAGMENT_LOAISACH){
               replaceFragment(new LoaiSachFragment());
               mCurrentFragment = FRAGMENT_LOAISACH;
           }

       }else if (id == R.id.nav_DangXuat){
           startActivity(new Intent(getApplicationContext(), Login.class));
           finish();
           
       }else if (id == R.id.nav_ThanhVien){
           if (mCurrentFragment != FRAGMENT_THANHVIEN){
               replaceFragment(new QLThanhVienFragment());
               mCurrentFragment = FRAGMENT_THANHVIEN;
           }
       }
       else if (id== R.id.nav_DoiMK){
           if (mCurrentFragment != FRAGMENT_DOIMK){
               replaceFragment(new ChangePassFragment());
               mCurrentFragment = FRAGMENT_DOIMK;
           }
       }else if (id== R.id.nav_DoanhThu){
            if (mCurrentFragment != FRAGMENT_DOANHTHU){
                replaceFragment(new ThongKeDoanhThuFragment());
                mCurrentFragment = FRAGMENT_DOANHTHU;
            }
        }
        else if (id== R.id.nav_GioHang){
            if (mCurrentFragment != FRAGMENT_GIOHANG){
                replaceFragment(new CartFragment());
                mCurrentFragment = FRAGMENT_GIOHANG;
            }
        }


       mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
       if (mDrawer.isDrawerOpen(GravityCompat.START)){
           mDrawer.closeDrawer(GravityCompat.START);
       }else{
           super.onBackPressed();
       }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}