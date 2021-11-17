package com.example.quizler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeckListAdapter extends RecyclerView.Adapter {
    private List<Deck> decks;

    public DeckListAdapter(List<Deck> decks) {
        this.decks = decks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_deck, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Deck deck = decks.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.deckName.setText(deck.name);
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardListActivity.class);
                intent.putExtra("deck_id", deck.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView deckName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            deckName = view.findViewById(R.id.tvListItemDeckName);
        }
    }
}
