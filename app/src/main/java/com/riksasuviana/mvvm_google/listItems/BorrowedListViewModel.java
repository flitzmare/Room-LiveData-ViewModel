package com.riksasuviana.mvvm_google.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.riksasuviana.mvvm_google.room.AppDatabase;
import com.riksasuviana.mvvm_google.room.BorrowModel;

import java.util.List;

/**
 * Created by riksa on 26/11/2017.
 */

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList(){
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel borrowModel){
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void>{

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase){
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            db.itemAndPersonModel().deleteBorrow(borrowModels[0]);
            return null;
        }
    }
}
