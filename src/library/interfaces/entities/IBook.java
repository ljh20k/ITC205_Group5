package library.interfaces.entities;

public interface IBook {
	
	void borrow(ILoan loan);
	
	ILoan getLoan();
	
	void returnBook(boolean damaged);
	
	void lose();
	
	void repair();
	
	void dispose();
	
	EBookState getState();
	
	String getAuthor();
	
	String getTitle();
	
	String getCallNumber();
	
	int getID();

}
