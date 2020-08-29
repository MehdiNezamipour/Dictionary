package com.example.dictionary.controller.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.dictionary.R;
import com.example.dictionary.models.Word;
import com.example.dictionary.repositories.WordRepository;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditWordFragment extends DialogFragment {


    public static final String ARG_WORD_ID = "wordId";
    private EditText mEditTextEnglishWord;
    private EditText mEditTextPersianWord;

    private WordRepository mRepository;
    private Long id;
    private Word mWord;

    private AddWordFragment.OnWordAddListener mWordAddListener;

    public EditWordFragment() {
        // Required empty public constructor
    }

    public static EditWordFragment newInstance(Long id) {
        EditWordFragment fragment = new EditWordFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_WORD_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong(ARG_WORD_ID);
        }

        mRepository = WordRepository.getInstance(getActivity());
        mWord = mRepository.get(id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mWordAddListener = (AddWordFragment.OnWordAddListener) context;
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
        initUI();
        setEnableView(false);

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                .setTitle(R.string.editOrRemove)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton(R.string.save, (dialogInterface, i) -> {
                    changeWordFields();
                    mRepository.update(mWord);
                    mWordAddListener.onWordAdd();
                })
                .setNegativeButton(R.string.edit, (dialogInterface, i) -> {

                })
                .setNeutralButton(R.string.remove, (dialogInterface, i) -> {
                    mRepository.delete(mWord);
                    mWordAddListener.onWordAdd();
                }).create();

        alertDialog.setOnShowListener(dialogInterface -> {
            Button button = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    setEnableView(true);
                }
            });
        });
        return alertDialog;
    }

    private void initUI() {
        mEditTextEnglishWord.setText(mWord.getEnglish());
        mEditTextPersianWord.setText(mWord.getPersian());
    }

    private void changeWordFields() {
        mWord.setEnglish(mEditTextEnglishWord.getText().toString());
        mWord.setPersian(mEditTextPersianWord.getText().toString());
    }

    private void setEnableView(boolean b) {
        mEditTextEnglishWord.setEnabled(b);
        mEditTextPersianWord.setEnabled(b);
    }

    private void findViews(View view) {
        mEditTextEnglishWord = view.findViewById(R.id.editText_english_word);
        mEditTextPersianWord = view.findViewById(R.id.editText_persian_word);

    }
}