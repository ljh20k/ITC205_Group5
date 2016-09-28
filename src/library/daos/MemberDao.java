package library.daos;

import library.entities.Member;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IMember;

import java.util.ArrayList;
import java.util.List;

public class MemberDao implements IMemberDAO {

    private List<Member> memberTableInDb = new ArrayList<>();

    @Override
    public IMember addMember(String firstName, String lastName, String contactPhone, String emailAddress) {
        Member member = new Member();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setContactPhone(contactPhone);
        member.setEmailAddress(emailAddress);
        member.setID(memberTableInDb.size());
        memberTableInDb.add(member);
        System.out.println(member.getID() + " " + member.getFirstName() + " added");
        return member;
    }

    @Override
    public IMember getMemberByID(int id) {
        for (Member member : memberTableInDb) {
            if(member.getID() == id) {
                System.out.println(member.getFirstName() + " " + member.getLastName() + " logged in !");
                return member;
            }
        }
        return null;
    }

    @Override
    public List<IMember> listMembers() {
        return null;
    }

    @Override
    public List<IMember> findMembersByLastName(String lastName) {
        return null;
    }

    @Override
    public List<IMember> findMembersByEmailAddress(String emailAddress) {
        return null;
    }

    @Override
    public List<IMember> findMembersByNames(String firstName, String lastName) {
        return null;
    }
}
