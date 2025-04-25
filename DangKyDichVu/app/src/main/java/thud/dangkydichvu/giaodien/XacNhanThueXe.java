package thud.dangkydichvu.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import thud.dangkydichvu.R;

public class XacNhanThueXe extends AppCompatActivity {
    String strHoTen, strDienThoai, strThanhToan, strThongTin, strLoaiXe, strNgayBD, strmail;
    int dongia, songay, sotien;
    boolean thuetaixe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xac_nhan_thue_xe);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_nho);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        strHoTen = bundleNhan.getString("HoTen", "");
        strDienThoai = bundleNhan.getString("DienThoai", "");
        strmail = bundleNhan.getString("Email", "");

        strThanhToan = bundleNhan.getString("ThanhToan", "");
        strLoaiXe = bundleNhan.getString("LoaiXe", "");
        strNgayBD = bundleNhan.getString("NgayBD", "");
        dongia = bundleNhan.getInt("DonGia", 0);
        songay = bundleNhan.getInt("SoNgay", 0);
        sotien = bundleNhan.getInt("SoTien", 0);
        thuetaixe = bundleNhan.getBoolean("ThueTaiXe", false);

        strThongTin = "Họ tên khách hàng: " + strHoTen;
        strThongTin += "\nSố điện thoại: " + strDienThoai;
        strThongTin += "\nSố điện thoại: " + strmail;
        strThongTin += "\nHình thức thanh toán: " + strThanhToan;
        strThongTin += "\nLoại xe: " + strLoaiXe;
        strThongTin += "\nNgày bắt đầu: " + strNgayBD;
        strThongTin += "\nĐơn giá: " + dongia + "đ";
        strThongTin += "\nSố ngày thuê: " + songay;
        strThongTin += "\nThuê tài xế: " + (thuetaixe ? "Có" : "Không");
        strThongTin += "\nSố tiền: " + sotien + "đ";

        TextView txtKhachHang = findViewById(R.id.txt_khachhang);
        txtKhachHang.setText(strThongTin);
    }

    private void GoiEmail(String[] address, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(intent, "Chọn ứng dụng gởi Email:"));
        } catch (ActivityNotFoundException err) {
            Toast.makeText(this, "Lỗi thực hiện gởi Email", Toast.LENGTH_LONG).show();
        }
    }

    public void DongY(View view) {
        String strChuDe = "Xác nhận thuê xe";
        String strNoiDung = strThongTin + "\n\nCảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!";
        String[] emailNhan = {strmail};
        GoiEmail(emailNhan, strChuDe, strNoiDung);

        setResult(RESULT_OK);
        finish();
    }

    public void HuyBo(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}