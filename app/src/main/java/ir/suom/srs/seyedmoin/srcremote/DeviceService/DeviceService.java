package ir.suom.srs.seyedmoin.srcremote.DeviceService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;

public class DeviceService extends Service {

    private final String TAG = "DeviceService";

    private WifiManager mWifiManager;
    private ScheduledFuture<?> scheduleReaderHandle;
    private ScheduledExecutorService mScheduler;

    private long initial_Delay = 500;
    private long period_Reader = 1000;

    @Override
    public void onCreate() {
        mWifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        mScheduler = Executors.newScheduledThreadPool(1);

        scheduleReaderHandle = mScheduler.scheduleAtFixedRate(new ScheduleReader(), initial_Delay, period_Reader,
                TimeUnit.MILLISECONDS);


        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID.equals("\"" + Constants.AP_NAME_EXAMPLE + "\"")) {
                Log.e("Remove_WC", "True");
                mWifiManager.removeNetwork(i.networkId);
            }
            mWifiManager.saveConfiguration();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // stop read thread
        scheduleReaderHandle.cancel(true);
        mScheduler.shutdown();
        super.onDestroy();

    }

    class ScheduleReader implements Runnable {
        @Override
        public void run() {

            Log.e("Service", "True");

            Intent intent = new Intent();
            intent.setAction(Constants.Action_DeviceService);

            if (mWifiManager.isWifiEnabled()) {

                if (ConnectToDevice()) {
                    intent.putExtra(Constants.KEY_CurrentSSID, Constants.AP_NAME_EXAMPLE);
                } else {
                    intent.putExtra(Constants.KEY_CurrentSSID, "");
                }

            } else {
                mWifiManager.setWifiEnabled(true);
            }

            sendBroadcast(intent);

        }
    }

    // Moin Saadati's Comment : Methods For Connect To Device
    // 2/14/17 4:04 PM
    private boolean ConnectToDevice() {

        WifiConfiguration wc = new WifiConfiguration();

        //wc.hiddenSSID = true;
        wc.SSID = "\"" + Constants.AP_NAME_EXAMPLE + "\"";
        wc.preSharedKey = "\"" + Constants.PWD + "\"";
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

        int id = mWifiManager.addNetwork(wc);

        //wifiManager.disconnect();
        if (mWifiManager.enableNetwork(id, true)) {
            Log.e("Connect To Device:", "True");
            return true;
        } else {
            Log.e("Connect To Device:", "False");
            return false;
        }
    }
}