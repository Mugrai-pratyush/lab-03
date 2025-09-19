package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {


    interface EditcityDialogListner{
        void editCity(City city,int position);
    }
    private EditCityFragment.EditcityDialogListner listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityFragment.AddCityDialogListener) {
            listener = (EditCityFragment.EditcityDialogListner) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener" );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle pos=getArguments();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Edit", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    int position=pos.getInt("positionofclick",-1);
                    if(pos == null){
                        position=-1;
                    }
                    listener.editCity(new City(cityName, provinceName),position);
                })
                .create();

    }}

