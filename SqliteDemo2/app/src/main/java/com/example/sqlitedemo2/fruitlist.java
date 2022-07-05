package com.example.sqlitedemo2;

import android.provider.BaseColumns;

public class fruitlist {

    public fruitlist() {
    }

    public static final class fruitadd implements BaseColumns
    {
        public static final String TABLE_NAME = "fruits";
        public static final String COLUMN_NAME ="name";
    }
}
