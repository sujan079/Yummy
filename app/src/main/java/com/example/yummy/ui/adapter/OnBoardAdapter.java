package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.OnBoardModel;

import java.util.ArrayList;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.OnBoardScreenViewHolder> {



    private ArrayList<OnBoardModel> onBoardModels;

    public OnBoardAdapter(ArrayList<OnBoardModel> onBoardModels) {
        this.onBoardModels = onBoardModels;
    }

    public void setOnBoardModels(ArrayList<OnBoardModel> onBoardModels) {
        this.onBoardModels = onBoardModels;
    }

    @NonNull
    @Override
    public OnBoardScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onboard_item, parent, false);
        OnBoardScreenViewHolder vh = new OnBoardScreenViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardScreenViewHolder holder, int position) {
        holder.setData(onBoardModels.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardModels.size();
    }


    static class OnBoardScreenViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_onboard;
        private TextView tv_title, tv_desc;

        public OnBoardScreenViewHolder(@NonNull View itemView) {
            super(itemView);

            img_onboard = itemView.findViewById(R.id.img_on_board);
            tv_title = itemView.findViewById(R.id.tv_on_board_title);
            tv_desc = itemView.findViewById(R.id.tv_on_board_des);
        }

        public void setData(OnBoardModel onBoardModel) {
            img_onboard.setImageResource(onBoardModel.getImg_id());
            tv_title.setText(onBoardModel.getTitle());
            tv_desc.setText(onBoardModel.getDescription());
        }
    }
}


