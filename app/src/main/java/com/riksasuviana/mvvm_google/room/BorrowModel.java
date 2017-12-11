package com.riksasuviana.mvvm_google.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by riksa on 25/11/2017.
 */

@Entity
public class BorrowModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private String pesonName;
    @TypeConverters(DateConverter.class)
    private Date borrowDate;

    public BorrowModel(String itemName, String pesonName, Date borrowDate) {
        this.itemName = itemName;
        this.pesonName = pesonName;
        this.borrowDate = borrowDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPesonName() {
        return pesonName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
