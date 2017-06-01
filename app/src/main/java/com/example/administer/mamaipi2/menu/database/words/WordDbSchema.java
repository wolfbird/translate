package com.example.administer.mamaipi2.menu.database.words;

/**
 * Created by administer on 2017/5/16.
 */

public class WordDbSchema {
    public static final class WordTable{//数据库的名字
        public static final String NAME = "Words";
    }
    public static final class Cols{//数据库的字段名称
        public static final String UUID = "uuid";
        public static final String SPELL = "spell";
        public static final String US = "us_phonetic";
        public static final String UK = "uk_phonetic";
        public static final String EXPLAIN = "explain";
        public static final String WEBEXPLAIN = "ebExplains";

    }
}
