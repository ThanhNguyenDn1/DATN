package com.omega.truecaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omega.truecaller.R;
import com.omega.truecaller.callbacks.OnClickCall;
import com.omega.truecaller.data.model.Call;

import java.util.List;


public class LogCallAdapterVertiacal extends RecyclerView.Adapter<LogCallAdapterVertiacal.viewHolder> {
    private OnClickCall onClickCall;

    public LogCallAdapterVertiacal(OnClickCall onClickCall, Context context, List<Call> mList) {
        this.onClickCall = onClickCall;
        this.context = context;
        this.mList = mList;
    }

    private Context context;
    private List<Call> mList;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_log_call_vertical, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;

    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Call call = mList.get(position);
        if (call.getmNameUser().equals(""))
            holder.mNameUser.setText(call.getmNumberPhone());
        else
            holder.mNameUser.setText(call.getmNameUser());
        holder.mTypeCall.setText(call.getCallType());
        holder.mTimeCall.setText(call.getTimeCall());
        holder.itemView.setOnClickListener(view -> {
            onClickCall.clickCall(call);
        });
        holder.mDetailCall.setOnClickListener(view -> {
            onClickCall.clickCallDetail(call);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView mNameUser;
        TextView mTypeCall;
        TextView mTimeCall;
        CardView mDetailCall;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mNameUser = itemView.findViewById(R.id.tv_name_or_phone_log_call);
            mTypeCall = itemView.findViewById(R.id.tv_type_log_call);
            mTimeCall = itemView.findViewById(R.id.tv_time_log_call);
            mDetailCall = itemView.findViewById(R.id.cv_detail_call);
        }

    }
}
