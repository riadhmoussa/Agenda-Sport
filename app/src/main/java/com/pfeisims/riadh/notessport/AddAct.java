package com.pfeisims.riadh.notessport;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.pfeisims.riadh.notessport.MainActivity.dbManager;

public class AddAct extends AppCompatActivity {
    EditText etName;
    EditText etJour;
    EditText etHeure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName=(EditText)findViewById(R.id.etName);
        etJour=(EditText)findViewById(R.id.etJour);
        etHeure=(EditText)findViewById(R.id.etHeure);
    }

    public void Cancel(View view) {
        this.finish();
    }

    void setTime(int Hour,int Minute,int Year,int Month,int Day){
        SaveData saveData=new SaveData(this);
        saveData.AlarmSet(Hour,Minute,Year,Month,Day);
        saveData.SaveData(Hour,Minute,Year,Month,Day);
    }
    public void Ajouter(View view) {
        String[] D=etHeure.getText().toString().split(":");

        Toast.makeText(this,"Alarm Add",Toast.LENGTH_SHORT).show();
        setTime(Integer.parseInt(D[0]),Integer.parseInt(D[1]),2017,12,8);
        ContentValues Values=new ContentValues();
        Values.put(DBManager.ColName,etName.getText().toString());
        Values.put(DBManager.ColJour,etJour.getText().toString());
        Values.put(DBManager.ColHeure,etHeure.getText().toString());
        long id=dbManager.InsertAct(Values);
        if(id>0){
            Toast.makeText(view.getContext(),"insert with successful",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(view.getContext(),"cannot insert",Toast.LENGTH_LONG).show();
        }
        this.finish();
    }
}
