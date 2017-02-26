package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;
import ir.suom.srs.seyedmoin.srcremote.Dialog.DReset_Device;
import ir.suom.srs.seyedmoin.srcremote.Dialog.DShow_DID;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Inst_Act extends AppCompatActivity {

    // Moin Saadati's Comment : Installer Panel
    // 2/14/17 5:18 PM
    Button btn_option1, btn_option2, btn_option3, btn_option4;
    TextView tv_control_smart, tv_sadjad_research_center, tv_massage_wifi, tv_main_setting_device;
    ImageView iv_massage_wifi, iv_icon;

    WifiManager wifiManager;

    // SharedPreference
    SharedPreferences local_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst);

        Initilization();

        btn_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activity for Set Device
                //Toast.makeText(getBaseContext(), "Set Device", Toast.LENGTH_SHORT).show();
                Intent inst_setting = new Intent(Inst_Act.this, Inst_Setting.class);
                startActivity(inst_setting);
            }
        });

        btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialog for Reset Factory Device
                // Toast.makeText(getBaseContext(), "Reset Device", Toast.LENGTH_SHORT).show();
                ResetFactory();
            }
        });

        btn_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialog for Show Devcie ID
                ShowDeviceID();
            }

        });
        btn_option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activity For Remotes
                //Toast.makeText(getBaseContext(), "Show Remotes", Toast.LENGTH_SHORT).show();
                Intent inst_remotes = new Intent(Inst_Act.this, Inst_Remotes.class);
                startActivity(inst_remotes);

            }
        });
    }

    private void ResetFactory() {

        DReset_Device dReset_device = DReset_Device.newInstance(10, 22f, false, false);
        dReset_device.show(getSupportFragmentManager(), "DReset_Device");

    }

    private void ShowDeviceID() {

        DShow_DID dShow_did = DShow_DID.newInstance(10, 22f, false, false);
        dShow_did.show(getSupportFragmentManager(), "DShow_DID");

    }


    private void Initilization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));
        Typeface tp_bold = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz_bold));


        // Action Bar
        tv_sadjad_research_center = (TextView) findViewById(R.id.tv_sadjad_research_center);
        tv_control_smart = (TextView) findViewById(R.id.tv_control_smart);
        tv_massage_wifi = (TextView) findViewById(R.id.tv_massage_wifi);
        iv_massage_wifi = (ImageView) findViewById(R.id.iv_massage_wifi);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_icon.setClickable(false);

        tv_main_setting_device = (TextView) findViewById(R.id.tv_main_setting_device);
        btn_option1 = (Button) findViewById(R.id.btn_option_1);
        btn_option2 = (Button) findViewById(R.id.btn_option_2);
        btn_option3 = (Button) findViewById(R.id.btn_option_3);
        btn_option4 = (Button) findViewById(R.id.btn_option_4);

        // Action Bar
        tv_sadjad_research_center = (TextView) findViewById(R.id.tv_sadjad_research_center);
        tv_control_smart = (TextView) findViewById(R.id.tv_control_smart);
        tv_massage_wifi = (TextView) findViewById(R.id.tv_massage_wifi);
        iv_massage_wifi = (ImageView) findViewById(R.id.iv_massage_wifi);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_massage_wifi.setVisibility(View.GONE);
        iv_massage_wifi.setVisibility(View.GONE);

        tv_sadjad_research_center.setTypeface(tp_bold);
        tv_control_smart.setTypeface(tp_bold);

        tv_main_setting_device.setTypeface(tp);
        btn_option1.setTypeface(tp);
        btn_option2.setTypeface(tp);
        btn_option3.setTypeface(tp);
        btn_option4.setTypeface(tp);


        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

    }

}
