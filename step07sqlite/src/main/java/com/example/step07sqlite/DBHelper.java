package com.example.step07sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    DB 생성 및 리셋을 도와주는 도우미 클래스 만들기
    - SQLiteOpenHelper  추상클래스를 상속받아서 만든다.
 */
public class DBHelper extends SQLiteOpenHelper {
    // 생성자
    public DBHelper(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    //APP에서 DB를 처음 사용할 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //사용할 테이블을 만들면 된다.
        String sql ="CREATE TABLE todo"
                + "(num INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "content TEXT, regdate TEXT)";
        db.execSQL(sql);

    }

    //DBHelper객체를 생성할 때 version 숫자가 올라가면(변경되면) 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //업그레이드 할 내용을 작성하면 된다.
        db.execSQL("DROP TABLE IF EXISTS todo");
        //다시 만들어 질 수 있도록 onCreate() 메소드를 호출한다.
        onCreate(db);

    }
}
