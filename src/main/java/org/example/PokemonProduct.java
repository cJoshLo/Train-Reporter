package org.example;

public class PokemonProduct {
    private String url;
    private String image;
    private String name;
    private String price;

    @Override
    public String toString(){
        return "{ \"url\":\"" + url + "\", "
                + " \"image\": \"" + image + "\", "
                + "\"name\":\"" + name + "\", "
                + "\"price\": \"" + price + "\" }";
    }

    public void setUrl(String attr) {
        url = attr;
    }

    public void setImage(String attr) {
        image = attr;
    }

    public void setName(String h2) {
        name = h2;
    }

    public void setPrice(String span) {
        price = span;
    }
}
