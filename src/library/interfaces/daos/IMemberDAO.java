package library.interfaces.daos;

import java.util.List;

import library.interfaces.entities.IMember;

public interface IMemberDAO {

		IMember addMember(String firstName, String lastName, String ContactPhone, String emailAddress);
		
		IMember getMemberByID(int id);
		
		List<IMember> listMembers();
		
		List<IMember> findMembersByLastName(String lastName);

		List<IMember> findMembersByEmailAddress(String emailAddress);
		
		List<IMember> findMembersByNames(String firstName, String lastName);

}
