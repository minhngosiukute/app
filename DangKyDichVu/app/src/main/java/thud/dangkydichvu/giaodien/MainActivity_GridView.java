package thud.dangkydichvu.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import thud.dangkydichvu.R;
import thud.dangkydichvu.xuly.ImageAdapter;

public class MainActivity_GridView extends AppCompatActivity {

    private final Integer[] Images = {R.mipmap.internet, R.mipmap.vvv,
                                    R.mipmap.car,R.mipmap.contact, R.mipmap.thoat };
    Class[] arrClasses = { DichVuInternet.class, DichVuTiemVacXin.class, DichVuThueOTo.class, KhieuNai.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gridview);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.service);

        GridView gdvMenu = findViewById(R.id.gdv_menu);
        ImageAdapter adapter = new ImageAdapter(this, Images);
        gdvMenu.setAdapter(adapter); gdvMenu.setOnItemClickListener(new ChonCongViec());
    }

    private class ChonCongViec implements android.widget.AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(i == 5)
                finish();
            else {
                Intent intent = new Intent(MainActivity_GridView.this, arrClasses[i]);
                startActivity(intent);
            }
        }
    }
}