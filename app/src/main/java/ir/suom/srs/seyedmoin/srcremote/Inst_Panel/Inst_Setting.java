package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Remote;
import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Setting;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;
import ir.suom.srs.seyedmoin.srcremote.Dialog.DSetting;
import ir.suom.srs.seyedmoin.srcremote.Model.remote;
import ir.suom.srs.seyedmoin.srcremote.Model.setting;
import ir.suom.srs.seyedmoin.srcremote.R;
import ir.suom.srs.seyedmoin.srcremote.Socket.Client;
import ir.suom.srs.seyedmoin.srcremote.Socket.Connections;
import ir.suom.srs.seyedmoin.srcremote.Socket.SocketResult;

public class Inst_Setting extends AppCompatActivity implements Adapter_Setting.OnItemClickListener, DSetting.CallBacks {

    // Moin Saadati's Comment : Inst_Setting - For Setting Devcie
    // 2/25/17 2:01 PM
    RecyclerView rv_settings;
    TextView tv_main_setting_title;
    ImageView iv_icon_back;
    Button btn_send_settings, btn_get_settings;

    private Adapter_Setting adapter_setting;
    private ArrayList<setting> Settings_ITEMS;
    ArrayList<String> Encode_Settings;

    SharedPreferences local_pref;
    SharedPreferences.Editor local_pref_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst__setting);

        Initilization();

    }

    private void Initilization() {

        local_pref = getSharedPreferences(Constants.Pref_Name, MODE_PRIVATE);
        local_pref_edit = local_pref.edit();

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));

        btn_get_settings = (Button) findViewById(R.id.btn_get_settings);
        btn_send_settings = (Button) findViewById(R.id.btn_send_settings);
        iv_icon_back = (ImageView) findViewById(R.id.iv_icon_back);
        tv_main_setting_title = (TextView) findViewById(R.id.tv_main_setting_title);

        tv_main_setting_title.setTypeface(tp);
        btn_send_settings.setTypeface(tp);
        btn_get_settings.setTypeface(tp);

        rv_settings = (RecyclerView) findViewById(R.id.rv_settings);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_settings.setLayoutManager(llm);

        adapter_setting = new Adapter_Setting(this);
        adapter_setting.setOnItemClickListener(this);
        adapter_setting.setData(Init_Data_Option());
        //ar.setData(getSettingOfDevice());

        rv_settings.setAdapter(adapter_setting);

        iv_icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_get_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSettingOfDevice();
                adapter_setting.notifyDataSetChanged();
            }
        });
        btn_send_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DefineSocketSend(postSettingsOfApp());
            }
        });

    }

    private ArrayList<setting> Init_Data_Option() {

        String[] setting_items = getResources().getStringArray(R.array.setting_option);

        ArrayList<setting> settings = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            settings.add(new setting(setting_items[i], local_pref.getString("opt" + i, getString(R.string.no_set))));
        }
        return settings;
    }

    // Moin Saadati's Comment : On Click Item RecycleView Setting for Set Dialog
    // 2/25/17 4:16 PM
    @Override
    public void onItemClick(View view, int position) {

        // Show Dialog for set option
        DSetting Dsetting_properties = DSetting.newInstance(10, 22f, false, false, position);
        Dsetting_properties.show(getSupportFragmentManager(), "Dsetting");

    }

    // Moin Saadati's Comment : Send Request For GET/POST Setting of/to Device
    // 2/26/17 9:45 PM
    public ArrayList<setting> getSettingOfDevice() {

        DefineSocketSend(Constants.GET_Settings);

        String[] setting_items = getResources().getStringArray(R.array.setting_option);
        ArrayList<setting> settings = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            settings.add(new setting(setting_items[i], Encode_Settings.get(i)));
        }
        return settings;
    }

    private String postSettingsOfApp() {
        String s = "$setting-";
        for (int i = 0; i < 23; i++) {
            s += "000" + "-";//getOfSPreference();
        }
        s += "$";
        return s;
    }

    // Moin Saadati's Comment : Methods For Connect To Device and Send Massage
    // 2/24/17 7:28 PM
    private void DefineSocketSend(final String msg) {

        Client myClient = new Client(Constants.IP_Address
                , Integer.parseInt(Constants.PORT)
                , new SocketResult() {
            @Override
            public void onResponse(String result) {

                Log.e("Response:", result);
                Encode_Settings = Connections.EncodeSettings(result);
                SaveSettingsToSP(Encode_Settings);
            }
        });
        myClient.execute(msg);

    }

    private void SaveSettingsToSP(ArrayList<String> encode_settings) {
        // Save To Shared Preference
        for (int i = 0; i < 23; i++) {
            local_pref_edit.putString("opt" + i, encode_settings.get(i));
        }
        local_pref_edit.commit();

    }

    @Override
    public void OnComplateLoginDialog() {
        //Reload Recycleiew
        Log.e("COMPLATE:", "TTT");
        adapter_setting.Swap(Init_Data_Option());
    }
}
