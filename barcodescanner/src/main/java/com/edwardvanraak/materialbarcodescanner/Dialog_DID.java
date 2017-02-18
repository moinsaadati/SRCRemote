package com.edwardvanraak.materialbarcodescanner;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


public class Dialog_DID extends DialogFragment {

    // Moin Saadati's Comment : Dialog For Enter Devcie ID
    // 2/17/17 5:58 PM
    TextView tv_enter_device_id, tv_help_edi;
    EditText et_device_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //tp = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_iransanz));

        View rootView = inflater.inflate(R.layout.layout_dialog_did, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz));

        tv_enter_device_id = (TextView) view.findViewById(R.id.tv_enter_device_id);
        tv_help_edi = (TextView) view.findViewById(R.id.tv_help_edi);
        et_device_id = (EditText) view.findViewById(R.id.et_device_id);

        tv_help_edi.setTypeface(tp);
        tv_enter_device_id.setTypeface(tp);

        et_device_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String et = et_device_id.getText().toString();
                if (et.endsWith("@")) {
                    Intent main_act = new Intent("ir.suom.srs.seyedmoin.srcremote.MainPage");
                    main_act.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    main_act.putExtra("response", et.substring(0, et.length() - 2));
                    startActivity(main_act);
                    getActivity().finish();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


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
