package com.rajkumarrajan.roomroomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajkumarrajan.roomroomdb.NotesListActivityAdapter;
import com.rajkumarrajan.roomroomdb.R;
import com.rajkumarrajan.roomroomdb.Room.MyRoomDBClient;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NotesListActivity extends AppCompatActivity {

    @BindView(R.id.FloatingActionButtonAddNotes)
    FloatingActionButton FloatingActionButtonAddNotes;

    @BindView(R.id.RecyclerViewNotes)
    RecyclerView RecyclerViewNotes;

    LinearLayoutManager llm1;
    private CompositeDisposable disposable;
    private NotesListActivityAdapter notesListActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        disposable = new CompositeDisposable();

        llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewNotes.setLayoutManager(llm1);
        RecyclerViewNotes.setHasFixedSize(true);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        FloatingActionButtonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotesListActivity.this, AddNewNotesActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable.add(MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                .notesDAO().GetAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(notesModelList -> {

                    if (notesModelList.size() > 0) {
                        RecyclerViewNotes.setAdapter(new NotesListActivityAdapter(NotesListActivity.this, notesModelList));
                    }


                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
