package com.example.administer.mamaipi2.menu.network;

import com.example.administer.mamaipi2.menu.library.WordsLab;
import com.example.administer.mamaipi2.menu.library.WordsSave;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by administer on 2017/5/7.
 */

public class AnJson {
    private String setText;
    private String WebExplains ;
    private WordsSave mWordsSave;

    public String getSetText() {
        return setText;
    }

    public void setSetText(String setText) {
        this.setText = setText;
    }

    public void AnJson(String Word,String json){

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONObject object = jsonObject.getJSONObject("basic");
            JSONArray ja = jsonObject.getJSONArray("web");
            WordsSave word = new WordsSave();

            word.setSpell(Word);
            word.setUk_phonetic(object.getString("uk-phonetic"));
            word.setUs_phonetic(object.getString("us-phonetic"));
            word.setExplains(object.getString("explains"));


            WebExplains ="";
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonObject3 = (JSONObject) ja.get(i);

                WebExplains +=jsonObject3.getString("value")+"\t";
                WebExplains += jsonObject3.getString("key")+"\n";

            } word.setWebExplains(WebExplains);
            String s="美式发音"+ word.getUs_phonetic()+"\n"+
                    "英式发音"+word.getUk_phonetic()+"\n"+
                    "释义："+"\n"+word.getExplains()+"\n"+"网络释义:"+"\n"+WebExplains;


            mWordsSave = word;
            //在顶部显示
           setSetText(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public WordsSave getWordsSave() {
        return mWordsSave;
    }
}
