package com.howbig.riot.type.item;

import com.howbig.riot.type.Gold;

import java.util.Map;

/**
 * Created by Alex on 5/21/2014.
 */
public class Item {
    public int id;
    public String name;
    public ItemRune itemRune;
    public Gold gold;
    public String group;
    public String description;
    public String colloq;
    public String plaintext;
    public ItemImage image;
    public boolean consumed;
    public int stacks;
    public int depth;
    public boolean consumeOnFull;
    public String[] from;
    public String[] into;
    public int specialRecipe;
    public boolean inStore;
    public String requiredChampion;
    public ItemStats stats;
    public String[] tags;
    public Map<String,Boolean> maps;
}
