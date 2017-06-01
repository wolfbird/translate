package com.example.administer.mamaipi2.menu.library;

import java.util.List;
import java.util.UUID;

/**
 * Created by administer on 2017/5/7.
 */

public class WordsSave {
    private String Spell;
    private String us_phonetic;
    private String uk_phonetic;
    private String explains;
    private String WebExplains;
    private UUID mUUID;

    public UUID getUUID() {
        return mUUID;
    }
    public WordsSave(){
        this(UUID.randomUUID());
    }
    public WordsSave(UUID uuid){
        mUUID = uuid;
    }

    public String getWebExplains() {
        return WebExplains;
    }

    public void setWebExplains(String webExplains) {
        WebExplains = webExplains;
    }

    public String getSpell() {
        return Spell;
    }

    public void setSpell(String spell) {
        Spell = spell;
    }

    public String getUs_phonetic() {
        return us_phonetic;
    }

    public void setUs_phonetic(String us_phonetic) {
        this.us_phonetic = us_phonetic;
    }

    public String getUk_phonetic() {
        return uk_phonetic;
    }

    public void setUk_phonetic(String uk_phonetic) {
        this.uk_phonetic = uk_phonetic;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }
}
