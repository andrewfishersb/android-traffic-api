package com.fisher.andrew.castlerock;

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

    private List<TrafficSign> mSigns;

    public TrafficSignAdapter(List<TrafficSign> signs){
        mSigns = signs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traffic_sign,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("Logged","Test" );

        viewHolder.mSignName.setText(mSigns.get(i).getName());
      //todo if true set color to something
    }

    @Override
    public int getItemCount() {
        return mSigns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mSignName;
        private ConstraintLayout mCurrentRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mSignName = itemView.findViewById(R.id.signNameTv);
            mCurrentRow = itemView.findViewById(R.id.trafficItemCl);

            mCurrentRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
}
