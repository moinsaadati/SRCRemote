package ir.suom.srs.seyedmoin.srcremote.Socket;

import java.util.ArrayList;

import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;


public class Connections {

    // Moin Saadati's Comment : Class For Methods Connection For Device
    // 2/26/17 10:03 PM
    public static ArrayList<String> EncodeSettings(String response) {

        ArrayList<String> parametrs = new ArrayList<>();
        if (response != null && response.startsWith(Constants.Response_Setting_PREFIX)) {
            int j = 8;
            for (int i = 0; i < 23; i++) {
                String opt = response.substring(j);
                if (i == 22) {
                    opt = response.substring(j, response.length() - 2);
                }
                parametrs.add(opt);
                j += 4;
            }
        }
        return parametrs;
    }

}
