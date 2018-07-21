package com.msttech.digitalattendence.ui.overall_view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.msttech.digitalattendence.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OverAllViewActivity extends AppCompatActivity {

    @BindView(R.id.table_layout)
    TableLayout tableLayout;

    int column = 7;
    TableRow.LayoutParams textParams = new TableRow.LayoutParams(150, 100);
    TableRow.LayoutParams headerParams = new TableRow.LayoutParams(300, 100);
    TableRow.LayoutParams lineParams = new TableRow.LayoutParams(1, 100);
    TableLayout.LayoutParams borderParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_all_view);

        ButterKnife.bind(this);

        initToolBar();

        showCTMarks();
    }

    private void initToolBar() {

        getSupportActionBar().setTitle("Overall View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void showCTMarks() {
        for (int i = 0; i < 15; i++) {
            final TableRow tableRow = new TableRow(this);
            tableLayout.addView(tableRow);
            View horizontalLine = new View(this);
            horizontalLine.setBackgroundColor(Color.BLACK);
            horizontalLine.setLayoutParams(borderParams);


            tableLayout.addView(horizontalLine);

            if (i == 0) {
                for (int j = 1; j <= column; j++) {
                    TextView ctTitle = new TextView(this);

                    if (j == 1) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Name");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 2) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Roll");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 3) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Total-day");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 4) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Present");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 5) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Attendance Mark");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 6) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("CT Mark");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == 7) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Total Mark");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    }

                    ctTitle.setBackgroundColor(Color.GRAY);
                    ctTitle.setTextColor(Color.WHITE);
                    tableRow.addView(ctTitle);

                    final View verticalLine = new View(this);

                    verticalLine.setBackgroundColor(Color.RED);
                    verticalLine.setLayoutParams(lineParams);
                    tableRow.addView(verticalLine);
                }
            } else {
                for (int j = 1; j <= column; j++) {

                    final EditText ctMarks = new EditText(this);
                    final View verticalLine = new View(this);

                    verticalLine.setBackgroundColor(Color.RED);

                    if (j < 3) {
                        if (j == 1) {
                            ctMarks.setLayoutParams(headerParams);
                            ctMarks.setText("Name " + i + 1);
                            ctMarks.setEnabled(false);
                            ctMarks.setTextColor(Color.GRAY);
                            ctMarks.setFocusable(false);
                        } else {
                            ctMarks.setLayoutParams(headerParams);
                            ctMarks.setText("Roll " + i + 1);
                            ctMarks.setEnabled(false);
                            ctMarks.setTextColor(Color.GRAY);
                            ctMarks.setFocusable(false);

                        }

                    } else if (j == (column + 3)) {
                        ctMarks.setLayoutParams(headerParams);
                        ctMarks.setEnabled(false);
                        ctMarks.setTextColor(Color.GRAY);
                        ctMarks.setText("10");
                        ctMarks.setFocusable(false);
                    } else if (j == (column + 4)) {
                        ctMarks.setLayoutParams(headerParams);
                        ctMarks.setEnabled(false);
                        ctMarks.setTextColor(Color.GRAY);
                        ctMarks.setText("6");
                        ctMarks.setFocusable(false);
                    } else {
                        ctMarks.setLayoutParams(textParams);
                        ctMarks.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        ctMarks.setMaxLines(1);
                        ctMarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                        ctMarks.setBackgroundColor(Color.TRANSPARENT);
                        ctMarks.setEnabled(false);
                        ctMarks.setFocusable(false);

                        if (i % 2 == 0) {
                            ctMarks.setText("6");
                            ctMarks.setGravity(Gravity.CENTER);

                        } else {
                            ctMarks.setText("10");
                            ctMarks.setGravity(Gravity.CENTER);

                        }


                    }

                    tableRow.addView(ctMarks);

                    verticalLine.setLayoutParams(lineParams);
                    tableRow.addView(verticalLine);

                }
            }


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
