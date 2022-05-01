package com.omega.truecaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omega.truecaller.R;
import com.omega.truecaller.data.model.Call;

import java.util.List;


public class LogCallAdapterHorizontal extends RecyclerView.Adapter<LogCallAdapterHorizontal.viewHolder> {


    public LogCallAdapterHorizontal(Context context, List<Call> mList) {
        this.context = context;
        this.mList = mList;
    }

    private Context context;
    private List<Call> mList;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_log_call_horizontal, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;

    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Call call = mList.get(position);
        holder.mNameUser.setText(call.getmNameUser());
        holder.mTypePhone.setText(R.string.type_number_phone);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView mNameUser;
        TextView mTypeCall;
        TextView mTypePhone;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mNameUser = itemView.findViewById(R.id.tv_name_or_phone_log_call);
            mTypePhone=itemView.findViewById(R.id.tv_type_phone_log_call);
        }
    }
}
