package com.demo;

import com.demo.UserAction.UserAction;
import com.demo.parseXML.ParseXML;

public class Test {
    public static void main(String[] args) {
        UserAction userAction = (UserAction) ParseXML.createInstance("UserAction");
        userAction.exec();
    }
}
