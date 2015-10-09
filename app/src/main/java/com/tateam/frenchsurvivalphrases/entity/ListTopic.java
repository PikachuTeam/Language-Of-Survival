package com.tateam.frenchsurvivalphrases.entity;


public class ListTopic {
    private String listName;


    public ListTopic(String listName){
       this.setListName(listName);

    }
    public ListTopic(){


    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
