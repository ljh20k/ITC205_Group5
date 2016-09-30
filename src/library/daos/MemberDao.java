package library.daos;

import library.entities.Member;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import java.util.*;

public class MemberDao implements IMemberDAO {
    private IMemberHelper helper;
    private Map<Integer, IMember> memberMap;
    private int nextID;
//    private List<Member> memberTableInDb = new ArrayList<>();


    public MemberDao(IMemberHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException(String.format("MemberMapDAO : constructor : helper cannot be null.", new Object[0]));
        }
        this.helper = helper;
        this.memberMap = new HashMap<Integer, IMember>();
        this.nextID = 1;
    }

    public MemberDao(IMemberHelper helper, Map<Integer, IMember> memberMap) {
        this(helper);
        if (memberMap == null) {
            throw new IllegalArgumentException(String.format("MemberMapDAO : constructor : memberMap cannot be null.", new Object[0]));
        }
        this.memberMap = memberMap;
    }

    private int getNextId() {
        return this.nextID++;
    }

    @Override
    public IMember addMember(String firstName, String lastName, String contactPhone, String emailAddress) {
        int id = this.getNextId();
        IMember mem = this.helper.makeMember(firstName, lastName, contactPhone, emailAddress, id);
        this.memberMap.put(id, mem);
        return mem;

//        --- Old Version ---
//        Member member = new Member();
//        member.setFirstName(firstName);
//        member.setLastName(lastName);
//        member.setContactPhone(contactPhone);
//        member.setEmailAddress(emailAddress);
//        member.setID(memberTableInDb.size());
//        memberTableInDb.add(member);
//        System.out.println(member.getID() + " " + member.getFirstName() + " added");
//        return member;
    }

    @Override
    public IMember getMemberByID(int id) {
        if (this.memberMap.keySet().contains(id)) {
            return this.memberMap.get(id);
        }
        return null;

//        --- Old Version ---
//        for (Member member : memberTableInDb) {
//            if(member.getID() == id) {
//                System.out.println(member.getFirstName() + " " + member.getLastName() + " logged in !");
//                return member;
//            }
//        }
//        return null;
    }

    @Override
    public List<IMember> listMembers() {
        ArrayList<IMember> list = new ArrayList<IMember>(this.memberMap.values());
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<IMember> findMembersByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(String.format("MemberMapDAO : findMembersByLastName : lastName cannot be null or blank", new Object[0]));
        }
        ArrayList<IMember> list = new ArrayList<IMember>();
        for (IMember m : this.memberMap.values()) {
            if (!lastName.equals(m.getLastName())) continue;
            list.add(m);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<IMember> findMembersByEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException(String.format("MemberMapDAO : findMembersByEmailAddress : emailAddress cannot be null or blank", new Object[0]));
        }
        ArrayList<IMember> list = new ArrayList<IMember>();
        for (IMember m : this.memberMap.values()) {
            if (!emailAddress.equals(m.getEmailAddress())) continue;
            list.add(m);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<IMember> findMembersByNames(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(String.format("MemberMapDAO : findMembersByNames : firstName and lastName cannot be null or blank", new Object[0]));
        }
        ArrayList<IMember> list = new ArrayList<IMember>();
        for (IMember m : this.memberMap.values()) {
            if (!firstName.equals(m.getFirstName()) || !lastName.equals(m.getLastName())) continue;
            list.add(m);
        }
        return Collections.unmodifiableList(list);
    }
}
