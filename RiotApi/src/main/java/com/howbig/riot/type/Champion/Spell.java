package com.howbig.riot.type.Champion;

import com.howbig.riot.type.Vars;

/**
 * Created by Alex on 5/18/2014.
 */
public class Spell {
    public String id;
    public String name;
    public String description;
    public String tooltip;
    public LevelTip leveltip;
    public int maxrank;
    public int[] cooldown;
    public String cooldownBurn;
    public int[] cost;
    public String costBurn;
    public int [][] effect;
    public String [] effectBurn;
    public Vars[] vars;
    public String costType;
    public int[] range;
    public String rangeBurn;
    public Image image;
    public String resource;
}
