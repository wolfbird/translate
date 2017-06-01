package com.example.administer.mamaipi2.menu.database.words;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administer.mamaipi2.menu.database.words.WordDbSchema.WordTable;

import static com.example.administer.mamaipi2.menu.database.words.WordDbSchema.*;

/**
 * Created by administer on 2017/5/16.
 */

public class WordBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "WordBase.db";
    public WordBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//创建数据库
        db.execSQL("create table "+ WordTable.NAME+"("+"_id integer primary key autoincrement,"+
                                                    Cols.UUID+","+Cols.SPELL+","+Cols.US+","+Cols.UK
                                                    +","+Cols.EXPLAIN+","+Cols.WEBEXPLAIN+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
