package ir.suom.srs.seyedmoin.srcremote.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import ir.suom.srs.seyedmoin.srcremote.Model.setting;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Adapter_Setting extends RecyclerView.Adapter<Adapter_Setting.ViewHolder> {

    Context mContext;
    ArrayList<setting> settings;

    OnItemClickListener mItemClickListener;

    // 2
    public Adapter_Setting(Context context) {
        this.mContext = context;
    }

    public void setData(ArrayList<setting> settings) {
        this.settings = settings;
    }

    // 3
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public FrameLayout fl_mainHolder;
        public TextView tv_setting_title;
        public TextView tv_setting_number;

        public ViewHolder(View itemView) {
            super(itemView);

            fl_mainHolder = (FrameLayout) itemView.findViewById(R.id.fl_mainHolder);
            tv_setting_title = (TextView) itemView.findViewById(R.id.tv_setting_title);
            tv_setting_number = (TextView) itemView.findViewById(R.id.tv_setting_number);

            fl_mainHolder.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    // 2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_item_setting, parent, false);
        return new ViewHolder(view);
    }

    // 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setting set = settings.get(position);

        Typeface tp = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.font_iransanz));

        holder.tv_setting_number.setTypeface(tp);
        holder.tv_setting_title.setTypeface(tp);

        holder.tv_setting_number.setText(set.getNumber());
        holder.tv_setting_title.setText(set.getTittle());

    }

    public void Swap(ArrayList<setting> settings) {
        this.settings.clear();
        this.settings.addAll(settings);
        notifyDataSetChanged();
    }

}