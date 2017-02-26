package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Remote;
import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Setting;
import ir.suom.srs.seyedmoin.srcremote.Model.remote;
import ir.suom.srs.seyedmoin.srcremote.Model.setting;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Inst_Setting extends AppCompatActivity implements Adapter_Setting.OnItemClickListener {

    // Moin Saadati's Comment : Inst_Setting - For Setting Devcie
    // 2/25/17 2:01 PM
    RecyclerView rv_settings;

    TextView tv_main_setting_title;
    ImageView iv_icon_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst__setting);

        Initilization();

    }

    private void Initilization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));

        iv_icon_back = (ImageView) findViewById(R.id.iv_icon_back);
        tv_main_setting_title = (TextView) findViewById(R.id.tv_main_setting_title);
        tv_main_setting_title.setTypeface(tp);

        rv_settings = (RecyclerView) findViewById(R.id.rv_settings);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_settings.setLayoutManager(llm);

        Adapter_Setting ar = new Adapter_Setting(this);
        ar.setOnItemClickListener(this);
        ar.setData(Init_Data_Option());

        rv_settings.setAdapter(ar);

        iv_icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private ArrayList<setting> Init_Data_Option() {

        String[] setting_items = getResources().getStringArray(R.array.setting_option);

        ArrayList<setting> settings = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            settings.add(new setting(setting_items[i], "100"));
        }
        return settings;
    }

    // Moin Saadati's Comment : On Click Item RecycleView Setting for Set Dialog
    // 2/25/17 4:16 PM
    @Override
    public void onItemClick(View view, int position) {

        // Show Dialog for set option
        Toast.makeText(getBaseContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

    }
}
