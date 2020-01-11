package com.Kushcabbage.BeerCalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    EditText iPercentageEdit, iQuantityEdit,iPriceEdit;
    TextView iOutputDensity;
    Spinner iContainerVolume;
    int currentContainerSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;
        iPercentageEdit = findViewById(R.id.percent);
        iQuantityEdit = findViewById(R.id.quantity);
        iPriceEdit = findViewById(R.id.price);
        iOutputDensity = findViewById(R.id.densityoutput);
        iContainerVolume = findViewById(R.id.containervol);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.container_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        iContainerVolume.setAdapter(adapter);

        iContainerVolume.setSelection(4);


        iPercentageEdit.addTextChangedListener(new CalcTextWatcher("percentage"));
        iPriceEdit.addTextChangedListener(new CalcTextWatcher("price"));
        iQuantityEdit.addTextChangedListener(new CalcTextWatcher("quantity"));

        currentContainerSelect=iContainerVolume.getSelectedItemPosition();

        iContainerVolume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentContainerSelect != i){
                    calcDensity();
                }
                currentContainerSelect=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void calcDensity(){
        //Intent intent = new Intent(this,SecondActivity.class);
        //startActivity(intent);


        try {
            float iQuantityFloat = Float.valueOf(iQuantityEdit.getText().toString());
            float iPercentFloat = Float.valueOf(iPercentageEdit.getText().toString());
            float iPriceFloat = Float.valueOf(iPriceEdit.getText().toString());

            int[] iVolumes= {1000,750,700,658,440,330};


            //price/(percent*volume*quantity)

            if(iPercentFloat>100 || iPercentFloat <0){              ////TODO learn how to create dialogue fragments
                new BadMathDialogue();
            }



            int iContainer = iVolumes[iContainerVolume.getSelectedItemPosition()];

            float val = (iPriceFloat/((iPercentFloat/100)*iContainer*iQuantityFloat)*100);      //price divided by alcohol in ml in beverage (*100 for pence)

            //DecimalFormat df = new DecimalFormat("#.##");
            ///val = Float.valueOf(df.format(val));

            int iPenceVal= Math.round(val);

            iOutputDensity.setText(String.valueOf(iPenceVal)+"p/%");


        }
        catch (IllegalArgumentException iaex){
            System.out.println("failed to cast to inputs to float");
        }





    }

    public static MainActivity getInstance(){
        return instance;
    }


}

