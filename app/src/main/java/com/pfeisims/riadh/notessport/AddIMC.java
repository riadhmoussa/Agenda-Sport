package com.pfeisims.riadh.notessport;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.pfeisims.riadh.notessport.MainActivity.dbManager;

public class AddIMC extends AppCompatActivity {
    EditText etDateImc;
    EditText etPoids;
    EditText etTaille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imc);
        etDateImc=(EditText)findViewById(R.id.etDateImc);
        etPoids=(EditText)findViewById(R.id.etPoids);
        etTaille=(EditText)findViewById(R.id.etTaille);
    }

    public void Cancel(View view) {
        this.finish();
    }

    public void Ajouterimc(View view) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBManager.ColDate,etDateImc.getText().toString());
        contentValues.put(DBManager.ColPoids,etPoids.getText().toString());
        contentValues.put(DBManager.ColTaille,etTaille.getText().toString());
        Double imc=(Double.parseDouble(etPoids.getText().toString())/Double.parseDouble(etTaille.getText().toString()))*(Double.parseDouble(etPoids.getText().toString())/Double.parseDouble(etTaille.getText().toString()));
        contentValues.put(DBManager.ColCalImc,imc);
        long id=dbManager.InsertIMC(contentValues);
        if(id>0){
            Toast.makeText(view.getContext(),"insert with successful",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(view.getContext(),"cannot insert",Toast.LENGTH_LONG).show();
        }
        this.finish();
    }
}
