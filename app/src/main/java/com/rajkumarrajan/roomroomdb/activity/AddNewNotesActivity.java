package com.rajkumarrajan.roomroomdb.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rajkumarrajan.roomroomdb.Model.NotesModel;
import com.rajkumarrajan.roomroomdb.R;
import com.rajkumarrajan.roomroomdb.Room.MyRoomDBClient;
import com.rajkumarrajan.roomroomdb.Util.Support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddNewNotesActivity extends AppCompatActivity {


    @BindView(R.id.FloatingActionButtonSave)
    FloatingActionButton FloatingActionButtonSave;

    @BindView(R.id.TextViewTimeAndSize)
    TextView TextViewTimeAndSize;

    @BindView(R.id.EditTextTitle)
    EditText EditTextTitle;

    @BindView(R.id.EditTextDescription)
    EditText EditTextDescription;

    @BindView(R.id.SpinnerOperation)
    Spinner SpinnerOperation;

    private String CurrentTime;
    private Support support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        support = new Support(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        CurrentTime = DateToString(new Date(), "MMMM dd hh:mm a");
        TextViewTimeAndSize.setText(CurrentTime + " | 0 characters");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        EditTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 1){

                }

                TextViewTimeAndSize.setText(CurrentTime + " | " + charSequence.length() + " characters");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SpinnerOperation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (SpinnerOperation.getSelectedItem().toString().equals("Notes")){
                    EditTextTitle.setVisibility(View.GONE);
                    EditTextDescription.setHint("");
                }else {
                    EditTextTitle.setVisibility(View.VISIBLE);
                    EditTextDescription.setHint("Message");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FloatingActionButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() {
                        //adding to database
                        MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                                .notesDAO().InsertAll(new NotesModel(R.drawable.ic_facebook,
                                support.EditTextToString(EditTextTitle),
                                support.EditTextToString(EditTextDescription), "",
                                SpinnerOperation.getSelectedItem().toString()));
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        support.DisplayToast(SpinnerOperation.getSelectedItem().toString() + " Saved");
                        onBackPressed();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        support.DisplayToast("Sorry! Unable to create account");
                    }
                });            }
        });


    }

    String DateToString(Date date, String Format) {
        return new SimpleDateFormat(Format, Locale.US).format(date);
    }

    Date StringToDate(String date, String Format) {
        Date OutPutDate = null;
        try {
            OutPutDate = new SimpleDateFormat(Format, Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return OutPutDate;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
