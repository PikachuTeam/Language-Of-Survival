package com.tateam.frenchsurvivalphrases.tabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;

import java.util.List;


public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    private ClickListener mlisListener;
    private List<EnglishGuide> list;


    public RecentAdapter(List<EnglishGuide> list) {
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_guide_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.englishText.setText(list.get(i).getEnglishSentence());




    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView englishText;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
           englishText = (TextView) itemView.findViewById(R.id.textEnglish);


            englishText.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mlisListener != null) {
                if (v == itemView){
//                    v.setClickable(false);
                    mlisListener.ItemClicked(getAdapterPosition());
                }

            }
        }

    }



    public interface ClickListener {
        public void ItemClicked(int position);

       // public void FavoriteChange(int position);
    }

    public void setMlisListener(ClickListener mlisListener) {
        this.mlisListener = mlisListener;

    }
}