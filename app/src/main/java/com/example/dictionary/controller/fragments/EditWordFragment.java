package com.example.dictionary.controller.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dictionary.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditWordFragment extends DialogFragment {

    public EditWordFragment() {
        // Required empty public constructor
    }

    public static AddWordFragment newInstance() {
        AddWordFragment fragment = new AddWordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_edit_word, null);


        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                .setTitle(R.string.editOrRemove)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton(R.string.save, (dialogInterface, i) -> {
                    //TODO
                })
                .setNegativeButton(R.string.edit, (dialogInterface, i) -> {

                })
                .setNeutralButton(R.string.remove, (dialogInterface, i) -> {
                    //TODO
                }).create();

        alertDialog.setOnShowListener(dialogInterface -> {
            Button button = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    //TODO
                }
            });
        });
        return alertDialog;
    }
}