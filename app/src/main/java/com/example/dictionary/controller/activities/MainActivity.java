package com.example.dictionary.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.dictionary.R;
import com.example.dictionary.adapters.WordListAdapter;
import com.example.dictionary.controller.fragments.AddWordFragment;
import com.example.dictionary.repositories.WordRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AddWordFragment.OnWordAddListener {

    public static final String ADD_FRAGMENT_TAG = "addFragment";
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private WordListAdapter mAdapter;

    private WordRepository mRepository;


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
        mAdapter.setWords(mRepository.getList());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

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


        //TODO
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onWordAdd() {
        mAdapter.setWords(mRepository.getList());
        mAdapter.notifyDataSetChanged();
    }
}