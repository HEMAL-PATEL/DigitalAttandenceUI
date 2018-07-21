package com.msttech.digitalattendence.ui.attendence;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
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


/*
 *  This activity will show overall attendance
 * */
public class AttendanceActivity extends AppCompatActivity {

    @BindView(R.id.table_layout)
    TableLayout tableLayout;

    int column = 10;
    TableRow.LayoutParams textParams = new TableRow.LayoutParams(150, 100);
    TableRow.LayoutParams headerParams = new TableRow.LayoutParams(300, 100);
    TableRow.LayoutParams lineParams = new TableRow.LayoutParams(1, 100);
    TableLayout.LayoutParams borderParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        ButterKnife.bind(this);

        initToolBar();

        showCTMarks();
    }

    private void initToolBar() {

        getSupportActionBar().setTitle("Attendance Management");
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
                for (int j = 1; j <= column + 6; j++) {
                    TextView ctTitle = new TextView(this);


                    if (j < 3) {
                        if (j == 1) {
                            ctTitle.setLayoutParams(headerParams);
                            ctTitle.setText("Name");
                            ctTitle.setGravity(Gravity.CENTER);
                            ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                        } else {
                            ctTitle.setLayoutParams(headerParams);
                            ctTitle.setText("Roll");
                            ctTitle.setGravity(Gravity.CENTER);
                            ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                        }

                    } else if (j == (column + 3)) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Total Day");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == (column + 4)) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Total Present");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == (column + 5)) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Percentage");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else if (j == (column + 6)) {
                        ctTitle.setLayoutParams(headerParams);
                        ctTitle.setText("Attendance Mark");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else {
                        ctTitle.setLayoutParams(textParams);
                        ctTitle.setText("Date # " + (j - 2));
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
                for (int j = 1; j <= column + 6; j++) {

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
                    } else if (j == (column + 5)) {
                        ctMarks.setLayoutParams(headerParams);
                        ctMarks.setEnabled(false);
                        ctMarks.setTextColor(Color.GRAY);
                        ctMarks.setText("80%");
                        ctMarks.setFocusable(false);
                    } else if (j == (column + 6)) {
                        ctMarks.setLayoutParams(headerParams);
                        ctMarks.setEnabled(false);
                        ctMarks.setTextColor(Color.GRAY);
                        ctMarks.setText("7");
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
                            ctMarks.setText("A");
                            ctMarks.setGravity(Gravity.CENTER);
                            ctMarks.setTextColor(Color.GREEN);
                        } else {
                            ctMarks.setText("P");
                            ctMarks.setGravity(Gravity.CENTER);
                            ctMarks.setTextColor(Color.RED);
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
