package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        boolean a=true;
        while (a){
            System.out.println("1.Book Ticket \n 2.Cancel ticket \n  3.Available tickets \n 4.Booked tickets \n 5.exit" );
            int input= in.nextInt();
            switch (input){
                case 1:
                    System.out.println("ENTER PASSENGER NAME,AGE,BERTHPREFERENCE  ");
                    in.nextLine();
                    String name=in.nextLine();
                    int age=in.nextInt();
                    in.nextLine();
                    String berthpreference=in.nextLine();
                    passenger p=new passenger(name,age,berthpreference);
                    bookticket(p);
                    break;
                case 2:
                    System.out.println("Cancel passenger id");
                    int c=in.nextInt();
                    cancelticket(c);
                    break;
                case 3:
                    //ticketbooker booker=new ticketbooker();
                    ticketbooker.printavailable();
                    break;
                case 4:
                    ticketbooker booker1=new ticketbooker();
                    booker1.printpassengers();
                    break;
                case 5:
                    a=false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
     public static void cancelticket(int id){
        ticketbooker booker=new ticketbooker();
        if(!booker.passengers.containsKey(id)) System.out.println("passenger id not available");
        else booker.cancelticket(id);
     }
    static void bookticket(passenger p) {
        ticketbooker booker=new ticketbooker();
        if(ticketbooker.availabewaitinglistticket==0){
            System.out.println("No Tickets Available");
            return;
        }
        if((p.berthpreference.equals("L")&&ticketbooker.availabelowerberthticket>0)||
                (p.berthpreference.equals("M")&&ticketbooker.availabemiddleberthticket>0)||
                (p.berthpreference.equals("U")&&ticketbooker.availabeupperberthticket>0)){
            System.out.println("preferred berth available");
            System.out.println(p.berthpreference);
            if(p.berthpreference.equals("L")){
                System.out.println("lower berth given");
                booker.booktickets(p,(ticketbooker.lowerberthposition.get(0)),"L");
                ticketbooker.lowerberthposition.remove(0);
                ticketbooker.availabelowerberthticket--;
            }
            else if(p.berthpreference.equals("M")){
                System.out.println("Middle berth given");
                booker.booktickets(p,(ticketbooker.middleberthposition.get(0)),"M");
                ticketbooker.middleberthposition.remove(0);
                ticketbooker.availabemiddleberthticket--;
            }
            else if(p.berthpreference.equals("U")){
                System.out.println("Upper berth given");
                booker.booktickets(p,(ticketbooker.upperberthposition.get(0)),"U");
                ticketbooker.upperberthposition.remove(0);
                ticketbooker.availabeupperberthticket--;
            }
        }
        else if(ticketbooker.availabelowerberthticket>0){
            System.out.println("lower berth given");
            booker.booktickets(p,(ticketbooker.lowerberthposition.get(0)),"L");
            ticketbooker.lowerberthposition.remove(0);
            ticketbooker.availabelowerberthticket--;
        }
        else if(ticketbooker.availabemiddleberthticket>0){
            System.out.println("Middle berth given");
            booker.booktickets(p,(ticketbooker.middleberthposition.get(0)),"M");
            ticketbooker.middleberthposition.remove(0);
            ticketbooker.availabemiddleberthticket--;
        }
        else if(ticketbooker.availabeupperberthticket>0){
            System.out.println("Upper berth given");
            booker.booktickets(p,(ticketbooker.upperberthposition.get(0)),"U");
            ticketbooker.upperberthposition.remove(0);
            ticketbooker.availabeupperberthticket--;
        }
        else if(ticketbooker.availaberacticket>0){
            System.out.println("Rac Available");
            booker.addtorac(p,(ticketbooker.racposition.get(0)),"RAC");
            ticketbooker.racposition.remove(0);
            ticketbooker.availaberacticket--;
        }
        else if(ticketbooker.availabewaitinglistticket>0){
            System.out.println("Waiting list available");
            booker.addtowaitinglist(p,ticketbooker.waitingposition.get(0),"waiting list");
             ticketbooker.availabewaitinglistticket--;
             ticketbooker.waitingposition.remove(0);
        }
    }
}
