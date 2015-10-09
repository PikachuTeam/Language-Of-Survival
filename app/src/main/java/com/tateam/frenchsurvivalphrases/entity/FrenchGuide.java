package com.tateam.frenchsurvivalphrases.entity;


public class FrenchGuide {

    private String frenchSentence;


    public FrenchGuide( String frenchSentence ){

        this.setFrenchSentence(frenchSentence);


    }
    public FrenchGuide(){


    }
    public String getFrenchSentence() {
        return frenchSentence;
    }

    public void setFrenchSentence(String frenchSentence) {
        this.frenchSentence = frenchSentence;
    }
}
