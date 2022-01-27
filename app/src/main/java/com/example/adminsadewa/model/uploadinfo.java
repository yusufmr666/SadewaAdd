package com.example.adminsadewa.model;

public class uploadinfo {

    public String imageName;
    public String imageURL;
    public String alamat;
    public String sumber;

    public uploadinfo(){}

    public uploadinfo(String name, String url, String alamat, String sumber) {
        this.imageName = name;
        this.imageURL = url;
        this.alamat = alamat;
        this.sumber = sumber;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getAlamat() {
        return alamat;
    }

    public String getSumber() {
        return sumber;
    }

}