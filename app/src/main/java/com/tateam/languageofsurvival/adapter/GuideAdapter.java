package com.tateam.languageofsurvival.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseActivity;
import com.tateam.languageofsurvival.entity.EnglishGuide;

import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {

    private clickListener mlisListener;
    private int type;
    private BaseActivity activity;
    private List<EnglishGuide> listEnglishguide;

    public GuideAdapter(BaseActivity activity, List<EnglishGuide> listEnglishguide, int type) {

        super();
        this.activity = activity;
        this.type = type;
        this.listEnglishguide = new ArrayList<>();
        this.listEnglishguide = listEnglishguide;
    }

    public GuideAdapter(BaseActivity activity, List<EnglishGuide> listEnglishguide) {

        super();
        this.activity = activity;

        this.listEnglishguide = new ArrayList<>();
        this.listEnglishguide = listEnglishguide;
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
        viewHolder.tvEnglish.setText(listEnglishguide.get(i).getEnglishSentence());
        viewHolder.bgEnglish.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
    }

    @Override
    public int getItemCount() {

        return listEnglishguide.size();
    }

    public void setmListener(clickListener mlisListener) {
        this.mlisListener = mlisListener;
    }

    public interface clickListener {
        public void onPhraseClick(int position);

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvEnglish;
        public CardView bgEnglish;

        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            tvEnglish = (TextView) itemView.findViewById(R.id.textEnglish);
            bgEnglish = (CardView) itemView.findViewById(R.id.bg_phrase);
            tvEnglish.setOnClickListener(this);
            //  flatEffectDrawable= (FlatEffectDrawable) itemView.findViewById(R.id.flatButton);
        }


        @Override
        public void onClick(View v) {
            if (mlisListener != null)
                mlisListener.onPhraseClick(getAdapterPosition());
        }
    }
}