package com.example.dictionary.controller.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.dictionary.R;
import com.example.dictionary.models.Word;
import com.example.dictionary.repositories.WordRepository;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddWordFragment extends DialogFragment {

    private EditText mEditTextEnglishWord;
    private EditText mEditTextPersianWord;

    private WordRepository mRepository;

    private OnWordAddListener mWordAddListener;


    public interface OnWordAddListener {
        void onWordAdd();
    }

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
        mRepository = WordRepository.getInstance(getActivity());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mWordAddListener = (OnWordAddListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnWordAddListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_word, null);
        findViews(view);
        return new MaterialAlertDialogBuilder(getActivity())
                .setTitle(R.string.AddWord)
                .setView(view)
                .setPositiveButton(R.string.add, (dialogInterface, i) -> {
                    if (checkInputs()) {
                        mRepository.insert(new Word(mEditTextEnglishWord.getText().toString()
                                , mEditTextPersianWord.getText().toString()));
                        mWordAddListener.onWordAdd();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true)
                .create();

    }

    //i can complete this method later
    private boolean checkInputs() {
        if (mEditTextEnglishWord.getText().toString().trim().length() == 0
                || mEditTextPersianWord.getText().toString().trim().length() == 0)
            return false;
        else
            return true;
    }

    private void findViews(View view) {
        mEditTextEnglishWord = view.findViewById(R.id.editText_english_word);
        mEditTextPersianWord = view.findViewById(R.id.editText_persian_word);
    }
}