package library.daos;

import library.entities.Loan;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanDao implements ILoanDAO {

    private List<ILoan> loanTableInDb = new ArrayList<>();


    @Override
    public ILoan createLoan(IMember borrower, IBook book) {
        Loan loan = new Loan();
        loan.setBorrower(borrower);
        loan.setBook(book);
        loan.setDateBorrowed(Calendar.getInstance());
        return loan;
    }

    @Override
    public void commitLoan(ILoan loan) {
        loanTableInDb.add(loan);
        System.out.println(loan.getID() + " " + loan.getBook().getTitle() + " " + loan.getBorrower().getFirstName());
    }

    @Override
    public ILoan getLoanByID(int id) {
        return null;
    }

    @Override
    public ILoan getLoanByBook(IBook book) {
        return null;
    }

    @Override
    public List<ILoan> listLoans() {
        return null;
    }

    @Override
    public List<ILoan> findLoansByBorrower(IMember borrower) {
        return null;
    }

    @Override
    public List<ILoan> findLoansByBookTitle(String title) {
        return null;
    }

    @Override
    public void updateOverDueStatus(Date currentDate) {

        for (ILoan iLoan : loanTableInDb) {
            Calendar dateBorrowed = ((Loan) iLoan).getDateBorrowed();
            dateBorrowed.add(Calendar.DATE,ILoan.LOAN_PERIOD);
            Date dateBorrowedInDate = new Date(dateBorrowed.getTimeInMillis());
            if(currentDate.after(dateBorrowedInDate)) {
                ((Loan)iLoan).setOverDue(true);
                System.out.println(iLoan.getBorrower().getFirstName() + " is a late mother fucker");
            }
        }
    }

    @Override
    public List<ILoan> findOverDueLoans() {
        return null;
    }
}
