package com.example.administer.mamaipi2.menu.database.words;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.administer.mamaipi2.menu.library.WordsSave;

/**
 * Created by administer on 2017/5/16.
 */

public class WordCursorWrapper extends CursorWrapper {
    public WordCursorWrapper(Cursor cursor) {//先输入查询的SQL语句获得需要的数据
        super(cursor);
    }
    public WordsSave getWord(){//将语句查询获得的数据分割并且赋予word
        String uuidString = getString(getColumnIndex(WordDbSchema.Cols.UUID));
        String spell = getString(getColumnIndex(WordDbSchema.Cols.SPELL));
        String Us = getString(getColumnIndex(WordDbSchema.Cols.US));
        String Uk = getString(getColumnIndex(WordDbSchema.Cols.UK));
        String explain = getString(getColumnIndex(WordDbSchema.Cols.EXPLAIN));
        String webexplains = getString(getColumnIndex(WordDbSchema.Cols.WEBEXPLAIN));

        WordsSave word = new WordsSave();
        word.setSpell(spell);
        word.setUs_phonetic(Us);
        word.setUk_phonetic(Uk);
        word.setExplains(explain);
        word.setWebExplains(webexplains);
        return word;
    }

    public String getWordProperty(){
        String spell = getString(getColumnIndex(WordDbSchema.Cols.SPELL));
        String Us = getString(getColumnIndex(WordDbSchema.Cols.US));
        String Uk = getString(getColumnIndex(WordDbSchema.Cols.UK));
        String explain = getString(getColumnIndex(WordDbSchema.Cols.EXPLAIN));
        String webexplains = getString(getColumnIndex(WordDbSchema.Cols.WEBEXPLAIN));
        String a = spell+"\n"+Us+"\n"+Uk+"\n"+explain+"\n"+webexplains+"\n";
        return a;
    }
}
