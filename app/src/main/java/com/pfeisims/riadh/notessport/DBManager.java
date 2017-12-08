package com.pfeisims.riadh.notessport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

/**
 * Created by Riadh on 07/12/2017.
 */

public class DBManager {
    private  SQLiteDatabase sqldb;
    static final String DBName="NoteSport";


    static final String ColID="ID";
    static final String TableName="Notes";
    static final String ColName="Name";
    static final String ColJour="Jour";
    static final String ColHeure="Heure";


    static final String ColI="ID";
    static final String TableNam="IMC";
    static final String ColDate="Date";
    static final String ColPoids="Poids";
    static final String ColTaille="Taille";
    static final String ColCalImc="CalImc";




    static final int DBVersion=1;
    static final String CreateTable="Create Table IF NOT EXISTS "+TableName+" (ID integer PRIMARY KEY AUTOINCREMENT,"+ColName+" text,"+
            ColJour+" text, "+ColHeure+" text);";
    static final String DropTable="Drop Table IF EXISTS "+TableName;

    static final String CreateTabl="Create Table IF NOT EXISTS "+TableNam+" (ID integer PRIMARY KEY AUTOINCREMENT, "+
            ColDate+" text, "+
            ColPoids+" text, "+ColTaille+" text, "+ColCalImc+" text);";
    static final String DropTabl="Drop Table IF EXISTS "+TableNam;

    static class DatabaseHelperCour extends SQLiteOpenHelper{
        Context context;
        public DatabaseHelperCour(Context context) {
            super(context, DBName, null, DBVersion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table "+TableName+" is Created",Toast.LENGTH_LONG).show();
            db.execSQL(CreateTabl);
            Toast.makeText(context,"Table "+TableNam+" is Created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(DropTable);
            db.execSQL(DropTabl);
            onCreate(db);

        }
    }
    public DBManager(Context context){
        DatabaseHelperCour db=new DatabaseHelperCour(context);
        sqldb=db.getWritableDatabase();

    }
    public long InsertAct(ContentValues Values){
        long ID= sqldb.insert(TableName,"",Values);
        return ID;
    }
    public Cursor queryAct(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableName);
        Cursor cursor=qb.query(sqldb,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }
    public int DeleteAct(String Selection, String[] SelectionArgs){
        int count=sqldb.delete(TableName,Selection,SelectionArgs);
        return count;
    }
    public int UpdateAct(ContentValues values, String Selection, String[] SelectionArgs){
        int count=sqldb.update(TableName,values,Selection,SelectionArgs);
        return count;
    }
    public long InsertIMC(ContentValues Values){
        long ID= sqldb.insert(TableNam,null,Values);
        return ID;
    }
    public Cursor queryIMC(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableNam);
        Cursor cursor=qb.query(sqldb,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

    public int DeleteIMC(String Selection, String[] SelectionArgs){
        int count=sqldb.delete(TableNam,Selection,SelectionArgs);
        return count;
    }





}