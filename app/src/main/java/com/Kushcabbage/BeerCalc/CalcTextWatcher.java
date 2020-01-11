package com.Kushcabbage.BeerCalc;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

public class CalcTextWatcher implements TextWatcher {
    Button iCalcButton;

    String iAttachedObj;
    static boolean percentready,priceready,quantityready=false;


    public CalcTextWatcher(String objattachedtoname){
        iAttachedObj=objattachedtoname;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        try{
            float iCastVar = Float.parseFloat(charSequence.toString());
            if (iAttachedObj.equals("percentage")){
                percentready=true;
            }

            if (iAttachedObj.equals("price")){
                priceready=true;
            }

            if (iAttachedObj.equals("quantity")){
                quantityready=true;
            }


        } catch (NumberFormatException NFEX) {

            if (iAttachedObj.equals("percentage")){
                percentready=false;
            }

            if (iAttachedObj.equals("price")){
                priceready=false;
            }

            if (iAttachedObj.equals("quantity")){
                quantityready=false;
            }


        }

        if (percentready && priceready && quantityready){
           MainActivity.getInstance().calcDensity();
            Log.d("debug", "new calc");
        }
        else{
            Log.d("debug", "Cant calc");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }



}
