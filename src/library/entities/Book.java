package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook{

    private int id;
    private String author;
    private String title;
    private String callNumber;
    private ILoan loan;
    private EBookState state;

    public Book(String author, String title, String callNumber, int bookID) {
        if (!this.sane(author, title, callNumber, bookID)) {
            throw new IllegalArgumentException("Member: constructor : bad parameters");
        }
        this.author = author;
        this.title = title;
        this.callNumber = callNumber;
        this.id = bookID;
        this.state = EBookState.AVAILABLE;
        this.loan = null;
    }

    private boolean sane(String author, String title, String callNumber, int bookID) {
        if (!(author == null || author.isEmpty() || title == null || title.isEmpty() || callNumber == null || callNumber.isEmpty() || bookID <= 0)) {
            return true;
        }
        return false;
    }

    @Override
    public void borrow(ILoan loan) {
    }

    @Override
    public ILoan getLoan() {
        return null;
    }

    @Override
    public void returnBook(boolean damaged) {
    }

    @Override
    public void lose() {

    }

    @Override
    public void repair() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public EBookState getState() {
        return null;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCallNumber() {
        return callNumber;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
