import database.LoadMember;
import database.MemberToSave;
import database.ReadFiles;
import factory.MemberGenerator;
import persons.Member;

public class DelfinenApp {
    static MemberToSave memberToSave = new MemberToSave();



    public static void main(String[] args) {
        new LoadMember();
        System.out.println();
        new ReadFiles("resources/members.csv");
        new ReadFiles("resources/memberIdCounter.csv");
        MemberGenerator memberGenerator = new MemberGenerator();
        memberToSave.saveMemberDetailsToFile(memberGenerator.MemberGenerator());

    }
}
