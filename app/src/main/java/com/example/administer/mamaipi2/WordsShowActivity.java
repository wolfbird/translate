package com.example.administer.mamaipi2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administer.mamaipi2.menu.library.WordsLab;
import com.example.administer.mamaipi2.menu.library.WordsSave;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class WordsShowActivity extends AppCompatActivity {
    private TextView WordsText;
    private String Spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_show);
        WordsText = (TextView) findViewById(R.id.WordsShow);
        Spell = getIntent().getStringExtra("Spell");


        WordsLab wordsLab = WordsLab.get(WordsShowActivity.this);
        String Words = wordsLab.getPointWord(Spell);
        WordsText.setText(Words);

    }
}
