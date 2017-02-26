package ir.suom.srs.seyedmoin.srcremote.DeviceService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
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
import ir.suom.srs.seyedmoin.srcremote.R;
import ir.suom.srs.seyedmoin.srcremote.Socket.Client;
import ir.suom.srs.seyedmoin.srcremote.Socket.SocketResult;

public class DeviceService extends Service {

    private final String TAG = "DeviceService";

    private WifiManager mWifiManager;
    private ScheduledFuture<?> scheduleReaderHandle;
    private ScheduledExecutorService mScheduler;

    private long initial_Delay = 500;
    private long period_Reader = 1000;

    // SharedPreference
    SharedPreferences local_pref;

    Intent intent;

    @Override
    public void onCreate() {
        mWifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        mScheduler = Executors.newScheduledThreadPool(1);

        scheduleReaderHandle = mScheduler.scheduleAtFixedRate(new ScheduleReader(), initial_Delay, period_Reader,
                TimeUnit.MILLISECONDS);

        local_pref = getSharedPreferences(Constants.Pref_Name, MODE_PRIVATE);

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

            intent = new Intent();
            intent.setAction(Constants.Action_DeviceService);

            // Moin Saadati's Comment : Flag for Connecting Status
            // Moin Saadati's Comment : flag = 1 -> Connected
            // Moin Saadati's Comment : flag = 2 -> Connecting
            // Moin Saadati's Comment : flag = 0 -> Connect is Fail
            // 2/18/17 9:40 PM
            int flag;

            if (mWifiManager.isWifiEnabled()) {

                WifiInfo wi = mWifiManager.getConnectionInfo();
                if (wi.getNetworkId() != -1) {
                    Log.e("SSID:", wi.getSSID());
                    if (wi.getSSID().equals("\"" + Constants.AP_NAME_EXAMPLE + "\"")) {
                        //tv_status.setText("Connect To * " + wi.getSSID());
                        flag = 1;
                    } else {
                        mWifiManager.disconnect();
                        //tv_status.setText("Connecting");
                        flag = 2;
                    }

                } else {
                    int netID = SearchInWC(Constants.AP_NAME_EXAMPLE);
                    if (netID != -1) {
                        if (mWifiManager.enableNetwork(netID, true)) {
                            //tv_status.setText("Connect To ** NoBoDy"); means Connecting
                            flag = 2;
                        } else {
                            //tv_status.setText("Not ** Connect");
                            flag = 0;
                        }

                    } else {

                        WifiConfiguration wc = new WifiConfiguration();
                        //wc.hiddenSSID = true;
                        wc.SSID = "\"" + Constants.AP_NAME_EXAMPLE + "\"";
                        String pwd = local_pref.getString(Constants.KEY_Device_ID, "");
                        wc.preSharedKey = "\"" + pwd + "\"";
                        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                        int id = mWifiManager.addNetwork(wc);
                        if (mWifiManager.enableNetwork(id, true)) {
                            //tv_status.setText("Connecte to NoBoDy");
                            Log.e("WC:", "TRUE");
                            flag = 1;

                        } else {
                            //tv_status.setText("Not Connect");
                            flag = 2;
                        }
                    }

                }

            } else {
                //tv_status.setText("wifi is off.");
                flag = 0;
            }

            Log.e("FLAG_SERVICE:", String.valueOf(flag));
            intent.putExtra(Constants.KEY_FLAG, flag);
            sendBroadcast(intent);

        }
    }

    private int SearchInWC(String ssid) {

        List<WifiConfiguration> list_wc = mWifiManager.getConfiguredNetworks();
        for (WifiConfiguration wc : list_wc) {
            if (wc.SSID.equals("\"" + ssid + "\"")) {
                return wc.networkId;
            }
        }
        return -1;
    }


}