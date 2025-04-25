package thud.dangkydichvu.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import thud.dangkydichvu.R;

public class DichVuThueOTo extends AppCompatActivity {

    String arrLoaiXe[] = { "Xe 4 chỗ", "Xe 7 chỗ", "Xe 16 chỗ", "Xe 29 chỗ", "Xe 45 chỗ" };
    int arrDonGia[] = {120000, 130000, 150000, 170000, 400000};
    Spinner spnLoaiXe;
    String strLoaiXe, strNgayBD;
    int dongia, songay, sotien;
    boolean thuetaixe;
    EditText edtDonGia, edtSoTien;
    TextView txtNgayBD;
    TextInputLayout layoutSoNgay;
    TextInputEditText edtSoNgay;
    CheckBox chkTaiXe;
    DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_thue_oto);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_nho);

        txtNgayBD = findViewById(R.id.txt_ngaybd);
        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoNgay = findViewById(R.id.edt_songay);
        edtSoTien = findViewById(R.id.edt_sotien);
        layoutSoNgay = findViewById(R.id.layout_songay);
        chkTaiXe = findViewById(R.id.chk_taixe);

        spnLoaiXe = findViewById(R.id.spn_loaixe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrLoaiXe);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnLoaiXe.setAdapter(adapter);
        spnLoaiXe.setOnItemSelectedListener(new ChonLoaiXe());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        strNgayBD = dateFormat.format(calendar.getTime());
        txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
    }

    private class ChonLoaiXe implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strLoaiXe = arrLoaiXe[position];
            dongia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + dongia + "đ");
        }

        public void onNothingSelected(AdapterView<?> parent){
            strLoaiXe = "";
            dongia = 0;
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
                strNgayBD = dayOfMonth + "/" + month + "/" + year;
                txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, myDateSetListener, iniYear, iniMonth, iniDay);
        dialog.setTitle("Chọn ngày bắt đầu thuê");
        dialog.show();
    }

    public boolean TinhTien(View view) {
        String strSoNgay = edtSoNgay.getText().toString().trim();
        if (strSoNgay.length() == 0 || Integer.parseInt(strSoNgay) == 0) {
            layoutSoNgay.setError("Lỗi nhập số ngày thuê xe");
            edtSoNgay.requestFocus();
            return false;
        } else {
            layoutSoNgay.setError(null);
            songay = Integer.parseInt(strSoNgay);
        }
        sotien = songay * dongia;
        thuetaixe = chkTaiXe.isChecked();
        if (thuetaixe)
            sotien = sotien + songay * 30000;
        edtSoTien.setText("Số tiền: " + sotien + "đ");
        return true;
    }

    public void DatThue(View view) {
        if (!TinhTien(view))
            return;
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("LoaiXe", strLoaiXe);
        bundleGoi.putInt("DonGia", dongia);
        bundleGoi.putString("NgayBD", strNgayBD);
        bundleGoi.putInt("SoNgay", songay);
        bundleGoi.putBoolean("ThueTaiXe", thuetaixe);
        bundleGoi.putInt("SoTien", sotien);
        Intent intent = new Intent(this, DatThueXe.class);
        intent.putExtras(bundleGoi);
        startActivity(intent);
    }

    public void DongActivity(View view) {
        Intent intent = new Intent(this, MainActivity_GridView.class);
        startActivity(intent);
    }
}