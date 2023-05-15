package com.example.reasy;

public class reception {
    public reception(int guest_number, int reception_id, int customer_id, String date, int artist_id, int reception_area_id, int catering_id) {
        this.guest_number = guest_number;
        this.reception_id = reception_id;
        this.customer_id = customer_id;
        this.date = date;
        this.artist_id = artist_id;
        this.reception_area_id = reception_area_id;
        this.catering_id = catering_id;
    }

    private int guest_number;
    private int reception_id;
    private int customer_id;
    private String date;
    private int artist_id;
    private int reception_area_id;
    private int catering_id;



    public void changeReception()   {}
}
