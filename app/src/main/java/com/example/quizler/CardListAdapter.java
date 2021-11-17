package com.example.quizler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter {
    private List<Card> cards;

    public CardListAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_deck, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Card card = cards.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.cardTitle.setText(card.title);
        vh.cardDescription.setText(card.description);
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddCardActivity.class);
                intent.putExtra("card_id", card.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView cardTitle, cardDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            cardTitle = view.findViewById(R.id.tvListItemFlashcardName);
            cardDescription = view.findViewById(R.id.tvListItemFlashcardDescription);
        }
    }
}
