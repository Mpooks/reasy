package com.example.reasy;

import java.util.ArrayList;

public class main_lists {
    private ArrayList<shop> shop_list = new ArrayList<shop>();
    private ArrayList<customer> customer_list = new ArrayList<customer>();
    private ArrayList<user> user_list = new ArrayList<user>();
    private ArrayList<reservation> res_list = new ArrayList<reservation>();
    private static ArrayList<job_offer> job_offers = new ArrayList<job_offer>();
    private ArrayList<reception_area> reception_area_list = new ArrayList<reception_area>();
    private static ArrayList<catering> catering_list = new ArrayList<catering>();
    private static ArrayList<artist> artist_list = new ArrayList<artist>();
    private static ArrayList<supplier> supplier_list = new ArrayList<supplier>();

    public main_lists(ArrayList<shop> shop_list, ArrayList<customer> customer_list, ArrayList<user> user_list, ArrayList<job_offer> job_offers, ArrayList<reception_area> reception_area_list, ArrayList<catering> catering_list, ArrayList<artist> artist_list, ArrayList<supplier> supplier_list, ArrayList<reservation> res_list) {
        this.shop_list = shop_list;
        this.customer_list = customer_list;
        this.user_list=user_list;
        this.job_offers = job_offers;
        this.reception_area_list = reception_area_list;
        this.catering_list = catering_list;
        this.artist_list = artist_list;
        this.supplier_list = supplier_list;
        this.res_list=res_list;
    }



    public ArrayList<reception_area> getReceptionArea(){
        return reception_area_list;
    }
    public static ArrayList<catering> getCatering(){
        return catering_list;
    }
    public static ArrayList<artist> getArtistList(){
        return artist_list;
    }

    public ArrayList<user> getUser_list() {
        return user_list;
    }

    public ArrayList<reservation> getRes_list() {
        return res_list;
    }

    public ArrayList<customer> getCustomer_list() {
        return customer_list;
    }

    public ArrayList<shop> getShop_list() {
        return shop_list;
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

    public static main_lists createLists(){
        reservation res1 = new reservation(1,5,1,2,"2023-05-20","20:30",2,null,2,null);
        reservation res2 = new reservation(2,5,2,4,"2023-05-19","21:30",4,null,1,null);
        reservation res3 = new reservation(2,5,3,2,"2023-05-01","20:30",5,null,3,null);
        reservation res4 = new reservation(3,6,4,2,"2023-05-20","22:30",1,null,4,null);
        reservation res5 = new reservation(4,7,5,3,"2023-05-01","19:30",3,null,5,null);
        ArrayList<reservation> allres = new ArrayList<reservation>();
        allres.add(res1);
        allres.add(res2);
        allres.add(res3);
        allres.add(res4);
        allres.add(res5);
        ArrayList<reservation> rs2 = new ArrayList<reservation>();
        rs2.add(res2);
        rs2.add(res3);
        ArrayList<reservation> rs1 = new ArrayList<reservation>();
        rs1.add(res1);
        ArrayList<reservation> rs3 = new ArrayList<reservation>();
        rs3.add(res4);
        ArrayList<reservation> rs4 = new ArrayList<reservation>();
        rs4.add(res5);
        ArrayList<reservation> rsc1 = new ArrayList<reservation>();
        rsc1.add(res1);
        rsc1.add(res2);
        rsc1.add(res3);
        ArrayList<reservation> rsc2 = new ArrayList<reservation>();
        rsc2.add(res4);
        ArrayList<reservation> rsc3 = new ArrayList<reservation>();
        rsc3.add(res5);
        rating rate1 = new rating(2,1,4.3);
        ArrayList<rating> ratel = new ArrayList<rating>();
        ratel.add(rate1);
        product_menu pfm1 = new product_menu(1,"First Time Omakase", 100,2,23);
        product_menu pfm2 = new product_menu(2,"Special Omakase", 250,2,2);
        product_menu pfm3 = new product_menu(3,"Crispy Rice Spicy Salmon", 35,2,18);
        product_menu pfm4 = new product_menu(4,"Wagyu Tacos", 50,2,7);
        product_menu pfm5 = new product_menu(5,"Benedict", 8,3,16);
        product_menu pfm6 = new product_menu(6,"Mmontreal", 8.5,3,9);
        product_menu pfm7 = new product_menu(7,"Meze Meze Salad", 7.8,4,23);
        product_menu pfm8 = new product_menu(8,"Cheese plateau", 16,1,7);
        product_supplier pfs1 = new product_supplier(9,"Tomatoes", 0.5125,1,80);
        product_supplier pfs2 = new product_supplier(10,"Potatoes", 0.34,1,34);
        product_supplier pfs3 = new product_supplier(11,"Cucumbers", 0.65,2,23);
        product_supplier pfs4 = new product_supplier(12,"Eggplants", 0.89,2,34);
        product_supplier pfs5 = new product_supplier(13,"Carrots", 0.42,3,69);
        product_supplier pfs6 = new product_supplier(14,"Lettuce", 0.75,3,54);
        product_supply ps1 = new product_supply(9,"Tomatoes",0.5125,1,40);
        product_order po1 = new product_order(1,"First Time Omakase",100,1,2);
        product_order po2 = new product_order(4,"Wagyu Tacos", 50,1,1);
        ArrayList<product_order> po_array=new ArrayList<product_order>();
        po_array.add(po1);
        po_array.add(po2);
        ArrayList<product_supply> ps_array=new ArrayList<product_supply>();
        ps_array.add(ps1);
        ArrayList<product_supplier> psupp_array1=new ArrayList<product_supplier>();
        psupp_array1.add(pfs1);
        psupp_array1.add(pfs2);
        ArrayList<product_supplier> psupp_array2=new ArrayList<product_supplier>();
        psupp_array2.add(pfs3);
        psupp_array2.add(pfs4);
        ArrayList<product_supplier> psupp_array3=new ArrayList<product_supplier>();
        psupp_array3.add(pfs5);
        psupp_array3.add(pfs6);
        ArrayList<product_menu> pm1_array=new ArrayList<product_menu>();
        pm1_array.add(pfm1);
        pm1_array.add(pfm2);
        pm1_array.add(pfm3);
        pm1_array.add(pfm4);
        ArrayList<product_menu> pm2_array=new ArrayList<product_menu>();
        pm2_array.add(pfm5);
        pm2_array.add(pfm6);
        ArrayList<product_menu> pm3_array=new ArrayList<product_menu>();
        pm3_array.add(pfm7);
        ArrayList<product_menu> pm4_array=new ArrayList<product_menu>();
        pm4_array.add(pfm8);
        order o1 = new order(5,1,2,250,"Online","Online",po_array);
        ArrayList<order> ol = new ArrayList<order>();
        ol.add(o1);
        supply supply1 = new supply(1,3,1,ps_array,"Leoforos Poseidonos 20 Athens",true,20.5);
        ArrayList<supply> sl = new ArrayList<supply>();
        sl.add(supply1);
        menu m1 = new menu(1,pm4_array,4.5,34);
        menu m2 = new menu(2,pm1_array,4.8,340);
        menu m3 = new menu(3,pm2_array,4.5,128);
        menu m4 = new menu(4,pm3_array,4.2,560);
        ArrayList<String> wo = new ArrayList<String>();
        wo.add("8:00-18:00");
        wo.add("8:00-20:00");
        wo.add("8:00-18:00");
        wo.add("8:00-20:00");
        wo.add("8:00-21:00");
        wo.add("8:00-21:00");
        wo.add("closed");
        job_offer jo = new job_offer(2,"waiter",800,wo,2.5,"2023-05-20","2023-06-06",null);
        ArrayList<job_offer> jl = new ArrayList<job_offer>();
        jl.add(jo);
        reception rec = new reception(125,1,5,"2023-05-25",1,1,1);
        ArrayList<reception> rl = new ArrayList<reception>();
        rl.add(rec);
        ArrayList<reservation> rt1 = new ArrayList<reservation>();
        rt1.add(res4);
        ArrayList<reservation> rt2 = new ArrayList<reservation>();
        rt2.add(res1);
        ArrayList<reservation> rt3 = new ArrayList<reservation>();
        rt3.add(res5);
        ArrayList<reservation> rt4 = new ArrayList<reservation>();
        rt4.add(res2);
        ArrayList<reservation> rt5 = new ArrayList<reservation>();
        rt5.add(res3);
        ArrayList<Integer> nt1 = new ArrayList<Integer>();
        nt1.add(6);
        ArrayList<Integer> nt4 = new ArrayList<Integer>();
        nt4.add(5);
        ArrayList<Integer> nt5 = new ArrayList<Integer>();
        nt5.add(4);
        ArrayList<Integer> nt6 = new ArrayList<Integer>();
        nt6.add(1);
        calendar c1 = new calendar(6,1,"2023-05-25");
        calendar c2 = new calendar(7,1,"2023-05-25");
        ArrayList<calendar> cl1 = new ArrayList<calendar>();
        cl1.add(c1);
        ArrayList<calendar> cl2 = new ArrayList<calendar>();
        cl2.add(c2);
        table t1 = new table(1,2,3,rt1,nt1);
        table t2 = new table(2,2,1,rt2,null);
        table t3 = new table(3,3,4,rt3,null);
        table t4 = new table(4,4,2,rt4,nt4);
        table t5 = new table(5,2,2,rt5,nt5);
        table t6 = new table(6,6,3,null,nt6);
        ArrayList<table> tl1 = new ArrayList<table>();
        tl1.add(t2);
        ArrayList<table> tl2 = new ArrayList<table>();
        tl2.add(t4);
        tl2.add(t5);
        ArrayList<table> tl3 = new ArrayList<table>();
        tl3.add(t1);
        tl3.add(t6);
        ArrayList<table> tl4 = new ArrayList<table>();
        tl4.add(t3);
        catering cat1 = new catering("CanRec",1,"Asian",500);
        catering cat2 = new catering("Eataly",2,"Italian",450);
        supplier sup1 = new supplier(1,"Jim",psupp_array1);
        supplier sup2 = new supplier(2,"Mike",psupp_array2);
        supplier sup3 = new supplier(3,"Phil",psupp_array3);
        reception_area ra1 = new reception_area("Haven",1,500,null,2000);
        reception_area ra2 = new reception_area("Pantheon",2,200,rl,1500);
        artist a1 = new artist("Tayor Swift",1,800,"pop");
        artist a2 = new artist("Ed Sheeran",2,900,"pop");
        waiter w1 = new waiter("John Green",1,2,wo);
        waiter w2 = new waiter("Jennifer Lawn",2,1,wo);
        waiter w3 = new waiter("Anastasia Pond",3,2,wo);
        waiter w4 = new waiter("Drake Lorden",4,3,wo);
        waiter w5 = new waiter("Paul Mann",5,4,wo);
        shop s1=new shop("mats@gmail.com","123","Matsuhisa Athens",2,1287,rs1,"40, Apollonos street, Vouliagmeni 166 71","Athens",wo,6,820,"Asian",7000,4500,2200,4.3,tl2,sl,"2108960510",m2);
        shop s2=new shop("sal@gmail.com","123","Salumeria",1,721.56,rs2,"Pantanassis 27","Patras",wo,2,1700,"Grill",6000,3500,2000,4.6,tl1,null,"2610225930",m1);
        shop s3=new shop("meze@gmail.com","123","MEZE MEZE",4,867,rs3,"Kudonion, Aigaleo","Athens",wo,3,851,"Grill",5500,2500,3000,4.5,tl4,null,"2105908829",m4);
        shop s4=new shop("pen@gmail.com","123","Peñarrubia Lounge",3,987.55,rs4,"Leoforos Poseidonos 20","Athens",wo,8,12000,"Bar",4500,2000,3000,4.1,tl3,null,"2109850118",m3);
        customer cus1 = new customer("josh@gmail.com","123","Josh Payne",5,450,rsc1,25,null,rl,null,3,ol,ratel,null);
        customer cus2 = new customer("alex@gmail.com","123","Alex Meyers",6,5000,rsc2,12,null,null,cl1,1,null,null,null);
        customer cus3 = new customer("kurtis@gmail.com","123","Kurtis Conner",7,50,rsc3,134,null,null,cl2,1,null,null,null);
        customer cus4 = new customer("danny@gmail.com","123","Danny Gonzalez",8,128,null,0,null,null,null,0,null,null,null);
        ArrayList<customer> fl1 = new ArrayList<customer>();
        fl1.add(cus2);
        fl1.add(cus3);
        ArrayList<customer> fl2 = new ArrayList<customer>();
        fl2.add(cus1);
        fl2.add(cus4);
        ArrayList<customer> fl3 = new ArrayList<customer>();
        fl3.add(cus1);
        ArrayList<customer> fl4 = new ArrayList<customer>();
        fl4.add(cus2);
        cus1.setFriend_list(fl1);
        cus2.setFriend_list(fl2);
        cus3.setFriend_list(fl3);
        cus4.setFriend_list(fl4);

        ArrayList<reception_area> recl = new ArrayList<reception_area>();
        recl.add(ra1);
        recl.add(ra2);
        ArrayList<catering> cl = new ArrayList<catering>();
        cl.add(cat1);
        cl.add(cat2);
        ArrayList<artist> al = new ArrayList<artist>();
        al.add(a1);
        al.add(a2);
        ArrayList<supplier> suppl = new ArrayList<supplier>();
        suppl.add(sup1);
        suppl.add(sup2);
        suppl.add(sup3);
        ArrayList<shop> slist = new ArrayList<shop>();
        slist.add(s1);
        slist.add(s2);
        slist.add(s3);
        slist.add(s4);
        ArrayList<customer> clist = new ArrayList<customer>();
        clist.add(cus1);
        clist.add(cus2);
        clist.add(cus3);
        clist.add(cus4);
        user u1 = new user("mats@gmail.com","123","Matsuhisa Athens",2,1287,rs1);
        user u2 = new user("sal@gmail.com","123","Salumeria",1,721.56,rs2);
        user u3 = new user("meze@gmail.com","123","MEZE MEZE",4,867,rs3);
        user u4 = new user("pen@gmail.com","123","Peñarrubia Lounge",3,987.55,rs4);
        user u5 = new user("josh@gmail.com","123","Josh Payne",5,450,rsc1);
        user u6 = new user("alex@gmail.com","123","Alex Meyers",6,5000,rsc2);
        user u7 = new user("kurtis@gmail.com","123","Kurtis Conner",7,50,rsc3);
        user u8 = new user("danny@gmail.com","123","Danny Gonzalez",8,128,null);
        ArrayList<user> ulist = new ArrayList<user>();
        ulist.add(u1);
        ulist.add(u2);
        ulist.add(u3);
        ulist.add(u4);
        ulist.add(u5);
        ulist.add(u6);
        ulist.add(u7);
        ulist.add(u8);
        main_lists list = new main_lists(slist,clist,ulist,jl,recl,cl,al,suppl,allres);
        return list;
    }

}
