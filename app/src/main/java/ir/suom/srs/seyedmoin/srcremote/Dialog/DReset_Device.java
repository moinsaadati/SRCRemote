package ir.suom.srs.seyedmoin.srcremote.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;
import ir.suom.srs.seyedmoin.srcremote.Inst_Panel.Inst_Act;
import ir.suom.srs.seyedmoin.srcremote.R;

/**
 * Created by seyedmoin on 2/17/17.
 */

public class DReset_Device extends SupportBlurDialogFragment {

    // Moin Saadati's Comment : Dialog For ResetDevice
    // 2/17/17 7:11 PM

    TextView tv_title_reset_device, tv_for_reset_device;
    EditText et_reset;
    Button btn_reset_factory;

    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";
    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;

    public static DReset_Device newInstance(int radius, float downScaleFactor, boolean dimming, boolean debug) {
        DReset_Device fragment = new DReset_Device();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_BLUR_RADIUS, radius);
        args.putFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR, downScaleFactor);
        args.putBoolean(BUNDLE_KEY_DIMMING, dimming);
        args.putBoolean(BUNDLE_KEY_DEBUG, debug);

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
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_dialog_reset_device, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz));
        Typeface tp_bold = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz_bold));

        tv_for_reset_device = (TextView) view.findViewById(R.id.tv_for_reset_device);
        tv_title_reset_device = (TextView) view.findViewById(R.id.tv_title_reset_device);
        et_reset = (EditText) view.findViewById(R.id.et_reset_device);
        btn_reset_factory = (Button) view.findViewById(R.id.btn_reset_factory);

        tv_for_reset_device.setTypeface(tp);
        tv_title_reset_device.setTypeface(tp_bold);
        btn_reset_factory.setTypeface(tp);

        btn_reset_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset Factory Device
                if (et_reset.getText().toString().equals("Reset") || et_reset.getText().toString().equals("reset"))
                    Toast.makeText(getActivity().getBaseContext(), R.string.reset_device_successfully, Toast.LENGTH_SHORT).show();
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


}
