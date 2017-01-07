package com.akella266.paspisaniereload;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<LessonInfo> lessons;

    public RecyclerAdapter(ArrayList<LessonInfo> lessons) {
        this.lessons = lessons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTvLesson().setText(lessons.get(position).getLesson());
        holder.getTvProf().setText(lessons.get(position).getProf());
        holder.getTvRoom().setText(lessons.get(position).getRoom());
        holder.getTvTime().setText(lessons.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }
}
