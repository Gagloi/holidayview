package com.example.holidayview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolidayHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public ViewHolidayHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        textView.setText(text);
    }

    static ViewHolidayHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolidayHolder(view);
    }
}
