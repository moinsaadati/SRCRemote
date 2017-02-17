package ir.suom.srs.seyedmoin.srcremote.Dialog;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;
import ir.suom.srs.seyedmoin.srcremote.R;

/**
 * Created by seyedmoin on 2/17/17.
 */

public class DShow_DID extends SupportBlurDialogFragment {

    // Moin Saadati's Comment : Dialog For ResetDevice
    // 2/17/17 7:11 PM

    TextView tv_title_show_did, tv_show_did, tv_device_id;

    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";
    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;

    public static DShow_DID newInstance(int radius, float downScaleFactor, boolean dimming, boolean debug) {
        DShow_DID fragment = new DShow_DID();
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

        //tp = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_iransanz));
        View rootView = inflater.inflate(R.layout.layout_dialog_show_did, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz));

        tv_device_id = (TextView) view.findViewById(R.id.tv_device_id);
        tv_show_did = (TextView) view.findViewById(R.id.tv_show_did);
        tv_title_show_did = (TextView) view.findViewById(R.id.tv_title_show_did);

        tv_device_id.setTypeface(tp);
        tv_title_show_did.setTypeface(tp);
        tv_show_did.setTypeface(tp);

        tv_device_id.setText("6SD78SDGV4zxi");


    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.animation_up_down;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


}
