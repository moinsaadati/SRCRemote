package ir.suom.srs.seyedmoin.srcremote.CheckWifi;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class WifiService extends Service {

    private final String TAG = "WifiService";

    private WifiManager mWifiManager;
    private ScheduledFuture<?> scheduleReaderHandle;
    private ScheduledExecutorService mScheduler;
    private WifiData mWifiData;

    private long initial_Delay = 500;
    private long period_Reader = 2000;

    /**
     * It creates a new Thread that it is executed periodically reading the last
     * scanning of WiFi networks (if WiFi is available).
     */
    @Override
    public void onCreate() {
        mWifiData = new WifiData();
        mWifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        mScheduler = Executors.newScheduledThreadPool(1);

        scheduleReaderHandle = mScheduler.scheduleAtFixedRate(new ScheduleReader(), initial_Delay, period_Reader,
                TimeUnit.MILLISECONDS);
    }

    /**
     * Kills the periodical Thread before destroying the service
     */
    @Override
    public void onDestroy() {
        // stop read thread
        scheduleReaderHandle.cancel(true);
        mScheduler.shutdown();
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Performs a periodical read of the WiFi scan result, then it creates a new
     * {@link WifiData()} object containing the list of networks and finally it
     * sends it to the main activity for being displayed.
     */
    class ScheduleReader implements Runnable {
        @Override
        public void run() {

            Log.e("WIFI", "True");

            if (mWifiManager.isWifiEnabled()) {

                // get networks
                List<ScanResult> mResults = mWifiManager.getScanResults();
                //Log.d(TAG, "New scan result: (" + mResults.size() + ") networks found");

                // Moin Saadati's Comment : if @link mResults.size() == 0 : MSG : Turn On Location
                // 2/10/17 4:16 PM
                // Store Networks
                mWifiData.addNetworks(mResults);

            }

            // send data to UI
            Intent intent = new Intent(Constants.Wifi_Service_INTENT_FILTER);
            intent.putExtra(Constants.KEY_WIFI_DATA, mWifiData);
            LocalBroadcastManager.getInstance(WifiService.this).sendBroadcast(intent);

        }
    }
}
