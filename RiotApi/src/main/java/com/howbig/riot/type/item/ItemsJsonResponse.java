package com.howbig.riot.type.item;

import java.util.ArrayList;

/**
 * Created by Alex on 5/19/2014.
 */
public class ItemsJsonResponse {
    public String type;
    public String  version;
    public Item basic;
    public ArrayList<Item> data;
    public ArrayList<ItemGroup> groups;
    public ArrayList<ItemTree> tree;
}