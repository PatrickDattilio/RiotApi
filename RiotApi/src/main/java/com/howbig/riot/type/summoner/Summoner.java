package com.howbig.riot.type.summoner;

import com.howbig.riot.type.Vars;
import com.howbig.riot.type.item.ItemImage;

/**
 * Created by Alex on 7/11/2014.
 */
public class Summoner {
    public String id;
    public String name;
    public String description;
    public String tooltip;
    public int maxrank;
    public LevelTip leveltip;
    public int[] cooldown;
    public String cooldownBurn;
    public int [] cost;
    public String costBurn;
    public Integer[][] effect;
    public String [] effectBurn;
    public Vars [] vars;
    public String key;
    public int summonerLevel;
    public String [] modes;
    public String costType;
    public Object range;
    public String rangeBurn;
    public ItemImage image;
    public String resource;

}
