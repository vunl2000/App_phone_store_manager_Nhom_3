package com.example.app_phone_store_manager_nhom_3.ui.KhachHang;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.dao.DaoKhachHang;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class EditKhachHangFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private Drawable drawable;
    private NavController navController;
    private DaoKhachHang dao;
    private List<KhachHang> list;
    private EditText edMaKHChange, edHoTenKHChange, edDienThoaiChange, edDiaChiChange;
    private KhachHang khachHang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_khach_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        edMaKHChange = view.findViewById(R.id.edMaKhachHangchange);
        edHoTenKHChange = view.findViewById(R.id.edHoTenKHchange);
        edDienThoaiChange = view.findViewById(R.id.edSoDTKHchange);
        edDiaChiChange = view.findViewById(R.id.edDiachiKHchange);

        appCompatActivity = (AppCompatActivity) getActivity();
        drawable = appCompatActivity.getDrawable(R.drawable.ic_backspace);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);
        appCompatActivity.getSupportActionBar().setTitle("Cập nhập Khách Hàng");
        khachHang = new KhachHang();
        dao = new DaoKhachHang(getActivity());
        dao.open();
        edMaKHChange.setText(khachHang.getMaKH());
        edHoTenKHChange.setText(khachHang.getHoTen());
        edDienThoaiChange.setText(khachHang.getDienThoai());
        edDiaChiChange.setText(khachHang.getDiaChi());


    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navController.navigate(R.id.action_editKH_to_chitietKH);
                return true;
            case R.id.menu_reset:
                return true;
            case R.id.menu_save:
                if (khachHang.getMaKH().equals(edMaKHChange.getText().toString()) &&
                        khachHang.getHoTen().equals(edHoTenKHChange.getText().toString()) &&
                        khachHang.getDienThoai().equals(edDienThoaiChange.getText().toString()) &&
                        khachHang.getDiaChi().equals(edDiaChiChange.getText().toString())) {

                } else {

                    khachHang.setMaKH(edMaKHChange.getText().toString());
                    khachHang.setHoTen(edHoTenKHChange.getText().toString());
                    khachHang.setDienThoai(edDienThoaiChange.getText().toString());
                    khachHang.setDiaChi(edDiaChiChange.getText().toString());
                    long kq = dao.updateKH(khachHang,"");
                }

                navController.navigate(R.id.editTk_to_ListTk);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}