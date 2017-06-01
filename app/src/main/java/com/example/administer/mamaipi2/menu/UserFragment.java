package com.example.administer.mamaipi2.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administer.mamaipi2.MainActivity;
import com.example.administer.mamaipi2.R;

import com.example.administer.mamaipi2.WordsShowActivity;
import com.example.administer.mamaipi2.menu.library.WordsLab;
import com.example.administer.mamaipi2.menu.library.WordsSave;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by administer on 2017/5/7.
 */


public class UserFragment extends Fragment {

    public static final String FRAG_KEY = "FragKey";
    private RecyclerView mUsersList;
    private UserAdapter mAdapter;


    private void assignViews(View view) {
        mUsersList = (RecyclerView) view.findViewById(R.id.Words_Searched);
        mUsersList.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.words, null);
        assignViews(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextView;
        private String c;
        public UserHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;

            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity a = getActivity(); String c = mTextView.getText().toString();
                    Intent i = new Intent(a, WordsShowActivity.class);
                    i.putExtra("Spell",c);
                    startActivity(i);
                }
            });
        }
    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{


        private List<WordsSave> word;
        public UserAdapter(List<WordsSave> words){
            word = words;
        }
        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            WordsSave wordsSave = word.get(position);

            holder.mTextView.setText(wordsSave.getSpell());
        }

        @Override
        public int getItemCount() {
            return word.size();
        }
    }
    private void UpdateUI(){
        WordsLab wordsLab = WordsLab.get(getActivity());
        List<WordsSave> Words = wordsLab.getWords();
       mAdapter= new UserAdapter(Words);
        mUsersList.setAdapter(mAdapter);
    }
}
