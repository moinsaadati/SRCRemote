package ir.suom.srs.seyedmoin.srcremote;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class OrgActivity extends AppCompatActivity {

    // Moin Saadati's Comment : Organize Start Activity : StartPage Or MainPage
    // Moin Saadati's Comment : request Perission
    // 2/10/17 3:09 PM
    private static final int REQUEST_GLOBAL_PERMINSSIONS_CODE = 1;
    private static String[] global_permissions = new String[]{
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(global_permissions, REQUEST_GLOBAL_PERMINSSIONS_CODE);
        } else {

            Intent nextAct;

            //String pwd = local_pref.getString(Constants.KEY_Device_ID, null);
            //Log.e("PWD:", local_pref.getString(Constants.KEY_Device_ID, "null"));
            /*if (local_pref.getString(Constants.KEY_Device_ID, null) == null) {
                nextAct = new Intent(this, StartPage.class);
            } else {
                nextAct = new Intent(this, MainPage.class);
            }*/
            nextAct = new Intent(this, StartPage.class);
            nextAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(nextAct);
            finish(); // finish activity
        }

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GLOBAL_PERMINSSIONS_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // The requested permission is granted.
                    Log.d("onRequestPermissionsResult:", "Grant");
                    Intent nextAct = new Intent(this, StartPage.class);
                    nextAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(nextAct);
                    finish(); // finish activity
                } else {
                    finish();
                }
            }
        }
    }
}
