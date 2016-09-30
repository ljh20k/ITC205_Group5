package library.interfaces.entities;

import java.util.List;

public interface IMember {
	
	static final int   LOAN_LIMIT = 5;
	
	static final float FINE_LIMIT = 10.0f;

	boolean hasOverDueLoans();
	
	boolean hasReachedLoanLimit();
	
	boolean hasFinesPayable();
	
	boolean hasReachedFineLimit();
	
	float   getFineAmount();
	
	void    addFine(float fine);

	void    payFine(float payment);
	
	void    addLoan(ILoan loan);
	
	List<ILoan> getLoans();
	
	void    removeLoan(ILoan loan);
	
	EMemberState   getState();
	
	String  getFirstName();
	
	String  getLastName();
	
	String  getContactPhone();
	
	String  getEmailAddress();
	
	int     getID();
	

}
