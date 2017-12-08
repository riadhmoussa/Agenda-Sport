package com.pfeisims.riadh.notessport;

/**
 * Created by Riadh on 07/12/2017.
 */

public class AdapterIMC {
    public String ID;
    public  String Date;
    public  String Poids;
    public String Taille;
    public String CalImc;
    //for news details
    AdapterIMC(String ID,String Date, String Poids, String Taille,String CalImc)
    {
        this. Date=Date;
        this. Poids=Poids;
        this.Taille=Taille;
        this.CalImc=CalImc;
    }
}
