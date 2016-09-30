package library.interfaces.daos;

import java.util.Date;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public interface ILoanHelper {
	
	ILoan makeLoan(IBook book, IMember borrower, Date borrowDate, Date dueDate);

}
