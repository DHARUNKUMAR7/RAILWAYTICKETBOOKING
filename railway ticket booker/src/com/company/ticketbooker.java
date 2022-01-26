package com.company;

import java.util.*;

public class ticketbooker {
    static int availabelowerberthticket=1;
    static int availabemiddleberthticket=1;
    static int availabeupperberthticket=1;
    static int availaberacticket=1;
    static int availabewaitinglistticket=1;

    static Queue<Integer> racticket=new LinkedList<>();
    static Queue<Integer> waitinglistticket=new LinkedList<>();
    static List<Integer>  bookedticket=new ArrayList<>();

    static List<Integer> lowerberthposition =new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleberthposition =new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperberthposition =new ArrayList<>(Arrays.asList(1));
    static List<Integer> racposition =new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingposition =new ArrayList<>(Arrays.asList(1));

    static Map<Integer,passenger> passengers=new HashMap<>();

    public void booktickets(passenger p,int berthinfo,String birthid){
        p.alloted=birthid;
        p.num=berthinfo;
        bookedticket.add(p.passengerid);
        passengers.put(p.passengerid,p);
        System.out.println("-----------------------------Booked successfully");
    }
    public void addtorac(passenger p,int racinfo,String berthid){
        p.alloted=berthid;
        p.num=racinfo;
        passengers.put(p.passengerid,p);
        racticket.add(p.passengerid);
        System.out.println("-----------------------------Booked rac successfully");
    }

    public void addtowaitinglist(passenger p, int  waitinginfo, String waitid) {
        p.num=waitinginfo;
        p.alloted=waitid;
        passengers.put(p.passengerid,p);
        waitinglistticket.add(p.passengerid);
        System.out.println("--------------------------------Booked waiting successfully");
    }

    public void cancelticket(int id) {
        passenger p=passengers.get(id);
        passengers.remove(id);
       // bookedticket.remove(id);
        int bookedposition =p.num;
        System.out.println("----------------------------------Cancelled successfully");
        if((p.alloted).equals("L")){
            availabelowerberthticket++;
            lowerberthposition.add(id);
        }
        if((p.alloted).equals("M")){
            availabemiddleberthticket++;
            middleberthposition.add(id);
        }
        if((p.alloted).equals("U")){
            availabeupperberthticket++;
            upperberthposition.add(id);
        }
        if(racticket.size()>0){
            passenger rac=passengers.get(racticket.poll());
            int passengerid=rac.num;
            availaberacticket++;

            racticket.remove(rac.num);
            racposition.add(rac.num);
            if(waitinglistticket.size()>0){
                passenger wait=passengers.get(waitinglistticket.poll());
                int waitpos=wait.num;
                waitingposition.add(waitpos);
                availabewaitinglistticket++;
                waitinglistticket.remove(wait.passengerid);

                wait.num=racposition.get(0);
                wait.alloted="RAC";
                racposition.remove(0);
                racticket.add(wait.passengerid);

                availaberacticket--;
            }
             Main.bookticket(rac);
        }
    }

    public void printavailable() {
        System.out.println("Available lower berth"+availabelowerberthticket);
        System.out.println("Available middle berth"+availabemiddleberthticket);
        System.out.println("Available upper berth"+availabeupperberthticket);
        System.out.println("Available RAC berth"+availaberacticket);
        System.out.println("Available  WAITING berth"+availabewaitinglistticket);
        System.out.println("------------------------------");
    }

    public void printpassengers() {
        if(passengers.size()<1){
            System.out.println("NO PASSENGERS AVAILABLE ");
        }
        else{
            for(passenger p: passengers.values()){
                System.out.println("PASSENGER ID"+p.passengerid);
                System.out.println("name"+p.name);
                System.out.println("age"+p.age);
                System.out.println("status"+p.num+p.alloted);
                System.out.println("----------------------------");
            }
        }
    }
}
