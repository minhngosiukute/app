package thud.dangkydichvu.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import thud.dangkydichvu.R;

public class DichVuInternet extends AppCompatActivity {
    String[] arrGoiCuoc={"Phổ thông ", "Gia đình"};
    Spinner spnGoiCuoc;
    String strGoiCuoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_internet);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.internet_nho);

        spnGoiCuoc = findViewById(R.id.spn_goicuoc);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrGoiCuoc);
        adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice);
        spnGoiCuoc.setAdapter(adapter);
        spnGoiCuoc.setOnItemSelectedListener(new ChonPhanTu());
    }

    private class ChonPhanTu implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strGoiCuoc = arrGoiCuoc[position];
        }

        public void onNothingSelected(AdapterView<?> parent){
            strGoiCuoc = "";
        }
    }

    public void DangKy(View view) {
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        CheckBox chkTruyenHinh, chkCamera;
        chkTruyenHinh = findViewById(R.id.chk_truyenhinh);
        chkCamera = findViewById(R.id.chk_camera);
        String strKetQua = "Gói cước: " + strGoiCuoc + "\n";
        strKetQua += "Hình thức thanh toán: " + rad.getText().toString() + "\n";
        strKetQua += "Dịch vụ khác: ";
        if ((!chkTruyenHinh.isChecked()) && (!chkCamera.isChecked()))
            strKetQua += "không";
        else {
            if (chkTruyenHinh.isChecked()) {
                strKetQua += chkTruyenHinh.getText().toString();
                if (chkCamera.isChecked())
                    strKetQua += ", ";
            }
            if (chkCamera.isChecked())
                strKetQua += chkCamera.getText().toString();
        }

        // Tạo AlertDialog với giao diện cải tiến
        AlertDialog.Builder bdrThongbao = new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Kết quả đăng ký");

        // Tạo view tùy chỉnh cho message
        TextView messageView = new TextView(this);
        messageView.setText(strKetQua);
        messageView.setTextSize(16);
        messageView.setTextColor(getResources().getColor(android.R.color.black));
        messageView.setBackgroundColor(getResources().getColor(android.R.color.white));
        messageView.setPadding(20, 20, 20, 20);
        messageView.setLineSpacing(1.2f, 1.2f);

        bdrThongbao.setView(messageView);

        // Cải tiến nút Đóng
        bdrThongbao.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Thêm nút icon và style
        bdrThongbao.setIcon(R.drawable.ic_check_circle); // Cần thêm drawable này vào res/drawable
        AlertDialog dialog = bdrThongbao.create();

        // Tùy chỉnh giao diện dialog
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        getResources().getColor(android.R.color.holo_green_dark));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(16);
            }
        });

        dialog.show();
    }

    public void DongActivity(View view){
        Intent intent = new Intent(this, MainActivity_GridView.class);
        startActivity(intent);
    }
}