package com.msttech.digitalattendence.ui.class_management;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.helper.CommonUtility;
import com.msttech.digitalattendence.data.helper.IntentKeys;
import com.msttech.digitalattendence.ui.attendence.AttendanceActivity;
import com.msttech.digitalattendence.ui.ct_management.CTManagementActivity;
import com.msttech.digitalattendence.ui.overall_view.OverAllViewActivity;
import com.msttech.digitalattendence.ui.take_attendance.TakeAttendanceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassManagementActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button_take_attendance)
    Button buttonTakeAttendance;

    @BindView(R.id.button_ct_marks)
    Button buttonCtMarks;

    @BindView(R.id.button_attendances)
    Button buttonAttendances;

    @BindView(R.id.button_overall_view)
    Button buttonOverallView;

    @BindView(R.id.text_view_session)
    TextView textViewSession;

    @BindView(R.id.text_view_course_code)
    TextView textViewCourseCode;

    @BindView(R.id.text_view_course_name)
    TextView textViewCourseName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);
        ButterKnife.bind(this);

        initToolBar();

        //click listener

        buttonTakeAttendance.setOnClickListener(this);
        buttonCtMarks.setOnClickListener(this);
        buttonAttendances.setOnClickListener(this);
        buttonOverallView.setOnClickListener(this);
    }

    private void initToolBar() {

        getSupportActionBar().setTitle("Class Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_take_attendance:
                startActivity(new Intent(ClassManagementActivity.this, TakeAttendanceActivity.class));
                break;
            case R.id.button_ct_marks:
                openCTDialog();
                break;
            case R.id.button_attendances:
                openAttendanceDialog();
                break;
            case R.id.button_overall_view:
                startActivity(new Intent(ClassManagementActivity.this, OverAllViewActivity.class));
                break;
        }
    }


    private void openCTDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassManagementActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ct_mark, null, false);
        builder.setView(dialogView);

        Button addCT = dialogView.findViewById(R.id.button_add_ct_marks);
        Button viewCT = dialogView.findViewById(R.id.button_view_ct_marks);
        ImageView closeButton = dialogView.findViewById(R.id.close_button);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        addCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassManagementActivity.this, CTManagementActivity.class);
                intent.putExtra(IntentKeys.CT_ACTIVITY, CommonUtility.ADD_CT);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        viewCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassManagementActivity.this, CTManagementActivity.class);
                intent.putExtra(IntentKeys.CT_ACTIVITY, CommonUtility.VIEW_CT);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void openAttendanceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassManagementActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view_attendence, null, false);
        builder.setView(dialogView);

        Button viewOne = dialogView.findViewById(R.id.button_view_one);
        Button viewAll = dialogView.findViewById(R.id.button_view_all);
        ImageView closeButton = dialogView.findViewById(R.id.close_button);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        viewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openRollSelectionDialog();
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassManagementActivity.this, AttendanceActivity.class);
                intent.putExtra(IntentKeys.CT_ACTIVITY, CommonUtility.VIEW_CT);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void openRollSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassManagementActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_one_student, null, false);
        builder.setView(dialogView);

        Button goButton = dialogView.findViewById(R.id.button_go);
        ImageView closeButton = dialogView.findViewById(R.id.close_button);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ClassManagementActivity.this, AttendanceActivity.class);
                intent.putExtra(IntentKeys.CT_ACTIVITY, CommonUtility.VIEW_CT);
                startActivity(intent);
                dialog.dismiss();*/

                dialog.dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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
