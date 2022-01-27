package com.example.adminsadewa.model;

public class User {

    String name, alamat, lat, lng, sumber, img, deskripsi, logo, img1, img2, img3;

    public User (){

    }

    public User(String name, String alamat, String lat, String lng, String sumber, String img, String deskripsi, String logo, String img1, String img2, String img3) {
        this.name = name;
        this.alamat = alamat;
        this.lat = lat;
        this.lng = lng;
        this.sumber = sumber;
        this.img = img;
        this.deskripsi = deskripsi;
        this.logo = logo;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }







}
