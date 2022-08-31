package com.example.myapplication;

public class songs {
    String image;
    String SongName;
    String url;
    String ArtistName;
    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        this.ArtistName = ArtistName;
    }


     public songs(){};

    public void setImage(String image) {
        this.image = image;
    }

    public void setSongName(String songName) {
        this.SongName = SongName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getSongName() {
        return SongName;
    }

    public String getUrl() {
        return url;
    }
}
