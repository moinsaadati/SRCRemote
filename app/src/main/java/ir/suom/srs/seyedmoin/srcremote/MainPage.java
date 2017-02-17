package ir.suom.srs.seyedmoin.srcremote;

import android.graphics.Typeface;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.suom.srs.seyedmoin.srcremote.Dialog.Adm_Dialog;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;


public class MainPage extends AppCompatActivity {

    // Moin Saadati's Comment : Connect To Device And Get Device's Status
    // Moin Saadati's Comment : then Control Remote
    // 2/10/17 7:25 PM
    Button btn_Control;
    TextView tv_Device_status, tv_control_smart, tv_sadjad_research_center, tv_massage_wifi, tv_massage;

    ImageView iv_massage_wifi, iv_icon;

    WifiManager wifiManager;

    int timetap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Initialization();

        /*if (getIntent().getExtras() != null) {
            tv_Device_status.setText(getIntent().getStringExtra("response"));
        }*/

        tv_Device_status.setText(R.string.door_status_closed);

        btn_Control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // onClick btn_control
            }
        });

    }

    private void Initialization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));
        Typeface tp_bold = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz_bold));

        btn_Control = (Button) findViewById(R.id.btn_control);
        tv_Device_status = (TextView) findViewById(R.id.tv_device_status);
        tv_massage = (TextView) findViewById(R.id.tv_massage);

        // Action Bar
        tv_sadjad_research_center = (TextView) findViewById(R.id.tv_sadjad_research_center);
        tv_control_smart = (TextView) findViewById(R.id.tv_control_smart);
        tv_massage_wifi = (TextView) findViewById(R.id.tv_massage_wifi);
        iv_massage_wifi = (ImageView) findViewById(R.id.iv_massage_wifi);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);

        tv_sadjad_research_center.setTypeface(tp_bold);
        tv_control_smart.setTypeface(tp_bold);
        tv_massage_wifi.setTypeface(tp);

        tv_Device_status.setTypeface(tp);
        tv_massage.setTypeface(tp);
        btn_Control.setTypeface(tp);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        ConnectToDevice();

    }

    // Moin Saadati's Comment : For ActionBar Item
    // 2/10/17 7:51 PM
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_administrator:
                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void Action_Administorator(View v) {
        if (v.getId() == R.id.iv_icon) {
            ShowDailog();

         /*   // Go To Adm_Dialog  After 20 Time Tap
            timetap++;
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    timetap = 0;
                }
            };
            if (timetap <= 19) {
                //Single click
                Toast.makeText(getBaseContext(), String.valueOf(20 - timetap), Toast.LENGTH_SHORT).show();
                handler.postDelayed(r, 10000);
            } else if (timetap == 20) {
                timetap = 0;
                ShowDailog();
            }*/
        }

    }

    private void ShowDailog() {

        Adm_Dialog adm_dialog = new Adm_Dialog();
        adm_dialog.show(getFragmentManager(), "admin_dialog");

    }

    // Moin Saadati's Comment : Methods For Connect To Device
    // 2/14/17 4:04 PM
    private boolean ConnectToDevice() {

        WifiConfiguration wc = new WifiConfiguration();

        //wc.hiddenSSID = true;
        wc.SSID = "\"" + Constants.AP_NAME_EXAMPLE + "\"";
        wc.preSharedKey = "\"" + tv_Device_status.getText().toString() + "\"";
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

        int id = wifiManager.addNetwork(wc);

        //wifiManager.disconnect();
        if (wifiManager.enableNetwork(id, true)) {
            Log.e("Connect To Device:", "True");
            return true;
        } else {
            Log.e("Connect To Device:", "False");
            return false;
        }

    }
}
