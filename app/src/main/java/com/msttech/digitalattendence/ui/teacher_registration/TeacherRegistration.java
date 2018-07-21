package com.msttech.digitalattendence.ui.teacher_registration;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.helper.IntentKeys;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherRegistration extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText_name)
    EditText teacherName;

    @BindView(R.id.editText_designation)
    EditText teacherDesignation;

    @BindView(R.id.editText_department)
    EditText teacherDepartment;

    private String activity;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        ButterKnife.bind(this);

        //initiate toolbar
        initiateToolbar();

        //get bundle data


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

    private void parseBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {

                activity = bundle.getString(IntentKeys.FROM_ACTIVITY);
                if (activity != null && activity.equals("teacher_home")) {

                    teacherName.setText(bundle.getString(IntentKeys.TEACHER_NAME));
                    teacherDesignation.setText(bundle.getString(IntentKeys.TEACHER_DESIGNATION));
                    teacherDepartment.setText(bundle.getString(IntentKeys.TEACHER_DEPT));
                } else if (activity != null && activity.equals("HOME")) {
                    if (menu != null) {
                        MenuItem item = menu.findItem(R.id.action_delete);
                        item.setVisible(false);

                    }
                }

            } catch (Exception e) {
            }

        }
    }

    private void initiateToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Teacher Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

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
