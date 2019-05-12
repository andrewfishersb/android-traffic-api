package com.fisher.andrew.castlerock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignAdapter.ViewHolder> {
    private Context mContext;
    private List<TrafficSign> mSigns;

    public TrafficSignAdapter(List<TrafficSign> signs){
        mSigns = signs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_traffic_sign,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView signText = viewHolder.mSignName;
        ConstraintLayout currentRow = viewHolder.mCurrentRow;
        TrafficSign currentSign = mSigns.get(i);

        signText.setText(currentSign.getName());

        if(currentSign.isDisplayingMessage()){
            signText.setTextColor(Color.BLACK);
            Log.d("Message",i+ " Message Displaying");
        }

        currentRow.setOnClickListener(view -> {
            Intent intent = new Intent(mContext,SignInformationActivity.class);
            intent.putExtra("display_message", currentSign.getMessage());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mSigns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mSignName;
        private ConstraintLayout mCurrentRow;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            mSignName = itemView.findViewById(R.id.signNameTv);
            mCurrentRow = itemView.findViewById(R.id.trafficItemCl);

        }
    }
}
