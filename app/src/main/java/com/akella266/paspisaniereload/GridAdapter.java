package com.akella266.paspisaniereload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Akella266 on 05.01.2017.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] week;

    public GridAdapter(Context c, String[] wk){
        mContext = c;
        week = wk;
    }

    @Override
    public int getCount() {
        return week.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.button_week, null,true);
            TextView tv = (TextView) grid.findViewById(R.id.tvText);
            tv.setText(week[i]);
        }
        else{
            grid = (View) view;
        }
        return grid;
    }
}
