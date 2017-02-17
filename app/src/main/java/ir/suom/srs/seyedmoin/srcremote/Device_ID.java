package ir.suom.srs.seyedmoin.srcremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Device_ID extends AppCompatActivity {

    // Moin Saadati's Comment : Enter Device ID
    // 2/10/17 6:42 PM
    Button btn_Config;
    EditText et_device_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activity_device_id);

        Initialization();

        btn_Config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Moin Saadati's Comment : Go To MainPage
                // 2/10/17 6:47 PM
                // Save ScanBarcode On Pref
                Intent MainAct = new Intent(Device_ID.this, MainPage.class);
                MainAct.putExtra("response", Validation_ID_Device());
                MainAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(MainAct);
                finish();
            }
        });

    }

    private void Initialization() {

        btn_Config = (Button) findViewById(R.id.btn_config);
        et_device_id = (EditText) findViewById(R.id.et_deviceID);
    }

    private String Validation_ID_Device() {
        return et_device_id.getText().toString();
    }
}
