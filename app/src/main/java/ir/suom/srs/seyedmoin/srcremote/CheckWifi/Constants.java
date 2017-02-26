package ir.suom.srs.seyedmoin.srcremote.CheckWifi;

public class Constants {
    public static final String APP_NAME = "ANDROID_WIFI_SCANNER";

    // Wifi Service
    public static final String Wifi_Service_INTENT_FILTER = "ANDROID_WIFI_SCANNER";
    public static final String KEY_WIFI_DATA = "WIFI_DATA";

    // Device Service
    public static String Action_DeviceService = "device_service";
    public static final String KEY_FLAG = "flag_connect";
    public static final String KEY_RESULT = "result_socket";
    public static final String AP_NAME = "parking";
    public static final String AP_NAME_EXAMPLE = "test_src";
    public static final String AP_NAME_EXAMPLE1 = "test";
    public static final String AP_NAME_EXAMPLE2 = "seyedmoin";

    // Socket Properties
    public static final String IP_Address = "10.42.0.98";
    public static final String PORT = "8080";

    // Preference Properties
    public static final String Pref_Name = "local_pref";
    public static final String KEY_Device_ID = "key_device_id";
    public static final String KEY_Device_Name = "key_device_name";
    public static final String KEY_IP_Address = "key_ip_address";
    public static final String KEY_Port = "key_port";

    public static String PIN_INST = "10211021";

    // REQUEST FOR USER
    public static String GET_DOOR_STATUS = "$door-status$";
    public static String POST_DOOR = "$door$";
    public static String STATUS_OPENED = "opened$";
    public static String STATUS_CLOSED = "closed$";
    public static String STATUS_OPENNING = "openning$";
    public static String STATUS_CLOSING = "closing$";
    public static String STATUS_STOPPED_OPEN = "stopped_open$";
    public static String STATUS_STOPPED_CLOSE = "stopped_clos$";

    // REQEST FOR INSTALLER
}
