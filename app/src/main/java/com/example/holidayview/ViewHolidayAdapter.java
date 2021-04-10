package com.example.holidayview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.localstorage.LocalHoliday;

public class ViewHolidayAdapter extends ListAdapter<LocalHoliday, ViewHolidayHolder> {

    public ViewHolidayAdapter(@NonNull DiffUtil.ItemCallback<LocalHoliday> diffCallback) {
        super(diffCallback);
    }


    @Override
    public ViewHolidayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolidayHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolidayHolder holder, int position) {
        LocalHoliday current = getItem(position);
        holder.bind(current.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dsd", v.toString());
                Intent intent = new Intent(MainActivity.context, HolidayItemActivity.class);
                intent.putExtra("identifier", current.getIdentifier());
                MainActivity.context.startActivity(intent);
            }
        });
    }

    static class WordDiff extends DiffUtil.ItemCallback<LocalHoliday> {

        @Override
        public boolean areItemsTheSame(@NonNull LocalHoliday oldItem, @NonNull LocalHoliday newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull LocalHoliday oldItem, @NonNull LocalHoliday newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
