package thud.dangkydichvu.giaodien;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import thud.dangkydichvu.R;

public class KhieuNai extends AppCompatActivity {

    private EditText editTextID, editTextName;
    private TextView resultTextView;
    private Spinner spinnerClass;
    private DatePicker datePicker;
    private Button btnOK, btnCancel, btnReturn, btnDelete;
    private CheckBox ch1, ch2, ch3;
    private RadioGroup rg1;
    private RadioButton rb1;

    private ListView dichvuListView;
    private ArrayAdapter<String> dichvuListAdapter;
    private ArrayList<String> InfoList = new ArrayList<>();
    private int getSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khieu_nai2);

        editTextID = findViewById(R.id.edittext);
        editTextName = findViewById(R.id.edittext1);
        resultTextView = findViewById(R.id.resultTextView);
        spinnerClass = findViewById(R.id.spinnerClass);
        datePicker = findViewById(R.id.datePicker);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);
        ch1 = findViewById(R.id.checkBox1);
        ch2 = findViewById(R.id.checkBox2);
        ch3 = findViewById(R.id.checkBox3);
        rg1 = findViewById(R.id.radioGroup);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerClass.setAdapter(adapter);


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = editTextID.getText().toString();
                String Name = editTextName.getText().toString();

                int selected = rg1.getCheckedRadioButtonId();
                rb1 = findViewById(selected);
                String gender = rb1.getText().toString();

                String selectedClass = spinnerClass.getSelectedItem().toString();
                StringBuilder StatusBuilder = new StringBuilder();

                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int year = datePicker.getYear();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
                String birthday = dateFormat.format(new Date(year - 1900, month, day));

                if (ch1.isChecked()) {
                    StatusBuilder.append(ch1.getText());
                }
                if (ch2.isChecked()) {
                    StatusBuilder.append(ch2.getText());
                }
                if (ch3.isChecked()) {
                    StatusBuilder.append(ch3.getText());
                }

                String Status = StatusBuilder.toString();

                String Info = "Số điện thoại: " + sdt + "\n" +
                        "Tên : " + Name + "\n" +
                        "Giới tính: " + gender + "\n" +
                        "Loại dịch vụ: " + selectedClass + "\n" +
                        "Ngày: " + birthday + "\n" +
                        "Trạng thái: " + Status;

                InfoList.add(Info);


                dichvuListAdapter.notifyDataSetChanged();
                dichvuListView.setAdapter(dichvuListAdapter);

                editTextID.setText("");
                editTextName.setText("");
//                rg1.clearCheck();
                spinnerClass.setSelection(0);
                datePicker.updateDate(Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                ch1.setChecked(false);
                ch2.setChecked(false);
                ch3.setChecked(false);
                resultTextView.setText("");

                dichvuListView.setVisibility(View.VISIBLE);
                btnReturn.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);

                editTextID.setVisibility(View.GONE);
                editTextName.setVisibility(View.GONE);
                spinnerClass.setVisibility(View.GONE);
                datePicker.setVisibility(View.GONE);
                rg1.setVisibility(View.GONE);
                ch1.setVisibility(View.GONE);
                ch2.setVisibility(View.GONE);
                ch3.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnOK.setVisibility(View.GONE);
                resultTextView.setVisibility(View.GONE);

                findViewById(R.id.textView1).setVisibility(View.GONE);
                findViewById(R.id.textView2).setVisibility(View.GONE);
                findViewById(R.id.textView3).setVisibility(View.GONE);
                findViewById(R.id.textView4).setVisibility(View.GONE);
                findViewById(R.id.textView5).setVisibility(View.GONE);
                findViewById(R.id.textView6).setVisibility(View.GONE);
                findViewById(R.id.textView7).setVisibility(View.GONE);

            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dichvuListView.setVisibility(View.GONE);
                btnReturn.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);

                // Restore visibility of input fields
                editTextID.setVisibility(View.VISIBLE);
                editTextName.setVisibility(View.VISIBLE);
                spinnerClass.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                rg1.setVisibility(View.VISIBLE);
                ch1.setVisibility(View.VISIBLE);
                ch2.setVisibility(View.VISIBLE);
                ch3.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                btnOK.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);

                findViewById(R.id.textView1).setVisibility(View.VISIBLE);
                findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                findViewById(R.id.textView3).setVisibility(View.VISIBLE);
                findViewById(R.id.textView4).setVisibility(View.VISIBLE);
                findViewById(R.id.textView5).setVisibility(View.VISIBLE);
                findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                findViewById(R.id.textView7).setVisibility(View.VISIBLE);

                rg1.check(R.id.radioButton);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (studentInfoList.size() > 0) {
//                    studentInfoList.remove(0);
//                    studentListAdapter.notifyDataSetChanged();
//                }
                if (KhieuNai.this.getSelectedIndex != -1) {
                    InfoList.remove(KhieuNai.this.getSelectedIndex);
                    KhieuNai.this.getSelectedIndex = -1;

                    dichvuListAdapter.notifyDataSetChanged();
                    dichvuListView.setAdapter(dichvuListAdapter);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextID.setText("");
                editTextName.setText("");
                rg1.check(R.id.radioButton);
                spinnerClass.setSelection(0);
                datePicker.updateDate(Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                ch1.setChecked(false);
                ch2.setChecked(false);
                ch3.setChecked(false);
                resultTextView.setText("");
            }
        });

        dichvuListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, InfoList);
        dichvuListView = findViewById(R.id.studentListView);
        dichvuListView.setAdapter(dichvuListAdapter);


        dichvuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhieuNai.this.getSelectedIndex = position;
                for (int i = 0; i < dichvuListView.getChildCount(); i++) {
                    if(position == i ){
                        dichvuListView.getChildAt(i).setBackgroundColor(Color.GRAY);
                    }else{
                        dichvuListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
    }
}