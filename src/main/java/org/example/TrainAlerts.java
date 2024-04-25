package org.example;

public class TrainAlerts {
    private String title;
    private String subTitle;


    @Override
    public String toString(){
        return "{ \"Alert\":\"" + title + "\", "
                +  subTitle + "\"}";
    }

    public void setTitle(String attr) {
    }
}
