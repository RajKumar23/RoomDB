package com.rajkumarrajan.roomroomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rajkumarrajan.roomroomdb.Model.UserModel;
import com.rajkumarrajan.roomroomdb.R;
import com.rajkumarrajan.roomroomdb.Room.MyRoomDBClient;
import com.rajkumarrajan.roomroomdb.Util.Support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.MyFloatingActionButton)
    FloatingActionButton MyFloatingActionButton;

    @BindView(R.id.EditTextNumber)
    EditText EditTextNumber;

    @BindView(R.id.ButtonSignIn)
    Button ButtonSignIn;

    @BindView(R.id.ButtonSignUp)
    Button ButtonSignUp;

    private Support support;
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        support = new Support(this);
        disposable = new CompositeDisposable();

        MyFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (support.EditTextToString(EditTextNumber).trim().length() == 10) {

                    disposable.add(MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                            .userDao().CheckNumber(Long.valueOf(support.EditTextToString(EditTextNumber))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(SelectedSize -> {
                                if (SelectedSize == 1){
                                    disposable.add(MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                                            .userDao().GetDetailsByNumber(Long.valueOf(support.EditTextToString(EditTextNumber))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(UserModel -> {
                                                if (UserModel.getMobileNumber() == Long.valueOf(support.EditTextToString(EditTextNumber))){
                                                    support.DisplayToast("Welcome to DigPassword " + UserModel.getUserFirsName() + " " + UserModel.getUserLastName());
                                                }
                                            }));
                                }else {
                                    support.DisplayToast("Sorry! Mobile number doesn't registered with us");
                                }
                            }));
                } else {
                    support.DisplayToast("Mobile number can't be less than 10 digit");
                }
            }
        });

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
