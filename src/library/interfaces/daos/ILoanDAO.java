package library.interfaces.daos;

import java.util.Date;
import java.util.List;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public interface ILoanDAO {

    ILoan createLoan(IMember borrower, IBook book);

    void commitLoan(ILoan loan);

    ILoan getLoanByID(int id);

    ILoan getLoanByBook(IBook book);

    List<ILoan> listLoans();

    List<ILoan> findLoansByBorrower(IMember borrower);

    List<ILoan> findLoansByBookTitle(String title);

    void updateOverDueStatus(Date currentDate);

    List<ILoan> findOverDueLoans();

}

