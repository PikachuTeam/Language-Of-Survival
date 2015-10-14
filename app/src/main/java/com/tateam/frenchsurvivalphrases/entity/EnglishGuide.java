package com.tateam.frenchsurvivalphrases.entity;


public class EnglishGuide {

    private String englishSentence;
    private String frenchSentence;
    private String type;
    private int typeno;
    private int recent;
    public EnglishGuide(String englishSentence, String frenchSentence, String type,int typeno,int recent){
        this.setEnglishSentence(englishSentence);
        this.setFrenchSentence(frenchSentence);
        this.setType(type);
        this.setTypeno(typeno);
        this.setRecent(recent);
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

    public int getRecent() {
        return recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeno() {
        return typeno;
    }

    public void setTypeno(int typeno) {
        this.typeno = typeno;
    }
}
