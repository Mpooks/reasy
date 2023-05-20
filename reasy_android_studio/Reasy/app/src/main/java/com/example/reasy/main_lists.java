package com.example.reasy;

import java.util.ArrayList;

public class main_lists {
    private ArrayList<shop> shop_list = new ArrayList<shop>();
    private ArrayList<user> user_list = new ArrayList<user>();
    private ArrayList<customer> customer_list = new ArrayList<customer>();
    private ArrayList<job_offer> job_offers = new ArrayList<job_offer>();
    private static ArrayList<reception_area> reception_area_list = new ArrayList<reception_area>();
    private ArrayList<catering> catering_list = new ArrayList<catering>();
    private ArrayList<artist> artist_list = new ArrayList<artist>();
    private ArrayList<supplier> supplier_list = new ArrayList<supplier>();

    public main_lists(ArrayList<shop> shop_list, ArrayList<user> user_list, ArrayList<customer> customer_list, ArrayList<job_offer> job_offers, ArrayList<reception_area> reception_area_list, ArrayList<catering> catering_list, ArrayList<artist> artist_list, ArrayList<supplier> supplier_list) {
        this.shop_list = shop_list;
        this.user_list = user_list;
        this.customer_list = customer_list;
        this.job_offers = job_offers;
        this.reception_area_list = reception_area_list;
        this.catering_list = catering_list;
        this.artist_list = artist_list;
        this.supplier_list = supplier_list;
    }



    public static ArrayList<reception_area> getReceptionArea(){
        return reception_area_list;
    }
    public ArrayList<catering> getCatering(){
        return catering_list;
    }
    public ArrayList<artist> getArtistList(){
        return artist_list;
    }
    public ArrayList<supplier> getSupplierList(){
        return supplier_list;
    }
    public ArrayList<job_offer> getJobOffer(){
        return job_offers;
    }
    public void addToJobOffer(job_offer jo){
        job_offers.add(jo);
    }

}
