package com.rajkumarrajan.roomroomdb.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rajkumarrajan.roomroomdb.R;

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

    @BindView(R.id.EditTextDescription)
    EditText EditTextDescription;

    private String CurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
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
