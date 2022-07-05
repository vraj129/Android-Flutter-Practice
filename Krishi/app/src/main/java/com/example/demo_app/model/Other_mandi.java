package com.example.demo_app.model;

public class Other_mandi {

    private String crop_id,district,district_id,hindi_name,id,image,km,last_date,lat,lng,market,meters,state;

    public String getCrop_id() {
        return crop_id;
    }

    public void setCrop_id(String crop_id) {
        this.crop_id = crop_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getHindi_name() {
        return hindi_name;
    }

    public void setHindi_name(String hindi_name) {
        this.hindi_name = hindi_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMeters() {
        return meters;
    }

    public void setMeters(String meters) {
        this.meters = meters;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Other_mandi{" +
                "crop_id='" + crop_id + '\'' +
                ", district='" + district + '\'' +
                ", district_id='" + district_id + '\'' +
                ", hindi_name='" + hindi_name + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", km='" + km + '\'' +
                ", last_date='" + last_date + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", market='" + market + '\'' +
                ", meters='" + meters + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
