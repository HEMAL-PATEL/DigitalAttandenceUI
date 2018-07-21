package com.msttech.digitalattendence.ui.home;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dekoservidoni.omfm.OneMoreFabMenu;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.msttech.digitalattendence.R;
import com.msttech.digitalattendence.data.helper.IntentKeys;
import com.msttech.digitalattendence.data.model.TeacherModel;
import com.msttech.digitalattendence.ui.student_registration.StudentRegistrationActivity;
import com.msttech.digitalattendence.ui.teacher_homepage.TeacherHomePageActivity;
import com.msttech.digitalattendence.ui.teacher_registration.TeacherRegistration;
import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnItemClick, View.OnClickListener {

    @BindView(R.id.recycler_view_list)
    RecyclerView recyclerViewTeacherList;

    @BindView(R.id.floating_button)
    FloatingActionMenu floatingActionButton;

    @BindView(R.id.menu_item_teacher)
    FloatingActionButton floatingActionButtonTeacher;

    @BindView(R.id.menu_item_student)
    FloatingActionButton floatingActionButtonStudent;

    @BindView(R.id.welcome_text)
    TextView welcomeText;


    private HomeAdapter mAdapter;
    private List<TeacherModel> mTeacherModels;

    // scanner element
    //the width of fingerprint image
    int fpWidth = 0;
    //the height of fingerprint image
    int fpHeight = 0;
    //for verify test
    private byte[] lastRegTemp = new byte[2048];
    //the length of lastRegTemp
    private int cbRegTemp = 0;
    //pre-register template
    private byte[][] regtemparray = new byte[3][2048];
    //Register
    private boolean bRegister = false;
    //Identify
    private boolean bIdentify = true;
    //finger id
    private int iFid = 1;

    private int nFakeFunOn = 1;
    //must be 3
    static final int enroll_cnt = 3;
    //the index of pre-register function
    private int enroll_idx = 0;

    private byte[] imgbuf = null;
    private byte[] template = new byte[2048];
    private int[] templateLen = new int[1];


    private boolean mbStop = true;
    private long mhDevice = 0;
    private long mhDB = 0;
    private WorkThread workThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //initiateToolbar
        initiateToolBar();

        //initiate adapter
        initiateAdapter();

        //add dummy list
        addDummyList();


        // click listener
        floatingActionButtonTeacher.setOnClickListener(this);
        floatingActionButtonStudent.setOnClickListener(this);


    }

    private void addDummyList() {
        //TODO we will change it later. it will get data from internet

        String imageUri = "android.resource://com.msttech.digitalattendence/drawable/";

        TeacherModel model = new TeacherModel(imageUri + "nowshin", "Nowsin Amin Sheikh",
                "Assistant Professor", "CSE");

        TeacherModel model1 = new TeacherModel(imageUri + "nazmu", "Nazmul Hossain",
                "Assistant Professor", "CSE");

        mTeacherModels.add(model);
        mTeacherModels.add(model1);
        mAdapter.notifyDataSetChanged();
    }

    private void initiateAdapter() {
        mTeacherModels = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewTeacherList.setLayoutManager(layoutManager);
        recyclerViewTeacherList.setHasFixedSize(true);
        mAdapter = new HomeAdapter(this, mTeacherModels, this);
        recyclerViewTeacherList.setAdapter(mAdapter);
    }

    private void initiateToolBar() {
        //TODO initialize and setup tool bar here
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void openScannerDialog(final int position, final String id, final String name) {
        //TODO open a dialog after item click for finger

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_fingerprint, null, false);
        builder.setView(dialogView);

        ImageView fingerPrintImage = dialogView.findViewById(R.id.finger_print);
        Button okButton = dialogView.findViewById(R.id.ok_button);

        final AlertDialog dialog = builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TeacherHomePageActivity.class);
                TeacherModel model = mTeacherModels.get(position);
                intent.putExtra(IntentKeys.TEACHER_ID, id);
                intent.putExtra(IntentKeys.TEACHER_NAME, name);
                intent.putExtra(IntentKeys.TEACHER_DESIGNATION, model.getTeacherDesignation());
                intent.putExtra(IntentKeys.TEACHER_DEPT, model.getDepartment());

                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });

    }

    @Override
    public void onGetItemClick(int position, String id, String name) {

        openScannerDialog(position, id, name);
       //scannerOpeen();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_teacher:
                floatingActionButton.close(true);
                Intent intent = new Intent(HomeActivity.this, TeacherRegistration.class);
                intent.putExtra(IntentKeys.FROM_ACTIVITY, "HOME");
                startActivity(intent);
                break;
            case R.id.menu_item_student:
                floatingActionButton.close(true);
                Intent intent1 = new Intent(HomeActivity.this, StudentRegistrationActivity.class);
                intent1.putExtra(IntentKeys.FROM_ACTIVITY, "HOME");
                startActivity(intent1);
                break;
        }
    }


    private void scannerOpeen() {
        if (0 != mhDevice) {
            //already inited
            welcomeText.setText("Please close device first!");
            return;
        }
        int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
        //Initialize
        cbRegTemp = 0;
        //bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init()) {
            welcomeText.setText( "Init failed!");
            return;
        }
        ret = FingerprintSensorEx.GetDeviceCount();
        if (ret < 0) {
            welcomeText.setText( "No devices connected!");
            FreeSensor();
            return;
        }
        if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0))) {
            welcomeText.setText( "Open device fail, ret = " + ret + "!");
            FreeSensor();
            return;
        }
        if (0 == (mhDB = FingerprintSensorEx.DBInit())) {
            welcomeText.setText( "Init DB fail, ret = " + ret + "!");
            FreeSensor();
            return;
        }

        //For ISO/Ansi
        int nFmt = 0;    //Ansi
       /* if (radioISO.isSelected())
        {
            nFmt = 1;	//ISO
        }*/
        FingerprintSensorEx.DBSetParameter(mhDB, 5010, nFmt);
        //For ISO/Ansi End

        //set fakefun off
        //FingerprintSensorEx.SetParameter(mhDevice, 2002, changeByte(nFakeFunOn), 4);

        byte[] paramValue = new byte[4];
        int[] size = new int[1];
        //GetFakeOn
        //size[0] = 4;
        //FingerprintSensorEx.GetParameters(mhDevice, 2002, paramValue, size);
        //nFakeFunOn = byteArrayToInt(paramValue);

        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
        //fpWidth = byteArrayToInt(paramValue);
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
        //fpHeight = byteArrayToInt(paramValue);
        //always commented below two line
        //width = fingerprintSensor.getImageWidth();
        //height = fingerprintSensor.getImageHeight();

        imgbuf = new byte[fpWidth*fpHeight];
       // btnImg.resize(fpWidth, fpHeight);
        mbStop = false;
        workThread = new WorkThread();
        workThread.start();// çº¿ç¨‹å�¯åŠ¨
        welcomeText.setText( "Open succ!");
    }

    private void FreeSensor() {
        mbStop = true;
        try {        //wait for thread stopping
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (0 != mhDB) {
            FingerprintSensorEx.DBFree(mhDB);
            mhDB = 0;
        }
        if (0 != mhDevice) {
            FingerprintSensorEx.CloseDevice(mhDevice);
            mhDevice = 0;
        }
        FingerprintSensorEx.Terminate();
    }


    private class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();
            int ret = 0;
            while (!mbStop) {
                templateLen[0] = 2048;
                if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))) {
                    if (nFakeFunOn == 1) {
                        byte[] paramValue = new byte[4];
                        int[] size = new int[1];
                        size[0] = 4;
                        int nFakeStatus = 0;
                        //GetFakeStatus
                        ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
                        nFakeStatus = byteArrayToInt(paramValue);
                        System.out.println("ret = " + ret + ",nFakeStatus=" + nFakeStatus);
                        if (0 == ret && (byte) (nFakeStatus & 31) != 31) {
                            welcomeText.setText("Is a fake-finer?");
                            return;
                        }
                    }
                    OnCatpureOK(imgbuf);
                    OnExtractOK(template, templateLen[0]);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private void runOnUiThread(Runnable runnable) {
            // TODO Auto-generated method stub

        }
    }

    public static int byteArrayToInt(byte[] bytes) {
        int number = bytes[0] & 0xFF;
        // "|="æŒ‰ä½�æˆ–èµ‹å€¼ã€‚
        number |= ((bytes[1] << 8) & 0xFF00);
        number |= ((bytes[2] << 16) & 0xFF0000);
        number |= ((bytes[3] << 24) & 0xFF000000);
        return number;
    }

    private void OnCatpureOK(byte[] imgBuf) {
        welcomeText.setText("image come");
       /* try {
            //writeBitmap(imgBuf, fpWidth, fpHeight, "fingerprint.bmp");
            //btnImg.setIcon(new ImageIcon(ImageIO.read(new File("fingerprint.bmp"))));
        } catch (IOException e) {

            e.printStackTrace();
        }*/
    }

    private void OnExtractOK(byte[] template, int len) {
        if (bRegister) {
            int[] fid = new int[1];
            int[] score = new int[1];
            int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
            if (ret == 0) {
                Log.d("scanner", "the finger already enroll by " + fid[0] + ",cancel enroll");
                bRegister = false;
                enroll_idx = 0;
                return;
            }
            if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx - 1], template) <= 0) {
                welcomeText.setText( "please press the same finger 3 times for the enrollment");
                return;
            }
            System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
            enroll_idx++;
            if (enroll_idx == 3) {
                int[] _retLen = new int[1];
                _retLen[0] = 2048;
                byte[] regTemp = new byte[_retLen[0]];

                if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2], regTemp, _retLen)) &&
                        0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
                    iFid++;
                    cbRegTemp = _retLen[0];
                    System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
                    //Base64 Template
                    welcomeText.setText( "enroll succ");
                } else {
                    welcomeText.setText( "enroll fail, error code=" + ret);
                }
                bRegister = false;
            } else {
                welcomeText.setText( "You need to press the " + (3 - enroll_idx) + " times fingerprint");
            }
        } else {
            if (bIdentify) {
                int[] fid = new int[1];
                int[] score = new int[1];
                int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
                if (ret == 0) {
                    welcomeText.setText( "Identify succ, fid=" + fid[0] + ",score=" + score[0]);
                } else {
                    welcomeText.setText( "Identify fail, errcode=" + ret);
                }

            } else {
                if (cbRegTemp <= 0) {
                    welcomeText.setText( "Please register first!");
                } else {
                    int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
                    if (ret > 0) {
                        welcomeText.setText( "Verify succ, score=" + ret);
                    } else {
                        welcomeText.setText( "Verify fail, ret=" + ret);
                    }
                }
            }
        }
    }
}
