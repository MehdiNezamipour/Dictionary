package com.example.dictionary.controller.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dictionary.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddWordFragment extends DialogFragment {


    public AddWordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_word, null);

        return new MaterialAlertDialogBuilder(getActivity())
                .setTitle(R.string.AddWord)
                .setView(view)
                .setPositiveButton(R.string.add, (dialogInterface, i) -> {

                })
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true)
                .create();

    }
}