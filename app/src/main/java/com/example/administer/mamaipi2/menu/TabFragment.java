package com.example.administer.mamaipi2.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administer.mamaipi2.menu.library.WordsLab;
import com.example.administer.mamaipi2.menu.library.WordsSave;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.toolbox.Loger;
import com.example.administer.mamaipi2.R;
import com.example.administer.mamaipi2.menu.network.AnJson;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by long on 2016/4/15.
 */
public class TabFragment extends Fragment {

    public static final String FRAG_KEY = "FragKey";
    public static final String URL =  "http://fanyi.youdao.com/openapi.do?keyfrom=aaa123ddd&key=336378893&type=data&doctype=json&version=1.1&q=";
    private TextView mean;
    private EditText in;
    private Button sure;
    private String Text;
    private String finalUrl;
    private String getJson ="";
    private AnJson jsonWork = new AnJson();
    private boolean isexit ;

    private void assignViews(View view) {
        mean=(TextView) view.findViewById(R.id.mean);
        in=(EditText) view.findViewById(R.id.in);
        sure=(Button) view.findViewById(R.id.sure);

        //为Button添加点击事件
        sure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Text   =in.getText().toString();


                                        if(Text==null||"".equals(Text)){
                                            Toast.makeText(getContext(),"输入框不能为空", Toast.LENGTH_LONG).show();
                                        }else {
                                            finalUrl=URL+Text;
                                            mean.setText(null);

                                            getJson(finalUrl);
                                        }



                                    }

                                }
        );
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_tab, null);
        assignViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mean.setText(null);
        Bundle b = getArguments();
        Bundle c = b.getBundle("Bundle");
        if (c != null) {
            String a = c.getString("SearchText");
            String d = "http://fanyi.youdao.com/openapi.do?keyfrom=aaa123ddd&key=336378893&type=data&doctype=json&version=1.1&q=" + a;
            RxVolley.get(finalUrl, new HttpCallback() {//永远是最后执行的
                @Override
                public void onSuccess(String t) {
                    getJson = t;
                    jsonWork.AnJson(Text,getJson);
                    WordsLab wordsLab = WordsLab.get(getActivity());
                    List<WordsSave> wordsSaves = wordsLab.getWords();
                    Log.d("Create是否获得了list",wordsSaves.toString());
                    if (wordsSaves.isEmpty()){
                        Log.d("第一次存入数据","FIrst save word\n");
                        WordsLab.get(getActivity()).addWord(jsonWork.getWordsSave());//将获得的word加入list
                    }
                    else {
                        for (int i = 0; i < wordsSaves.size(); i++) {

                            if (jsonWork.getWordsSave().getSpell().equals(wordsSaves.get(i).getSpell())) {
                                Log.d("word已经存在", jsonWork.getWordsSave().getSpell());
                                isexit = true;
                            }
                        }
                        if (isexit = false) {
                            Log.d("word不存在", jsonWork.getWordsSave().getSpell());
                            WordsLab.get(getActivity()).addWord(jsonWork.getWordsSave());//将获得的word加入list
                        }
                    }
                    isexit =false;

                    Log.d("sdf",jsonWork.getSetText());

                    mean.setText(jsonWork.getSetText());//将获得的数据显示

                }
            });
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        savedate();
    }
    @Override
    public void onPause(){
        super.onPause();
        WordsLab.get(getActivity()).updataWord(jsonWork.getWordsSave());
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        savedate();

    }


    private void savedate(){
        if(Text!=null&&!"".equals(Text)){
            Bundle bundle = new Bundle();
            bundle.putString("SearchText",Text);
            getArguments().putBundle("Bundle",bundle);
        }


    }


    private  void getJson(String finalUrl){
        RxVolley.get(finalUrl, new HttpCallback() {//永远是最后执行的
            @Override
            public void onSuccess(String t) {
                getJson = t;
                jsonWork.AnJson(Text,getJson);
                WordsLab wordsLab = WordsLab.get(getActivity());
                List<WordsSave> wordsSaves = wordsLab.getWords();
                Log.d("是否获得了list",wordsSaves.toString());
                if (wordsSaves.isEmpty()){
                    Log.d("第一次存入数据","FIrst save word\n");
                    WordsLab.get(getActivity()).addWord(jsonWork.getWordsSave());//将获得的word加入list
                }
                else {
                    for (int i = 0; i < wordsSaves.size(); i++) {
                        Log.d("已经搜索过的单词",wordsSaves.get(i).getSpell());
                        if (jsonWork.getWordsSave().getSpell().equals(wordsSaves.get(i).getSpell())) {
                            Log.d("word已经存在", jsonWork.getWordsSave().getSpell());

                            isexit = true;
                            Log.d("单词存在的状态", String.valueOf(isexit));
                        }
                    }
                    if (isexit == false) {

                        Log.d("word不存在", jsonWork.getWordsSave().getSpell());
                        WordsLab.get(getActivity()).addWord(jsonWork.getWordsSave());//将获得的word加入list
                    }
                }
                Log.d("单词的存在状态", String.valueOf(isexit));

                isexit = false;
                Log.d("sdf",getJson);

                mean.setText(jsonWork.getSetText());//将获得的数据显示

            }
        });

    }


}
