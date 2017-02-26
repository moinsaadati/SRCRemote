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

import ir.suom.srs.seyedmoin.srcremote.Model.remote;
import ir.suom.srs.seyedmoin.srcremote.R;

public class Adapter_Remote extends RecyclerView.Adapter<Adapter_Remote.ViewHolder> {

    Context mContext;
    ArrayList<remote> remotes;

    OnItemClickListener mItemClickListener;

    // 2
    public Adapter_Remote(Context context) {
        this.mContext = context;
    }

    public void setData(ArrayList<remote> remotes) {
        this.remotes = remotes;
    }

    // 3
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public FrameLayout fl_mainHolder;
        public TextView tv_remote_title;

        public ViewHolder(View itemView) {
            super(itemView);

            fl_mainHolder = (FrameLayout) itemView.findViewById(R.id.fl_mainHolder);
            tv_remote_title = (TextView) itemView.findViewById(R.id.tv_remote_title);

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
        return remotes.size();
    }

    // 2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_item_remote, parent, false);
        return new ViewHolder(view);
    }

    // 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        remote rem = remotes.get(position);
        Typeface tp = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.font_iransanz));
        holder.tv_remote_title.setText(rem.getTitle());
        holder.tv_remote_title.setTypeface(tp);
    }
}