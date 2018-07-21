package com.msttech.digitalattendence.ui.student_registration;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.helper.IntentKeys;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentRegistrationActivity extends AppCompatActivity {

    @BindView(R.id.editText_name)
    TextView studentName;

    @BindView(R.id.editText_roll)
    TextView studentRoll;

    @BindView(R.id.editText_session)
    TextView studentSession;

    @BindView(R.id.editText_phone_number)
    TextView studentPhoneNumber;

    @BindView(R.id.finger_print)
    ImageView studentFingerPrint;
    private String activity;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        ButterKnife.bind(this);

        //initToolbar
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                parseBundleData();
            }
        }, 100);

    }


    private void initToolbar() {

        getSupportActionBar().setTitle("Student Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {

                activity = bundle.getString(IntentKeys.FROM_ACTIVITY);
                if (activity != null && activity.equals("HOME")) {
                    if (menu != null) {
                        MenuItem item = menu.findItem(R.id.action_delete);
                        item.setVisible(false);

                    }
                }

            } catch (Exception e) {
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                //TODO here we have to save teacher profile into database

                finish();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
