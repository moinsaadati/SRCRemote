package ir.suom.srs.seyedmoin.srcremote.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.suom.srs.seyedmoin.srcremote.Inst_Panel.Inst_Act;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Adm_Dialog extends DialogFragment {

    // Moin Saadati's Comment : Dialog For Authentication Instellar
    // 2/14/17 3:30 PM
    private EditText et_pwd;
    private TextView tv_please_enter_sec_code;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //tp = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_iransanz));
        View rootView = inflater.inflate(R.layout.fragment_adm__dialog, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Typeface tp = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.font_iransanz));

        et_pwd = (EditText) view.findViewById(R.id.et_pwd_adm);
        tv_please_enter_sec_code = (TextView) view.findViewById(R.id.tv_please_enter_sec_code);

        et_pwd.setTypeface(tp);
        tv_please_enter_sec_code.setTypeface(tp);

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // nothing
                String pwd = et_pwd.getText().toString();
                // Go To Installer Panel
                if (pwd.equals("1234")) {
                    Toast.makeText(getActivity().getBaseContext(), "You Are Installer", Toast.LENGTH_SHORT).show();
                    Intent installer_act = new Intent(getActivity(), Inst_Act.class);
                    startActivity(installer_act);
                    getDialog().dismiss();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        });

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
