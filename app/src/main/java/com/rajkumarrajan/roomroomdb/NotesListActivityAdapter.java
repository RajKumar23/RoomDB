package com.rajkumarrajan.roomroomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rajkumarrajan.roomroomdb.Model.NotesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesListActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NotesModel> myPojos;

    public NotesListActivityAdapter(Context context, List<NotesModel> body) {
        this.context = context;
        this.myPojos = body;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.TextViewDate)
        TextView TextViewDate;

        @BindView(R.id.TextViewTitle)
        TextView TextViewTitle;

        @BindView(R.id.TextViewDescription)
        TextView TextViewDescription;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View menuItemLayoutView = LayoutInflater.from(context).inflate(
                R.layout.recycler_notes_view, viewGroup, false);
        viewHolder = new ViewHolder(menuItemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (myPojos.get(holder.getAdapterPosition()).getTitle().length() == 0){
            viewHolder.TextViewTitle.setVisibility(View.GONE);
        }else {
            viewHolder.TextViewTitle.setText(myPojos.get(holder.getAdapterPosition()).getTitle());
        }
        viewHolder.TextViewDate.setText(myPojos.get(holder.getAdapterPosition()).getSaveDate().toString());
        viewHolder.TextViewDescription.setText(myPojos.get(holder.getAdapterPosition()).getDescription());
    }

    @Override
    public int getItemCount() {
        return myPojos.size();
    }
}