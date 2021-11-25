package persons;

import database.EditFile;
import database.MemberToSave;
import database.ReadAllMembers;
import factory.MemberGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Cashier {
    private ArrayList<Member> members = new ArrayList<Member>();
    private ReadAllMembers readAllMembers = new ReadAllMembers();
    private ArrayList<Member> hasntPayedMembers = new ArrayList<Member>();
    private Member memberToChangeStatus;



    public void showContingentPrices(){
        System.out.println("Contingent list:");
        System.out.println("Under 18 years......... 1000 dkk");
        System.out.println("Over 18 years.......... 1600 dkk");
        System.out.println("Over 60 years.......... 1200 dkk");
        System.out.println("Passive membership...... 500 dkk");
    }

    public void getExpectedContigentRevenue(){
        int expectedContigent = 0;
        members.removeAll(members);
        members = readAllMembers.ReadAllMembers();


        for (int i = 0; i <members.size() ; i++) {

            if (members.get(i).isActive() == false){
                expectedContigent += 500;
            } else if (members.get(i).getAge() < 18){
                expectedContigent += 1000;
            } else if (members.get(i).getAge() >=18 && members.get(i).getAge() < 60){
                expectedContigent += 1600;
            } else if (members.get(i).getAge() >= 60){
                expectedContigent += (1600*0.75);
            } else {
            }
            //System.out.println(expectedContigent);
        }
        System.out.println("Yearly expected revenue: " + expectedContigent + " dkk.");
    }

    public void getMembersWhoHasntPayed(){

       members.removeAll(members);
       hasntPayedMembers.removeAll(hasntPayedMembers);

        members = readAllMembers.ReadAllMembers();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).isHasPaid() == false) {
                hasntPayedMembers.add(members.get(i));
            }
        }
        System.out.println(hasntPayedMembers.size());

        for (Member member:hasntPayedMembers) {
            System.out.println(member);

        }
    }


    public void setMembersWhoHasntPayed() throws IOException {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Type the memberId to change payment status");
            int userInput = scanner.nextInt();
        for (int i = 0; i < hasntPayedMembers.size(); i++) {
            if (userInput == hasntPayedMembers.get(i).getMemberId()){
                hasntPayedMembers.get(i).setHasPaid(true);
                members.add(hasntPayedMembers.get(i));

                for (int j = 0; j < members.size() ; j++) {
                    if (members.get(j).getMemberId() == hasntPayedMembers.get(i).getMemberId()) {
                        Collections.swap(members, j, members.size()-1);
                       // members.remove(members.size()-1);
                        new EditFile().removeMember(members,members.get(0));
                        //new EditFile().removeMember(members,members.get(members.size()-1));
                    }
            }
        }


        }


        /*
        for (int i = 0; i < hasntPayedMembers.size(); i++) {
            System.out.println("Type the memberId to change payment status");
            int userInput = scanner.nextInt();
            if (hasntPayedMembers.get(i).getMemberId() == userInput){
                hasntPayedMembers.get(i).setHasPaid(true);

                for (int j = 0; j < members.size() ; j++) {
                    if (members.get(j).getMemberId() == hasntPayedMembers.get(i).getMemberId())

                    new EditFile().removeMember(members,members.get(j));

                }
                members.add(hasntPayedMembers.get(i));

                for (int j = 0; j < members.size(); j++) {
                    new EditFile().addMember(members,members.get(j));
                }
            }

        }

        //System.out.println(members);
        */
    }

    public static void main(String[] args) {
        MemberToSave member = new MemberToSave();
        MemberGenerator memberGenerator = new MemberGenerator();
        member.saveMemberDetailsToFile(memberGenerator.MemberGenerator());



    }
}