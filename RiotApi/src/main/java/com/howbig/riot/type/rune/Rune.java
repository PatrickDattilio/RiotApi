package com.howbig.riot.type.rune;

import com.howbig.riot.type.Gold;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.item.ItemStats;

import java.util.Map;

/**
 * Created by Alex on 5/29/2014.
 */
public class Rune {
    public String name;
    public int id;
    public RuneInfo rune;
    public ItemImage image;
    public Gold gold;
    public String group;
    public String description;
    public String colloq;
    public String plaintext;
    public boolean consumed;
    public int stacks;
    public int depth;
    public boolean consumeOnFull;
    public String[] from;
    public String[] into;
    public int specialRecipe;
    public boolean inStore;
    public boolean hideFromAll;
    public String requiredChampion;
    public ItemStats stats;
    public String[] tags;
    public Map<String,Boolean> maps;


}
