package com.example.dictionary.controller.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.adapters.WordListAdapter;
import com.example.dictionary.controller.fragments.AddWordFragment;
import com.example.dictionary.models.Word;
import com.example.dictionary.repositories.WordRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddWordFragment.OnWordAddListener {

    public static final String ADD_FRAGMENT_TAG = "addFragment";
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private WordListAdapter mAdapter;

    private WordRepository mRepository;
    private String mSearchString = "";
    private List<Word> mEmptyList = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepository = WordRepository.getInstance(this);

        findViews();
        setListeners();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (mAdapter == null) {
            mAdapter = new WordListAdapter(this, getSupportFragmentManager());
        }
        mRecyclerView.setAdapter(mAdapter);
        updateSubtitle();
    }

    private void updateSubtitle() {
        getSupportActionBar().setSubtitle(String.valueOf(mRepository.getList().size()) + " Words");
    }

    private void findViews() {
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerView_word_list);
        mEditText = findViewById(R.id.editText_search);
    }

    private void setListeners() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordFragment addWordFragment = new AddWordFragment();
                addWordFragment.show(getSupportFragmentManager(), ADD_FRAGMENT_TAG);
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    mSearchString = charSequence.toString();
                    mAdapter.setWords(mRepository.search(mSearchString));
                } else {
                    mSearchString = "";
                    mAdapter.setWords(mEmptyList);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onWordAdd() {
        updateSubtitle();
        if (mSearchString.equals("")) {
            mAdapter.setWords(mEmptyList);
        } else {
            mAdapter.setWords(mRepository.search(mSearchString));
        }
        mAdapter.notifyDataSetChanged();
    }
}