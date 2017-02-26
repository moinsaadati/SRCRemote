package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Remote;
import ir.suom.srs.seyedmoin.srcremote.Model.remote;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Inst_Remotes extends AppCompatActivity {

    RecyclerView rv_remotes;
    TextView tv_my_remotes;
    ImageView iv_icon_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst__remotes);

        Initilization();

    }

    private void Initilization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));

        iv_icon_back = (ImageView) findViewById(R.id.iv_icon_back);
        tv_my_remotes = (TextView) findViewById(R.id.tv_my_remotes);
        tv_my_remotes.setTypeface(tp);

        rv_remotes = (RecyclerView) findViewById(R.id.rv_remotes);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_remotes.setLayoutManager(llm);

        Adapter_Remote ar = new Adapter_Remote(this);
        ar.setData(Init_Data_Remote());

        rv_remotes.setAdapter(ar);

        iv_icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private ArrayList<remote> Init_Data_Remote() {
        ArrayList<remote> remotes = new ArrayList<>();
        remotes.add(new remote("ریموت ۱"));
        remotes.add(new remote("ریموت ۲"));
        remotes.add(new remote("ریموت ۳"));
        remotes.add(new remote("ریموت ۴"));
        return remotes;
    }
}
