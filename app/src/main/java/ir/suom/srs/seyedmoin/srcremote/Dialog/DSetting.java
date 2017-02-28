package ir.suom.srs.seyedmoin.srcremote.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;
import ir.suom.srs.seyedmoin.srcremote.CheckWifi.Constants;
import ir.suom.srs.seyedmoin.srcremote.R;

import static android.content.Context.CLIPBOARD_SERVICE;
import static ir.suom.srs.seyedmoin.srcremote.R.id.tv_device_id;

public class DSetting extends SupportBlurDialogFragment {

    // Moin Saadati's Comment : Dialog For ResetDevice
    // 2/17/17 7:11 PM

    TextView tv_setting_tittle, tv_setting_description;
    Button btn_back_and_save;
    DiscreteSeekBar dissb_set;

    CallBacks Call;

    // BlurDialog
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";

    // Properties Setting
    private static final String BUNDLE_KEY_SETTING_INDEX = "bundle_key_setting_index";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private int setting_index;

    // SharedPreference
    SharedPreferences local_pref;
    SharedPreferences.Editor local_pref_edit;

    public static DSetting newInstance(int radius, float downScaleFactor
            , boolean dimming, boolean debug
            , int setting_index) {
        DSetting fragment = new DSetting();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_BLUR_RADIUS, radius);
        args.putFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR, downScaleFactor);
        args.putBoolean(BUNDLE_KEY_DIMMING, dimming);
        args.putBoolean(BUNDLE_KEY_DEBUG, debug);

        // Get Properties
        args.putInt(BUNDLE_KEY_SETTING_INDEX, setting_index);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
        setting_index = args.getInt(BUNDLE_KEY_SETTING_INDEX);

        this.Call = (CallBacks) activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        local_pref = getActivity().getSharedPreferences(Constants.Pref_Name, Context.MODE_PRIVATE);
        local_pref_edit = local_pref.edit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_set_setting, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz));
        dissb_set = (DiscreteSeekBar) view.findViewById(R.id.dissb_set);
        tv_setting_tittle = (TextView) view.findViewById(R.id.tv_setting_title);
        tv_setting_description = (TextView) view.findViewById(R.id.tv_setting_description);
        btn_back_and_save = (Button) view.findViewById(R.id.btn_back_setting);

        tv_setting_description.setTypeface(tp);
        tv_setting_tittle.setTypeface(tp);
        btn_back_and_save.setTypeface(tp);

        String[] setting_tittles = getResources().getStringArray(R.array.setting_option);
        String[] setting_descriptions = getResources().getStringArray(R.array.setting_option_description);

        tv_setting_tittle.setText(setting_tittles[setting_index]);
        tv_setting_description.setText(setting_descriptions[setting_index]);

        btn_back_and_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local_pref_edit.putString("opt" + setting_index, String.valueOf(dissb_set.getProgress()));
                local_pref_edit.commit();
                dismiss();
                Call.OnComplateLoginDialog();
            }
        });

    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.animation_fade_in_out;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    // Moin Saadati's Comment : For Save And Reload ListSetting
    // 2/27/17 2:21 PM
    public interface CallBacks {
        public void OnComplateLoginDialog();
    }

}
