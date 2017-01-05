package com.akella266.paspisaniereload;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    Toolbar toolbar;
    GridView gvMain;
    //GridAdapter adapter;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initGridWithButtons();
    }

    private void initGridWithButtons() {
        //adapter = new GridAdapter(MainActivity.this, week);
        adapter = new ArrayAdapter<String>(this, R.layout.button_week,R.id.tvText, week);
        gvMain = (GridView)findViewById(R.id.gridView);
        gvMain.setAdapter(adapter);
        gvMain.setColumnWidth(400);
        gvMain.setVerticalSpacing(32);
        gvMain.setHorizontalSpacing(32);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view, "Was Clicked " + week[+i], Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initToolBar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolBar_titleColor));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }
}
