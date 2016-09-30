package library.entities;

import library.interfaces.entities.ELoanState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan implements ILoan {

    private int id;
    private IBook book;
    private IMember borrower;
    private Date borrowDate;
    private Date dueDate;
    private ELoanState state;
//    private boolean overDue;
//    private Calendar dateBorrowed;


    public Loan(IBook book, IMember borrower, Date borrowDate, Date returnDate) {
        if (!this.sane(book, borrower, borrowDate, returnDate)) {
            throw new IllegalArgumentException("Loan: constructor : bad parameters");
        }
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.dueDate = returnDate;
        this.state = ELoanState.PENDING;
    }

    private boolean sane(IBook book, IMember borrower, Date borrowDate, Date returnDate) {
        if (book != null && borrower != null && borrowDate != null && returnDate != null && borrowDate.compareTo(returnDate) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void commit(int loanId) {
        if (this.state != ELoanState.PENDING) {
            throw new RuntimeException(String.format("Loan : commit : incorrect state transition  : %s -> %s\n", new Object[]{this.state, ELoanState.CURRENT}));
        }
        if (loanId <= 0) {
            throw new RuntimeException(String.format("Loan : commit : id must be a positive integer  : %d\n", loanId));
        }
        this.id = loanId;
        this.state = ELoanState.CURRENT;
        this.book.borrow(this);
        this.borrower.addLoan(this);
    }

    @Override
    public void complete() {
        if (this.state != ELoanState.CURRENT && this.state != ELoanState.OVERDUE) {
            throw new RuntimeException(String.format("Loan : complete : incorrect state transition  : %s -> %s\n", new Object[]{this.state, ELoanState.COMPLETE}));
        }
        this.state = ELoanState.COMPLETE;
    }

    @Override
    public boolean isOverDue() {
        if (this.state == ELoanState.OVERDUE) {
            return true;
        }
        return false;
    }

//    public void setOverDue(boolean overDue) {
//        this.overDue = overDue;
//    }

    @Override
    public boolean checkOverDue(Date currentDate) {
        if (this.state != ELoanState.CURRENT && this.state != ELoanState.OVERDUE) {
            throw new RuntimeException(String.format("Loan : checkOverDue : incorrect state transition  : %s -> %s\n", new Object[]{this.state, ELoanState.OVERDUE}));
        }
        if (currentDate.compareTo(this.dueDate) > 0) {
            this.state = ELoanState.OVERDUE;
        }
        return this.isOverDue();
    }

    @Override
    public IMember getBorrower() {
        return borrower;
    }

    @Override
    public IBook getBook() {
        return book;
    }

    @Override
    public int getID() {
        return id;
    }

    public ELoanState getState() {
        return this.state;
    }

//    public void setID(int id) {
//        this.id = id;
//    }

//    public Calendar getDateBorrowed() {
//        return dateBorrowed;
//    }
//
//    public void setDateBorrowed(Calendar dateBorrowed) {
//        this.dateBorrowed = dateBorrowed;
//    }

    public void setBorrower(IMember borrower) {
        this.borrower = borrower;
    }

    public void setBook(IBook book) {
        this.book = book;
    }

    public String toString() {
        return String.format("Loan ID:  %d\nAuthor:   %s\nTitle:    %s\nBorrower: %s %s\nBorrowed: %s\nDue Date: %s", this.id, this.book.getAuthor(), this.book.getTitle(), this.borrower.getFirstName(), this.borrower.getLastName(), DateFormat.getDateInstance().format(this.borrowDate), DateFormat.getDateInstance().format(this.dueDate));
    }

}
