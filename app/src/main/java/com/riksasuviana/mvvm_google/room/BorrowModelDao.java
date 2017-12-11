package com.riksasuviana.mvvm_google.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.icu.text.Replaceable;

import java.util.List;

/**
 * Created by riksa on 25/11/2017.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {
    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    @Query("select * from BorrowModel where id = :id")
    BorrowModel getItemById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);
}
