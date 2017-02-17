package ir.suom.srs.seyedmoin.srcremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
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

import ir.suom.srs.seyedmoin.srcremote.DeviceService.DeviceService;
import ir.suom.srs.seyedmoin.srcremote.Dialog.DInst_Auth;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;


public class MainPage extends AppCompatActivity {

    // Moin Saadati's Comment : Connect To Device And Get Device's Status
    // Moin Saadati's Comment : then Control Remote
    // 2/10/17 7:25 PM
    Button btn_Control;
    TextView tv_Door_Device_status, tv_control_smart, tv_sadjad_research_center, tv_massage_wifi, tv_massage;

    ImageView iv_massage_wifi, iv_icon;


    // For Device Service
    WifiManager wifiManager;
    Intent device_service;
    MainPage.DeviceReceiver dReceiver;
    IntentFilter intentFilter;
    String CurrentSSID;

    int timetap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.Action_DeviceService);

        // Set Receiver
        dReceiver = new DeviceReceiver();
        registerReceiver(dReceiver, intentFilter);

        // Launch WiFi service
        device_service = new Intent(this, DeviceService.class);
        startService(device_service);

        setContentView(R.layout.activity_main_page);

        Initialization();

        tv_Door_Device_status.setText(R.string.door_status_closed);

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
        tv_Door_Device_status = (TextView) findViewById(R.id.tv_door_device_status);
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

        tv_Door_Device_status.setTypeface(tp);
        tv_massage.setTypeface(tp);
        btn_Control.setTypeface(tp_bold);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

    }

    // Moin Saadati's Comment : Methods For Scan Wifi
    // 2/10/17 4:26 PM
    private void UIControl() {

        iv_massage_wifi.setVisibility(View.VISIBLE);
        tv_massage_wifi.setVisibility(View.VISIBLE);

        if (wifiManager.isWifiEnabled()) {
            if (CurrentSSID != null) {
                if (CurrentSSID.equals(Constants.AP_NAME_EXAMPLE)) {
                    iv_massage_wifi.setImageResource(R.drawable.ic_check_circle);
                    tv_massage_wifi.setTextColor(getResources().getColor(R.color.green_text));
                    tv_massage_wifi.setText(R.string.wifi_massage_OK);
                    tv_massage.setText(R.string.msg_time_for_apply_control);
                } else {
                    iv_massage_wifi.setImageResource(R.drawable.ic_remove_circle);
                    tv_massage_wifi.setTextColor(getResources().getColor(R.color.orange_text));
                    tv_massage_wifi.setText(R.string.wifi_massage_Connecting);
                    tv_massage.setText(R.string.msg_near_to_connect);
                }
            }
        } else {
            iv_massage_wifi.setImageResource(R.drawable.ic_cancel);
            tv_massage_wifi.setTextColor(getResources().getColor(R.color.red_text));
            tv_massage_wifi.setText(R.string.wifi_massage_ERROR);
            tv_massage.setText(R.string.msg_near_to_connect);
        }

    }

    public class DeviceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.Action_DeviceService)) {
                CurrentSSID = intent.getStringExtra(Constants.KEY_CurrentSSID);
                UIControl();
                Log.e("OnRecive:", "TRUE");
            }
        }
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

            // Go To DInst_Auth  After 20 Time Tap
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
            }
        }

    }

    private void ShowDailog() {

        DInst_Auth adm_dialog = DInst_Auth.newInstance(8, 4f, false, false);
        adm_dialog.show(getSupportFragmentManager(), "admin_dialog");

    }

    // Moin Saadati's Comment : Other Methods
    // 2/10/17 4:51 PM
    @Override
    public void onDestroy() {
        stopService(device_service);
        super.onDestroy();
    }

    @Override
    public void onPause() {
        //stopService(device_service);
        unregisterReceiver(dReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {

        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.Action_DeviceService);

        registerReceiver(dReceiver, intentFilter);
        super.onResume();
    }
}
