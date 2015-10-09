package com.tateam.frenchsurvivalphrases.entity;


public class EnglishGuide {

    private String englishSentence;
    private String frenchSentence;
    private String type;

    public EnglishGuide(String englishSentence, String frenchSentence, String type){
        this.setEnglishSentence(englishSentence);
        this.setFrenchSentence(frenchSentence);
        this.setType(type);

    }
    public EnglishGuide(){


    }
    public String getEnglishSentence() {
        return englishSentence;
    }

    public void setEnglishSentence(String englishSentence) {
        this.englishSentence = englishSentence;
    }

    public String getFrenchSentence() {
        return frenchSentence;
    }

    public void setFrenchSentence(String frenchSentence) {
        this.frenchSentence = frenchSentence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
