package ir.suom.srs.seyedmoin.srcremote.Inst_Panel;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.suom.srs.seyedmoin.srcremote.Adapter.Adapter_Remote;
import ir.suom.srs.seyedmoin.srcremote.Model.remote;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Inst_Remotes extends AppCompatActivity {

    RecyclerView rv_remotes;
    TextView tv_my_remotes;
    ImageView iv_icon_back;
    ImageButton ibtn_add_remotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst__remotes);

        Initilization();

        ibtn_add_remotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), R.string.not_access_to_add_remote, Toast.LENGTH_SHORT).show();
            }
        });
        ibtn_add_remotes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getBaseContext(), R.string.add_remote, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void Initilization() {

        Typeface tp = Typeface.createFromAsset(getAssets(), getString(R.string.font_iransanz));

        ibtn_add_remotes = (ImageButton) findViewById(R.id.ibtn_add_remote);
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

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getBaseContext(), "Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(getBaseContext(), "Swiped", Toast.LENGTH_SHORT).show();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(rv_remotes);


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
