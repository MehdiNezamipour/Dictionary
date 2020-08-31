package com.example.dictionary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.controller.fragments.EditWordFragment;
import com.example.dictionary.models.Word;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordHolder> {

    private List<Word> mWords = new ArrayList<>();
    private Context mContext;
    private FragmentManager mFragmentManager;


    public WordListAdapter(Context context, FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    public void setWords(List<Word> words) {
        mWords = words;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.word_row_layout, viewGroup, false);
        return new WordHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder wordHolder, int i) {
        wordHolder.bindWord(mWords.get(i));
    }


    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class WordHolder extends RecyclerView.ViewHolder {

        public static final String EDIT_WORD_FRAGMENT_TAG = "editWordFragment";
        private TextView mTextViewEnglishWord;
        private TextView mTextViewPersianWord;
        private CardView mCardView;
        private ImageView mImageViewShareWord;


        public WordHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewEnglishWord = itemView.findViewById(R.id.textView_word_english);
            mTextViewPersianWord = itemView.findViewById(R.id.textView_word_persian);
            mCardView = itemView.findViewById(R.id.cardView_word_container);
            mImageViewShareWord = itemView.findViewById(R.id.imageView_share_word);


        }

        public void bindWord(Word word) {
            mTextViewEnglishWord.setText(word.getEnglish());
            mTextViewPersianWord.setText(word.getPersian());
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditWordFragment editWordFragment = EditWordFragment.newInstance(word.getId());
                    editWordFragment.show(mFragmentManager, WordHolder.EDIT_WORD_FRAGMENT_TAG);
                }
            });
            mImageViewShareWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getWordData(word));
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.wordShareSubject);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    if (sendIntent.resolveActivity(mContext.getPackageManager()) != null)
                        mContext.startActivity(shareIntent);

                }
            });
        }

        public String getWordData(Word word) {
            return "English Word : " + word.getEnglish() + "\n" + "Persian Meaning : " + word.getPersian();
        }
    }
}
