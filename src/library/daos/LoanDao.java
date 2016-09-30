package library.daos;

import library.entities.Loan;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.*;

public class LoanDao implements ILoanDAO {

    private int nextID;
    private Map<Integer, ILoan> loanMap;
    private ILoanHelper helper;
    private Calendar cal;
//    private List<ILoan> loanTableInDb = new ArrayList<>();

    public LoanDao(ILoanHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : constructor : helper cannot be null.", new Object[0]));
        }
        this.nextID = 0;
        this.helper = helper;
        this.loanMap = new HashMap<Integer, ILoan>();
        this.cal = Calendar.getInstance();
    }

    public LoanDao(ILoanHelper helper, Map<Integer, ILoan> loanMap) {
        this(helper);
        if (loanMap == null) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : constructor : loanMap cannot be null.", new Object[0]));
        }
        this.loanMap = loanMap;
    }

    private int getNextId() {
        return ++this.nextID;
    }

    @Override
    public ILoan createLoan(IMember borrower, IBook book) {
        if (borrower == null || book == null) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : createLoan : borrower and book cannot be null.", new Object[0]));
        }
        Date borrowDate = new Date();
        this.cal.setTime(borrowDate);
        this.cal.add(5, 14);
        Date dueDate = this.cal.getTime();
        ILoan loan = this.helper.makeLoan(book, borrower, borrowDate, dueDate);
        return loan;
//        Loan loan = new Loan();
//        loan.setBorrower(borrower);
//        loan.setBook(book);
//        loan.setDateBorrowed(Calendar.getInstance());
//        return loan;
    }

    @Override
    public void commitLoan(ILoan loan) {
        int id = this.getNextId();
        loan.commit(id);
        this.loanMap.put(id, loan);
//        loanTableInDb.add(loan);
//        System.out.println(loan.getID() + " " + loan.getBook().getTitle() + " " + loan.getBorrower().getFirstName());
    }

    @Override
    public ILoan getLoanByID(int id) {
        if (this.loanMap.containsKey(id)) {
            return this.loanMap.get(id);
        }
        return null;
    }

    @Override
    public ILoan getLoanByBook(IBook book) {
        if (book == null) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : getLoanByBook : book cannot be null.", new Object[0]));
        }
        for (ILoan loan : this.loanMap.values()) {
            IBook tempBook = loan.getBook();
            if (!book.equals(tempBook)) continue;
            return loan;
        }
        return null;
    }

    @Override
    public List<ILoan> listLoans() {
        ArrayList<ILoan> list = new ArrayList<ILoan>(this.loanMap.values());
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<ILoan> findLoansByBorrower(IMember borrower) {
        if (borrower == null) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : findLoansByBorrower : borrower cannot be null.", new Object[0]));
        }
        ArrayList<ILoan> list = new ArrayList<ILoan>();
        for (ILoan loan : this.loanMap.values()) {
            if (!borrower.equals(loan.getBorrower())) continue;
            list.add(loan);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<ILoan> findLoansByBookTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException(String.format("LoanMapDAO : findLoansByBookTitle : title cannot be null or blank.", new Object[0]));
        }
        ArrayList<ILoan> list = new ArrayList<ILoan>();
        for (ILoan loan : this.loanMap.values()) {
            String tempTitle = loan.getBook().getTitle();
            if (!title.equals(tempTitle)) continue;
            list.add(loan);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public void updateOverDueStatus(Date currentDate) {
        for (ILoan loan : this.loanMap.values()) {
            loan.checkOverDue(currentDate);
        }

//        --- Old Version --
//        for (ILoan iLoan : loanTableInDb) {
//            Calendar dateBorrowed = ((Loan) iLoan).getDateBorrowed();
//            dateBorrowed.add(Calendar.DATE,ILoan.LOAN_PERIOD);
//            Date dateBorrowedInDate = new Date(dateBorrowed.getTimeInMillis());
//            if(currentDate.after(dateBorrowedInDate)) {
//                ((Loan)iLoan).setOverDue(true);
//                System.out.println(iLoan.getBorrower().getFirstName() + " is a late mother fucker");
//            }
//        }
    }

    @Override
    public List<ILoan> findOverDueLoans() {
        ArrayList<ILoan> list = new ArrayList<ILoan>();
        for (ILoan loan : this.loanMap.values()) {
            if (!loan.isOverDue()) continue;
            list.add(loan);
        }
        return Collections.unmodifiableList(list);
    }
}
