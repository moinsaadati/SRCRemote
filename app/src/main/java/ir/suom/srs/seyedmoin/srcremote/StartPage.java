package ir.suom.srs.seyedmoin.srcremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;

import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.WifiData;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.WifiDataNetwork;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.WifiService;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class StartPage extends AppCompatActivity {

    // Moin Saadati's Comment : Start For First time and check Wifi and so Scan Barcode
    // 2/10/17 3:11 PM
    TextView tv_TurnOnLocation, tv_wifi_off;
    Button btn_Scan;

    private WifiData mWifiData;

    public static final String BARCODE_KEY = "BARCODE";
    private Barcode barcodeResult;

    private WifiManager wifiManager;

    Intent wifi_service;

    GifDrawable gifDrawable;
    GifImageView start_gif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mWifiData = null;

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        // Set Receiver
        WifiReceiver mReceiver = new WifiReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(Constants.APP_NAME));

        // Launch WiFi service
        wifi_service = new Intent(this, WifiService.class);
        startService(wifi_service);

        // Recover Retained object
        mWifiData = (WifiData) getLastNonConfigurationInstance();

        setContentView(R.layout.activity_start_page);

        Initialization();

        start_gif.setImageDrawable(gifDrawable);


        //logNetworks();

        btn_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartScan();
            }
        });

        if (savedInstanceState != null) {
            Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
            if (restoredBarcode != null) {
                barcodeResult = restoredBarcode;

                Intent barcodeAct = new Intent(StartPage.this, MainPage.class);
                barcodeAct.putExtra("response", barcodeResult.rawValue);
                barcodeAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(barcodeAct);
                finish();
            }
        }

    }

    private void Initialization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));

        btn_Scan = (Button) findViewById(R.id.btn_scanBarcode);
        tv_TurnOnLocation = (TextView) findViewById(R.id.tv_turn_on_location);
        tv_wifi_off = (TextView) findViewById(R.id.tv_wifi_off);

        btn_Scan.setTypeface(tp);
        tv_TurnOnLocation.setTypeface(tp);
        tv_wifi_off.setTypeface(tp);

        start_gif = (GifImageView) findViewById(R.id.start_gif);
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.start_gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gifDrawable.setLoopCount(1);

    }

    // Moin Saadati's Comment : Method For Scan Wifi
    // 2/10/17 4:26 PM
    private void logNetworks() {
        Log.d("logNetworks:", "--------------------------");

        if (mWifiData.getNetworks().size() == 0) {
            Log.e("logNetworks:", "Turn On Location");
            tv_TurnOnLocation.setVisibility(View.VISIBLE);
            btn_Scan.setVisibility(View.INVISIBLE);
            tv_wifi_off.setVisibility(View.INVISIBLE);
        } else {
            tv_TurnOnLocation.setVisibility(View.INVISIBLE);
            btn_Scan.setVisibility(View.VISIBLE);
            tv_wifi_off.setVisibility(View.INVISIBLE);
        }
        for (WifiDataNetwork sr : mWifiData.getNetworks()) {
            Log.d("logNetworks:", sr.getSsid());
            if (sr.getSsid().equals(Constants.AP_NAME_EXAMPLE)) {
                tv_TurnOnLocation.setVisibility(View.INVISIBLE);
                btn_Scan.setVisibility(View.VISIBLE);
                tv_wifi_off.setVisibility(View.INVISIBLE);
            }
        }

    }

    public class WifiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            WifiData data = (WifiData) intent.getParcelableExtra(Constants.WIFI_DATA);
            if (data != null) {
                mWifiData = data;
                logNetworks();
            }
        }
    }

    // Moin Saadati's Comment : Methods For Scan Barcode
    // 2/10/17 3:57 PM
    private void StartScan() {
        /** * Build a new MaterialBarcodeScanner */
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(StartPage.this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        barcodeResult = barcode;

                        Intent i = new Intent(StartPage.this, MainPage.class);
                        i.putExtra("response", barcodeResult.rawValue);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BARCODE_KEY, barcodeResult);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MaterialBarcodeScanner.RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            StartScan();
            return;
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(android.R.string.ok, listener)
                .show();
    }


    // Moin Saadati's Comment : Other Methods
    // 2/10/17 4:51 PM
    @Override
    public void onPause() {
        stopService(wifi_service);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        stopService(wifi_service);
        super.onDestroy();
    }
}