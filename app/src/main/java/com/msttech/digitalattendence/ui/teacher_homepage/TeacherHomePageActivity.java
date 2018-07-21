package com.msttech.digitalattendence.ui.teacher_homepage;

import android.content.Intent;
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
import com.msttech.digitalattendence.ui.class_management.ClassManagementActivity;
import com.msttech.digitalattendence.ui.student_registration.StudentRegistrationActivity;
import com.msttech.digitalattendence.ui.teacher_registration.TeacherRegistration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherHomePageActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.text_view_header)
    TextView headerText;

    @BindView(R.id.button_class_management)
    Button buttonClassManagement;

    @BindView(R.id.button_add_course)
    Button buttonAddCourse;

    @BindView(R.id.button_remove_course)
    Button buttonRemoveCourse;

    @BindView(R.id.button_view_course)
    Button buttonViewCourse;

    @BindView(R.id.button_edit_profile)
    Button buttonEditProfile;

    private String teacherName;
    private String designation;
    private String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        ButterKnife.bind(this);

        //init Toolbar
        initToolbar();

        //click listener

        buttonClassManagement.setOnClickListener(this);
        buttonAddCourse.setOnClickListener(this);
        buttonViewCourse.setOnClickListener(this);
        buttonEditProfile.setOnClickListener(this);
        buttonRemoveCourse.setOnClickListener(this);

        // get intent data
        parseBundleData();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            teacherName = bundle.getString(IntentKeys.TEACHER_NAME);
            headerText.setText("Hi!! " + teacherName);
            designation = bundle.getString(IntentKeys.TEACHER_DESIGNATION);
            department = bundle.getString(IntentKeys.TEACHER_DEPT);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_class_management:
                openSessionDialog();
                break;
            case R.id.button_add_course:
                openAddCourseDialog();
                break;
            case R.id.button_view_course:
                openViewCourseDialog();
                break;
            case R.id.button_remove_course:
                openRemoveCourseDialog();
                break;
            case R.id.button_edit_profile:
                //TODO goto profile activity to show data
                /*
                 *  So temporary just going teacher profile activity
                 * */
                openProfileDialog();
                break;
        }
    }

    private void openSessionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_course_selection, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);
        Button goButton = dialogView.findViewById(R.id.button_go);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherHomePageActivity.this, ClassManagementActivity.class));
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

    private void openRemoveCourseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_remove_course, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);
        Button addCourse = dialogView.findViewById(R.id.button_add_course);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void openAddCourseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_course, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);
        Button addCourse = dialogView.findViewById(R.id.button_add_course);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void openViewCourseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view_course, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void openProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_profile, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);
        Button teacher = dialogView.findViewById(R.id.button_teacher);
        Button student = dialogView.findViewById(R.id.button_student);


        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                opeStudentDialog();
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHomePageActivity.this, TeacherRegistration.class);
                intent.putExtra(IntentKeys.FROM_ACTIVITY, "teacher_home");
                intent.putExtra(IntentKeys.TEACHER_NAME, teacherName);
                intent.putExtra(IntentKeys.TEACHER_DESIGNATION, designation);
                intent.putExtra(IntentKeys.TEACHER_DEPT, department);
                dialog.dismiss();
                startActivity(intent);
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void opeStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHomePageActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_one_student, null, false);
        builder.setView(dialogView);

        ImageView closeButton = dialogView.findViewById(R.id.close_button);
        Button go = dialogView.findViewById(R.id.button_go);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(TeacherHomePageActivity.this, StudentRegistrationActivity.class);
                intent.putExtra(IntentKeys.FROM_ACTIVITY, "abc");
                startActivity(intent);
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
