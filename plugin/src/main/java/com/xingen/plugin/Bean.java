package com.xingen.plugin;

import com.xingen.pluginlibrary.BeanProvider;

/**
 * Author by ${xinGen},  Date on 2018/5/23.
 */
public class Bean implements BeanProvider{
    private String name="根根";

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }
}
