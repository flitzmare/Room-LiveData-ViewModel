package com.riksasuviana.mvvm_google.addItem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.riksasuviana.mvvm_google.room.AppDatabase;
import com.riksasuviana.mvvm_google.room.BorrowModel;

/**
 * Created by riksa on 10/12/2017.
 */

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(getApplication());
    }

    public void addBorrow(BorrowModel borrowModel){
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<BorrowModel, Void, Void>{

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase){
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            db.itemAndPersonModel().addBorrow(borrowModels[0]);
            return null;
        }
    }
}
