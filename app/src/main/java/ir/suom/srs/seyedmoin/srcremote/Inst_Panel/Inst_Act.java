package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ir.suom.srs.seyedmoin.srcremote.R;

public class Inst_Act extends AppCompatActivity {

    // Moin Saadati's Comment : Installer Panel
    // 2/14/17 5:18 PM
    Button btn_option1, btn_option2, btn_option3, btn_option4;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst);

        Initilization();

        btn_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activity for Set Device
                Toast.makeText(getBaseContext(), "Set Device", Toast.LENGTH_SHORT).show();
            }
        });

        btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialog for ResetDevice
                Toast.makeText(getBaseContext(), "Reset Device", Toast.LENGTH_SHORT).show();
                ResetDevice();
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
                Toast.makeText(getBaseContext(), "Show Remotes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ResetDevice() {
    }

    private void ShowDeviceID() {
        Toast.makeText(getBaseContext(), "Device ID", Toast.LENGTH_SHORT).show();
    }


    private void Initilization() {

        btn_option1 = (Button) findViewById(R.id.btn_option_1);
        btn_option2 = (Button) findViewById(R.id.btn_option_2);
        btn_option3 = (Button) findViewById(R.id.btn_option_3);
        btn_option4 = (Button) findViewById(R.id.btn_option_4);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

    }

}
