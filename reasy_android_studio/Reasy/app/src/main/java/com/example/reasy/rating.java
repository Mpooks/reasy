package com.example.reasy;

public class rating {

    private int shop_id;

    private int customer_id;

    private double evaluation;

    public rating(int shop_id, int customer_id, double evaluation) {
        this.shop_id = shop_id;
        this.customer_id = customer_id;
        this.evaluation = evaluation;
    }
}
