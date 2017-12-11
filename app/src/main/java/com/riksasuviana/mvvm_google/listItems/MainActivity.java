package com.riksasuviana.mvvm_google.listItems;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.riksasuviana.mvvm_google.R;
import com.riksasuviana.mvvm_google.addItem.AddActivity;
import com.riksasuviana.mvvm_google.room.BorrowModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riksa on 25/11/2017.
 */

public class MainActivity extends LifecycleActivity implements View.OnLongClickListener{

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.tb);

        setActionBar(tb);
        setTitle(getString(R.string.app_name));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> borrowModelList) {
                recyclerViewAdapter.addItems(borrowModelList);
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onLongClick(View v) {
        BorrowModel borrowModel = (BorrowModel)v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }
}
