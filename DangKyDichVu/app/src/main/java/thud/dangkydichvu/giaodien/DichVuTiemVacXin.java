package thud.dangkydichvu.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import thud.dangkydichvu.R;

public class DichVuTiemVacXin extends AppCompatActivity {
    String arrTrungTamTiemChung[] = {
            "Bệnh viện Nhi đồng Cần Thơ",
            "Bệnh viện Đa khoa Trung ương Cần Thơ",
            "Bệnh viện Phụ sản Cần Thơ",
            "Bệnh viện Đa khoa Thành phố Cần Thơ",
            "Trung tâm Tiêm Chủng VNVC Cần Thơ",
            "Trung tâm Y tế quận Ninh Kiều",
            "Trung tâm Y tế quận Bình Thủy"
    };

    String arrLoaiVacXin[] = {
            "Vắc xin ngừa COVID-19",
            "Vắc xin ngừa HPV",
            "Vắc xin ngừa viêm gan A",
            "Vắc xin ngừa viêm gan B",
            "Vắc xin ngừa cúm mùa",
            "Vắc xin ngừa sởi, quai bị, rubella",
            "Vắc xin ngừa viêm màng não mô cầu",
            "Vắc xin ngừa thủy đậu"
    };
    int arrDonGia[] = {250000, 300000, 400000, 200000, 120000 ,180000, 300000};
    Spinner spnTrungTamTiemChung, spnLoaiVacXin;
    String strTrungTamTiemChung, strLoaiVacXin, strNgayTiem;
    int dongia, sotien;
    EditText edtDonGia, edtSoTien;
    TextView txtNgayTiem;
    DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_tiem_vac_xin);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.vvv_nho);

        txtNgayTiem = findViewById(R.id.txt_ngaytiem);
        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoTien = findViewById(R.id.edt_sotien);

        spnTrungTamTiemChung = findViewById(R.id.spn_trungtamtiemchung);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrTrungTamTiemChung);
        adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice);
        spnTrungTamTiemChung.setAdapter(adapter);
        spnTrungTamTiemChung.setOnItemSelectedListener(new ChonTrungTamTiemChung());

        spnLoaiVacXin = findViewById(R.id.spn_loaivacxin);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrLoaiVacXin);
        adapter2.setDropDownViewResource( android.R.layout.simple_list_item_single_choice);
        spnLoaiVacXin.setAdapter(adapter2);
        spnLoaiVacXin.setOnItemSelectedListener(new ChonLoaiVacXin());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        strNgayTiem = dateFormat.format(calendar.getTime());
        txtNgayTiem.setText("Ngày tiêm: " + strNgayTiem);
    }

    private class ChonLoaiVacXin implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strLoaiVacXin = arrLoaiVacXin[position];
            dongia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + dongia +"đ");
        }

        public void onNothingSelected(AdapterView<?> parent){
            strLoaiVacXin = "";
            dongia = 0;
        }
    }

    private class ChonTrungTamTiemChung implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strTrungTamTiemChung = arrTrungTamTiemChung[position];
        }

        public void onNothingSelected(AdapterView<?> parent){
            strTrungTamTiemChung = "";
        }
    }

    public void ChonNgay(View view) {
        Calendar calendar = Calendar.getInstance();
        int iniYear = calendar.get(Calendar.YEAR);
        int iniMonth = calendar.get(Calendar.MONTH);
        int iniDay = calendar.get(Calendar.DAY_OF_MONTH);
        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                strNgayTiem = dayOfMonth + "/" + month + "/" + year;
                txtNgayTiem.setText("Ngày tiêm: " + strNgayTiem);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, myDateSetListener, iniYear, iniMonth, iniDay);
        dialog.setTitle("Chọn ngày tiêm");
        dialog.show();
    }
    public boolean TinhTien(View view) {
        sotien = dongia;
        edtSoTien.setText("Số tiền: " + sotien + "đ");
        return true;
    }
    public void DangKy(View view) {
        if (!TinhTien(view))
            return;

        String strKetQua = "Trung tâm tiêm chủng: " + strTrungTamTiemChung + "\n";
        strKetQua += "Loại vắc xin: " + strLoaiVacXin + "\n";
        strKetQua += "Ngày Tiêm: " + strNgayTiem + "\n";
        strKetQua += "Số tiền: " + sotien + "\n";

        // Tạo AlertDialog với giao diện cải tiến
        AlertDialog.Builder bdrThongbao = new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Kết quả đăng ký");

        // Tạo TextView tùy chỉnh cho message
        TextView messageView = new TextView(this);
        messageView.setText(strKetQua);
        messageView.setTextSize(16); // Kích thước chữ lớn hơn
        messageView.setTextColor(getResources().getColor(android.R.color.black));
        messageView.setBackgroundColor(getResources().getColor(android.R.color.white));
        messageView.setPadding(20, 20, 20, 20); // Thêm khoảng cách
        messageView.setLineSpacing(1.2f, 1.2f); // Giãn dòng cho dễ đọc

        bdrThongbao.setView(messageView);

        // Thêm icon
        bdrThongbao.setIcon(R.drawable.ic_check_circle); // Cần thêm drawable này

        // Cải tiến nút Đóng
        bdrThongbao.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Tùy chỉnh giao diện khi hiển thị
        AlertDialog dialog = bdrThongbao.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        getResources().getColor(android.R.color.holo_green_dark));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(16);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(null, Typeface.BOLD);
            }
        });

        dialog.show();
    }
    public void DongActivity(View view) {
        Intent intent = new Intent(this, MainActivity_GridView.class);
        startActivity(intent);
    }
}