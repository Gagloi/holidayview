package com.example.holidayview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.localstorage.LocalHoliday;

public class HolidayItemActivity extends AppCompatActivity {


    private TextView nameTextView;
    private TextView typeTextView;
    private TextView countryCodeTextView;
    private TextView dateTextView;
    private TextView localNameTextView;
    private TextView fixedTextView;
    private TextView globalTextView;
    private TextView launchYearTextView;
    private ProgressBar progressBar;

    private ViewHolidayItem viewModel;

    private String identifier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_item);
        nameTextView = findViewById(R.id.nameTextView);
        dateTextView = findViewById(R.id.date);
        localNameTextView = findViewById(R.id.localName);
        fixedTextView = findViewById(R.id.fixed);
        globalTextView = findViewById(R.id.global);
        launchYearTextView = findViewById(R.id.launchYear);
        progressBar = findViewById(R.id.progress);
        countryCodeTextView = findViewById(R.id.countryCode);
        typeTextView = findViewById(R.id.typeAccess);

        Bundle args = getIntent().getExtras();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ViewHolidayItem.class);

        viewModel.getData(args.getString("identifier")).observe(this, res -> {
            progressBar.setVisibility(View.GONE);
            LocalHoliday holiday = LocalHoliday.builder()
                    .countryCode(res.getCountryCode())
                    .date(res.getDate())
                    .fixed(res.getFixed())
                    .global(res.getGlobal())
                    .identifier(res.getIdentifier())
                    .launchYear(res.getLaunchYear())
                    .localName(res.getLocalName())
                    .name(res.getName())
                    .type(res.getType())
                    .build();
                    nameTextView.setText(holiday.getName());
                    dateTextView.setText(holiday.getDate());
                    localNameTextView.setText(holiday.getLocalName());
                    fixedTextView.setText(String.valueOf(holiday.getFixed()));
                    globalTextView.setText(String.valueOf(holiday.getGlobal()));
                    launchYearTextView.setText(String.valueOf(holiday.getLaunchYear()));
                    countryCodeTextView.setText(String.valueOf(res.getCountryCode()));
                    typeTextView.setText(String.valueOf(res.getType()));
                    progressBar.setVisibility(View.GONE);
        });
//        viewModel.getResults().observe(this, result -> {
//            switch (result.getStatus()) {
//                case SUCCESS:
//                    Holiday holiday = result.getData();
//                    nameTextView.setText(holiday.getName());
//                    dateTextView.setText(holiday.getDate());
//                    localNameTextView.setText(holiday.getLocalName());
//                    fixedTextView.setText(String.valueOf(holiday.isFixed()));
//                    globalTextView.setText(String.valueOf(holiday.isGlobal()));
//                    launchYearTextView.setText(String.valueOf(holiday.getLaunchYear()));
//                    progressBar.setVisibility(View.GONE);
//                    break;
//                case EMPTY:
//                case LOADING:
//                    nameTextView.setText("");
//                    dateTextView.setText("");
//                    localNameTextView.setText("");
//                    fixedTextView.setText("");
//                    globalTextView.setText("");
//                    launchYearTextView.setText("");
//                    progressBar.setVisibility(View.GONE);
//                    break;
//            }
//        });
    }

}
