package com.rajkumarrajan.roomroomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rajkumarrajan.roomroomdb.Model.UserModel;
import com.rajkumarrajan.roomroomdb.R;
import com.rajkumarrajan.roomroomdb.Room.MyRoomDBClient;
import com.rajkumarrajan.roomroomdb.Util.Support;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.EditTextFirstName)
    EditText EditTextFirstName;

    @BindView(R.id.EditTextLastName)
    EditText EditTextLastName;

    @BindView(R.id.EditTextNumber)
    EditText EditTextNumber;

    @BindView(R.id.ButtonSignUp)
    Button ButtonSignUp;

    @BindView(R.id.ButtonPrint)
    Button ButtonPrint;

    @BindView(R.id.ButtonSignIn)
    Button ButtonSignIn;

    private CompositeDisposable disposable;
    private Support support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        ButterKnife.bind(this);
        support = new Support(this);
        disposable = new CompositeDisposable();


        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!support.EditTextToString(EditTextFirstName).trim().isEmpty() && !support.EditTextToString(EditTextLastName).trim().isEmpty()) {
                    if (support.EditTextToString(EditTextFirstName).trim().length() >= 4 && support.EditTextToString(EditTextLastName).trim().length() >= 4) {
                        if (support.EditTextToString(EditTextNumber).trim().length() == 10) {

                            Completable.fromAction(new Action() {
                                @Override
                                public void run() {
                                    //adding to database
                                    MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                                            .userDao().InsertAll(new UserModel(support.EditTextToString(EditTextFirstName),
                                            support.EditTextToString(EditTextLastName),
                                            Long.valueOf(support.EditTextToString(EditTextNumber))));
                                }
                            }).observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onComplete() {
                                    EditTextFirstName.setText("");
                                    EditTextLastName.setText("");
                                    EditTextNumber.setText("");
                                    Log.e("Test", support.EditTextToString(EditTextFirstName));
                                    support.DisplayToast("Welcome " + support.EditTextToString(EditTextFirstName));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    support.DisplayToast("Sorry! Unable to create account");
                                }
                            });


                        } else {
                            support.DisplayToast("Mobile number can't be less than 10 digit");
                        }
                    } else {
                        support.DisplayToast("First Name or Lst Name must be minim 4 letters");
                    }
                } else {
                    support.DisplayToast("First Name or Lst Name can't be empty");
                }

            }
        });

        ButtonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                disposable.add(MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao().GetAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userModelList -> {

                            for (UserModel userModel : userModelList) {
                                Log.e("Test", userModel.getUserFirsName());
                                support.DisplayToast("Welcome " + userModel.getUserFirsName());
                            }

                        }));
            }
        });


        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
