package org.example;

public class Alert {
    private String header;
    private String subheader;
    private String bodyContent;

    public Alert(String header, String subheader, String bodyContent){
        this.header = header;
        this.subheader = subheader;
        this.bodyContent = bodyContent;
    }

    public Alert(){
        header = null;
        subheader = null;
        bodyContent = null;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public String getHeader() {
        return header;
    }

    public String getSubheader() {
        return subheader;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setSubheader(String subheader) {
        this.subheader = subheader;
    }
}

