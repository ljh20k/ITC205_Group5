package library.entities;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.Calendar;
import java.util.Date;

public class Loan implements ILoan {

    private int id;
    private Calendar dateBorrowed;
    private IBook book;
    private IMember borrower;
    private boolean overDue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Loan() {
    }

    @Override
    public void commit(int id) {

    }

    @Override
    public void complete() {

    }

    @Override
    public boolean isOverDue() {
        return false;
    }

    public void setOverDue(boolean overDue) {
        this.overDue = overDue;
    }

    @Override
    public boolean checkOverDue(Date currentDate) {
        return false;
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

    public void setID(int id) {
        this.id = id;
    }

    public Calendar getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Calendar dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public void setBorrower(IMember borrower) {
        this.borrower = borrower;
    }

    public void setBook(IBook book) {
        this.book = book;
    }
}
