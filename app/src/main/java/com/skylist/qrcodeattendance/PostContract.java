package com.skylist.qrcodeattendance;

import android.provider.BaseColumns;

public final class PostContract {
    private PostContract() {}
    public static class  PostEntry implements BaseColumns{
        public static final String TABLE_NAME = "attendance";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REGNUMBER = "regNumber";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_DATE = "date";
    }
}
