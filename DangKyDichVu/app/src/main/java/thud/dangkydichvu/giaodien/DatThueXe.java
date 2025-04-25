package thud.dangkydichvu.giaodien;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import thud.dangkydichvu.R;

public class DatThueXe extends AppCompatActivity {
    String strHoTen, strDienThoai, strEmail, strThanhToan;
    EditText edtHoTen, edtDienThoai, edtEmail;
    TextInputLayout layoutHoTen, layoutDienThoai, layoutEmail;
    ActivityResultLauncher<Intent> runActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_thue_xe);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_nho);

        edtHoTen = findViewById(R.id.edt_hotenkh);
        edtDienThoai = findViewById(R.id.edt_dienthoai);
        edtEmail = findViewById(R.id.edt_email);
        layoutHoTen = findViewById(R.id.layout_hotenkh);
        layoutDienThoai = findViewById(R.id.layout_dienthoai);
        layoutEmail = findViewById(R.id.layout_email);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        String strLoaiXe, strNgayBD, strThongTin;
        int dongia, songay, sotien;
        boolean thuetaixe;

        strNgayBD = bundleNhan.getString("NgayBD", "");
        strLoaiXe = bundleNhan.getString("LoaiXe", "");
        dongia = bundleNhan.getInt("DonGia", 0);
        songay = bundleNhan.getInt("SoNgay", 0);
        sotien = bundleNhan.getInt("SoTien", 0);
        thuetaixe = bundleNhan.getBoolean("ThueTaiXe", false);
        strThongTin = "Thông tin đặt thuê xe:";
        strThongTin += "\n Loại xe: " + strLoaiXe;
        strThongTin += "\n Đơn giá: " + dongia + "đ";
        strThongTin += "\n Ngày bắt đầu: " + strNgayBD;
        strThongTin += "\n Số ngày thuê: " + songay;
        if (thuetaixe)
            strThongTin += "\n Thuê tài xế: Có";
        else strThongTin += "\n Thuê tài xế: Không";
        strThongTin += "\n Số tiền: " + sotien+"đ";
        TextView txtThongTin = findViewById(R.id.txt_thongtin);
        txtThongTin.setText(strThongTin);

        runActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Toast.makeText(DatThueXe.this, "Đồng ý thuê xe!", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(DatThueXe.this, "Không đồng ý thuê xe!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void XacNhanThueXe(View view) {
        strHoTen = edtHoTen.getText().toString().trim();
        if (strHoTen.length() == 0) {
            layoutHoTen.setError("Lỗi chưa nhập họ tên khách hàng");
            edtHoTen.requestFocus();
            return;
        }
        else
            layoutHoTen.setError(null);
        strDienThoai = edtDienThoai.getText().toString().trim();
        if (strDienThoai.length() == 0) {
            layoutDienThoai.setError("Lỗi chưa nhập số điện thoại");
            edtDienThoai.requestFocus();
            return;
        } else
            layoutDienThoai.setError(null);
        strEmail = edtEmail.getText().toString().trim();
        if (strEmail.length() == 0) {
            layoutEmail.setError("Lỗi chưa nhập email");
            edtEmail.requestFocus();
            return;
        } else
            layoutEmail.setError(null);
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strThanhToan = rad.getText().toString();
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("HoTen", strHoTen);
        bundleGoi.putString("DienThoai", strDienThoai);
        bundleGoi.putString("Email", strEmail);
        bundleGoi.putString("ThanhToan", strThanhToan);
        bundleGoi.putString("LoaiXe", getIntent().getExtras().getString("LoaiXe"));
        bundleGoi.putInt("DonGia", getIntent().getExtras().getInt("DonGia"));
        bundleGoi.putString("NgayBD", getIntent().getExtras().getString("NgayBD"));
        bundleGoi.putInt("SoNgay", getIntent().getExtras().getInt("SoNgay"));
        bundleGoi.putBoolean("ThueTaiXe", getIntent().getExtras().getBoolean("ThueTaiXe"));
        bundleGoi.putInt("SoTien", getIntent().getExtras().getInt("SoTien"));
        Intent intent = new Intent(this, XacNhanThueXe.class);
        intent.putExtras(bundleGoi);
        runActivity.launch(intent);
    }

    public void HuyBo(View view) {
        finish();
    }
}