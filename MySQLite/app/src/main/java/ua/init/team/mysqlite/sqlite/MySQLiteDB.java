package ua.init.team.mysqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by dev on 21.09.17.
 */

public class MySQLiteDB extends SQLiteOpenHelper implements MyDBHelper{

    private static final String TAG = "myLogs";
        //Cоздание базы данных
    public MySQLiteDB(Context context) {
        super(context, "TraLaLa", null, 1);
    }
        //Cоздание таблицы в данной базе данных(0)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Запрос на создание таблицы в базе данных(1)
        String mySqLite = "CREATE TABLE " + "TraLaLa" + "("+
	"_ID"+	" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"+
	"NAME"+	" TEXT NOT NULL," +
	"SECOND_NAME"+	" TEXT NOT NULL,"+
	"B_DAY"+" TEXT NOT NULL"+
    ")";
        //Выполнение запроса(Наш запрос: см(1)) (2)
            sqLiteDatabase.execSQL(mySqLite);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Проверка версии таблицы(3)
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + " TraLaLa");
        onCreate(sqLiteDatabase);

    }

    @Override
    public void addModels(String name, String secondName, String bDay) {
        //Сохздаем обьект базы данных для обращения к ней
        SQLiteDatabase db = this.getWritableDatabase();

        //Создание обькта, который может хранить строку из базы данных
        ContentValues values = new ContentValues();

        /*  Влкадываем данный через(клшюч , значение)
             где ключ это название столбца из базы данных.
        */

        values.put("NAME", name);
        values.put("SECOND_NAME", secondName);
        values.put("B_DAY", bDay);
        //Все заданные значение вкладываем в таблицу
        db.insert("TraLaLa", null, values);
        db.close();

    }

    @Override
    public String getModels(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Курсор считывает данные из базы данных.
        Cursor cursor = db.query("TraLaLa", new String[] { "_ID",
                        "NAME", "SECOND_NAME", "B_DAY" }, "_ID" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);


        Log.d(TAG,"Cursor " + cursor.toString());

        if (cursor != null){
            cursor.moveToFirst();
        }

        String models = Integer.parseInt(cursor.getString(0))
                +" "
                + cursor.getString(1)
                +" "
                + cursor.getString(2)
                +" "
                + cursor.getString(3);

        return models;    }

    @Override
    public List<String> getAllModels() {
        return null;
    }

    public int [] getAllId(){
        SQLiteDatabase db = this.getReadableDatabase();

        String getAll = "select _ID from TraLaLa where id = 1";

        return db.query("TraLaLa", new String[] { "_ID"}, null, null, null, null, null);
    }


}
