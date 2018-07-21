package com.msttech.digitalattendence.ui.ct_management;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.helper.CommonUtility;
import com.msttech.digitalattendence.data.helper.IntentKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CTManagementActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.table_layout)
    TableLayout tableLayout;

    @BindView(R.id.text_view_date)
    TextView textViewCalendar;

    @BindView(R.id.calendar_button)
    ImageView calendarButton;

    @BindView(R.id.add_button)
    ImageView addButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    int column = 3;
    TableRow.LayoutParams textParams = new TableRow.LayoutParams(150, 100);
    TableRow.LayoutParams headerParams = new TableRow.LayoutParams(300, 100);
    TableRow.LayoutParams lineParams = new TableRow.LayoutParams(1, 100);
    TableLayout.LayoutParams borderParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1);

    private boolean isAddCT = false;

    private HashMap<String, View> rowList = new HashMap<>();
    private HashMap<String, View> titleList = new HashMap<>();
    private HashMap<String, View> markList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctmanagement);
        ButterKnife.bind(this);

        //initToolbar
        initToolBar();

        // parse bundle data
        parseBundleData();

        //show exist column

        showCTMarks();

        //click listener
        calendarButton.setOnClickListener(this);
        addButton.setOnClickListener(this);

    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CT Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private void parseBundleData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String bundleString = bundle.getString(IntentKeys.CT_ACTIVITY);

            assert bundleString != null;
            isAddCT = bundleString.equals(CommonUtility.ADD_CT);

            if (isAddCT) {
                addButton.setVisibility(View.VISIBLE);
            } else {
                addButton.setVisibility(View.GONE);
            }
        }
    }


    private void showCTMarks() {
        for (int i = 0; i < 15; i++) {
            final TableRow tableRow = new TableRow(this);
            tableLayout.addView(tableRow);
            View horizontalLine = new View(this);
            horizontalLine.setBackgroundColor(Color.BLACK);
            horizontalLine.setLayoutParams(borderParams);

            rowList.put(String.valueOf(i), tableRow);

            tableLayout.addView(horizontalLine);

            if (i == 0) {
                for (int j = 1; j <= column + 3; j++) {
                    final TextView ctTitle = new TextView(this);

                    titleList.put(String.valueOf(j), ctTitle);


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
                        ctTitle.setText("Total");
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);
                    } else {
                        ctTitle.setLayoutParams(textParams);
                        ctTitle.setText("CT # " + (j - 2));
                        ctTitle.setGravity(Gravity.CENTER);
                        ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);


                        final int finalJ = i;
                        ctTitle.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                removeCTMark(finalJ);
                                return false;
                            }
                        });
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
                for (int j = 1; j <= column + 3; j++) {

                    final EditText ctMarks = new EditText(this);
                    final View verticalLine = new View(this);

                    markList.put(String.valueOf(j), ctMarks);

                    verticalLine.setBackgroundColor(Color.RED);

                    ctMarks.setId(i);

                    if (j < 3) {
                        if (j == 1) {
                            ctMarks.setLayoutParams(headerParams);
                            ctMarks.setText("Name " + i + 1);
                            ctMarks.setEnabled(false);
                            ctMarks.setTextColor(Color.GRAY);
                        } else {
                            ctMarks.setLayoutParams(headerParams);
                            ctMarks.setText("Roll " + i + 1);
                            ctMarks.setEnabled(false);
                            ctMarks.setTextColor(Color.GRAY);

                        }

                    } else if (j == (column + 3)) {
                        ctMarks.setLayoutParams(headerParams);
                        ctMarks.setEnabled(false);
                        ctMarks.setTextColor(Color.GRAY);
                    } else {
                        ctMarks.setLayoutParams(textParams);
                        ctMarks.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        ctMarks.setMaxLines(1);
                        ctMarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                        ctMarks.setBackgroundColor(Color.TRANSPARENT);
                        ctMarks.setEnabled(true);

                        ctMarks.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                if (editable.toString().length() > 0) {
                                    if (Float.valueOf(editable.toString()) < 10.00) {
                                        ctMarks.setTextColor(Color.RED);
                                    } else {
                                        ctMarks.setTextColor(Color.GREEN);
                                    }
                                }
                                countResult(tableRow);

                            }
                        });

                    }

                    tableRow.addView(ctMarks);

                    verticalLine.setLayoutParams(lineParams);
                    tableRow.addView(verticalLine);

                }
            }


        }
    }

    private void countResult(TableRow tableRow) {

        int total = tableRow.getChildCount();

        List<Float> values = new ArrayList<>();
        for (int j = 4; j < total - 2; j += 2) {
            EditText mark = (EditText) tableRow.getChildAt(j);

            if (TextUtils.isEmpty(mark.getText())) {
                values.add(0.0f);
                Log.d("column", "" + 0);
            } else {
                Log.d("column", "" + mark.getText().toString());
                values.add(Float.parseFloat(mark.getText().toString()));
            }
        }

        EditText result = (EditText) tableRow.getChildAt(total - 2);
        result.setText(String.valueOf((twoLargest(values).get(0) + twoLargest(values).get(1)) / 2));
    }

    private void addOneColumn(TableLayout tableLayout, int number) {

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            if (i % 2 == 0) {
                final TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

                int total = tableRow.getChildCount();
                if (i == 0) {

                    TextView ctTitle = new TextView(this);

                    ctTitle.setLayoutParams(textParams);

                    ctTitle.setText("CT # " + number);
                    ctTitle.setBackgroundColor(Color.GRAY);
                    ctTitle.setTextColor(Color.WHITE);
                    ctTitle.setGravity(Gravity.CENTER);
                    ctTitle.setTypeface(ctTitle.getTypeface(), Typeface.BOLD);

                    tableRow.addView(ctTitle, total - 2);

                    final View verticalLine = new View(this);

                    verticalLine.setBackgroundColor(Color.RED);
                    verticalLine.setLayoutParams(lineParams);
                    tableRow.addView(verticalLine, total - 1);
                } else {

                    final EditText ctMarks = new EditText(this);
                    final View verticalLine = new View(this);

                    verticalLine.setBackgroundColor(Color.RED);


                    ctMarks.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    ctMarks.setMaxLines(1);
                    ctMarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                    ctMarks.setBackgroundColor(Color.TRANSPARENT);

                    ctMarks.setLayoutParams(textParams);

                    ctMarks.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (editable.toString().length() > 0) {
                                if (Float.valueOf(editable.toString()) < 10.00) {
                                    ctMarks.setTextColor(Color.RED);
                                } else {
                                    ctMarks.setTextColor(Color.GREEN);
                                }
                            }
                            countResult(tableRow);

                        }
                    });


                    tableRow.addView(ctMarks, total - 2);

                    verticalLine.setLayoutParams(lineParams);
                    tableRow.addView(verticalLine, total - 1);

                }
            }
        }
    }

    public static List<Float> twoLargest(List<Float> values) {
        float largestA = Integer.MIN_VALUE, largestB = Integer.MIN_VALUE;

        List<Float> result = new ArrayList<>();
        for (float value : values) {
            if (value > largestA) {
                largestB = largestA;
                largestA = value;
            } else if (value > largestB) {
                largestB = value;
            }
        }
        result.add(largestA);
        result.add(largestB);
        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calendar_button:
                break;
            case R.id.add_button:
                column++;
                addOneColumn(tableLayout, column);
                break;
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

    private void removeCTMark(int position) {
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            if (i % 2 == 0) {
                final TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                int child = tableRow.getChildCount();


            }

        }
    }
}
