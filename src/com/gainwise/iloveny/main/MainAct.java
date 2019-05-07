package com.gainwise.iloveny.main;

import jdk.jshell.execution.Util;

public class MainAct {

    public static void main(String[] args){
        Utils.initLogger();
        JSONFileCreator creator = new JSONFileCreator();
        creator.begin();
    }

}
