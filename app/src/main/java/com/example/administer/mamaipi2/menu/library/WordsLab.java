package com.example.administer.mamaipi2.menu.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.administer.mamaipi2.menu.database.words.WordBaseHelper;
import com.example.administer.mamaipi2.menu.database.words.WordCursorWrapper;
import com.example.administer.mamaipi2.menu.database.words.WordDbSchema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by administer on 2017/5/9.
 */

public class WordsLab {
    private static WordsLab sWordsLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static WordsLab get(Context context){

        if (sWordsLab==null){
            sWordsLab = new WordsLab(context);
        }
        return sWordsLab;
    }
    private WordsLab(Context context){
        mContext = context;
        mDatabase = new WordBaseHelper(mContext).getWritableDatabase();


    }
    public void addWord(WordsSave word){
        ContentValues contentValues = getContentValues(word);
        mDatabase.insert(WordDbSchema.WordTable.NAME,null,contentValues);

    }

    public List<WordsSave> getWords() {
        List<WordsSave> words =  new ArrayList<>();
        WordCursorWrapper cursorWrapper = queryWords(null,null);//根据cursorwrapper获得了spell不重复的数据
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                words.add(cursorWrapper.getWord());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return words;
    }

    public String getPointWord(String word){//通过拼写来获得数据
        String words="";
        WordCursorWrapper cursorWrapper = GetWord(word);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                words = cursorWrapper.getWordProperty();
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return words;
    }

    public WordsSave getWordsSave(UUID uuid){

        return null;
    }
    private static ContentValues getContentValues(WordsSave wordsSave){//向数据库插入数据
        ContentValues values = new ContentValues();
        values.put(WordDbSchema.Cols.UUID,wordsSave.getUUID().toString());
        values.put(WordDbSchema.Cols.SPELL,wordsSave.getSpell());
        values.put(WordDbSchema.Cols.US,wordsSave.getUs_phonetic());
        values.put(WordDbSchema.Cols.UK,wordsSave.getUk_phonetic());
        values.put(WordDbSchema.Cols.EXPLAIN,wordsSave.getExplains());
        values.put(WordDbSchema.Cols.WEBEXPLAIN,wordsSave.getWebExplains());
        return values;
    }
    public void updataWord(WordsSave wordsSave){//更新数据库
        String uuidString  = wordsSave.getUUID().toString();
        ContentValues contentValues = getContentValues(wordsSave);
        mDatabase.update(WordDbSchema.WordTable.NAME,contentValues, WordDbSchema.Cols.UUID+"=?",new String[]{uuidString});
    }
    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs){
        Cursor cursor= mDatabase.query(WordDbSchema.WordTable.NAME,
                null,whereClause,whereArgs, WordDbSchema.Cols.SPELL,null,null);//mDatabase.query("")是一个简化的SQL语句=select * from table
        return new WordCursorWrapper(cursor);
    }
    public WordCursorWrapper GetWord(String word){
        Cursor cursor = mDatabase.query(WordDbSchema.WordTable.NAME,
                null,WordDbSchema.Cols.SPELL+"=?",new String[]{word}, WordDbSchema.Cols.SPELL,null,null);
        return new WordCursorWrapper(cursor);
    }

}
