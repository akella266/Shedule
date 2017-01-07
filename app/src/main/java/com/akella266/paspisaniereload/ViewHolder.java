package com.akella266.paspisaniereload;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Akella266 on 06.01.2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView tvLesson;
    private TextView tvProf;
    private TextView tvRoom;
    private TextView tvTime;

    public TextView getTvLesson() {
        return tvLesson;
    }

    public TextView getTvProf() {
        return tvProf;
    }

    public TextView getTvRoom() {
        return tvRoom;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public ViewHolder(View itemView) {
        super(itemView);

        tvLesson = (TextView)itemView.findViewById(R.id.tvNameLesson);
        tvProf = (TextView)itemView.findViewById(R.id.tvProf);
        tvRoom = (TextView)itemView.findViewById(R.id.tvRoom);
        tvTime = (TextView)itemView.findViewById(R.id.tvTime);
    }
}
