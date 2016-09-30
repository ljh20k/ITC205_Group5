package library.interfaces.daos;

import library.interfaces.entities.IMember;

public interface IMemberHelper {
	
	IMember makeMember(String firstName, String lastName, String contactPhone, String emailAddress, int id);

}
