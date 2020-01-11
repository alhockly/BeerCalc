package com.Kushcabbage.BeerCalc;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class BadMathDialogue extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Percentage must be between 0 and 100");

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }


