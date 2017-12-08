package com.pfeisims.riadh.notessport;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Riadh on 07/12/2017.
 */

public class choix extends DialogFragment implements View.OnClickListener{
View view;
Button btnact;
Button btnimc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.choix,container,false);
        btnact=(Button)view.findViewById(R.id.btnact);
        btnimc=(Button)view.findViewById(R.id.btnimc);
        btnact.setOnClickListener(this);
        btnimc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Button bu=(Button)view;
        if(bu.getText().toString().equalsIgnoreCase("Ajouter Activity")){

            Intent intent=new Intent(view.getContext(),AddAct.class);
            startActivity(intent);
        }else if(bu.getText().toString().equalsIgnoreCase("Ajouter IMC")){
            Intent intent=new Intent(view.getContext(),AddIMC.class);
            startActivity(intent);

        }
        this.dismiss();
    }
}
