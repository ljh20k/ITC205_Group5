package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

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
        if (loan == null) {
            throw new IllegalArgumentException(String.format("Book: borrow : Bad parameter: loan cannot be null", new Object[0]));
        }
        if (this.state != EBookState.AVAILABLE) {
            throw new RuntimeException(String.format("Illegal operation in state : %s", new Object[]{this.state}));
        }
        this.loan = loan;
        this.state = EBookState.ON_LOAN;
    }

    @Override
    public ILoan getLoan() {
        return this.loan;
    }

    @Override
    public void returnBook(boolean damaged) {
        if (this.state != EBookState.ON_LOAN && this.state != EBookState.LOST) {
            throw new RuntimeException(String.format("Illegal operation in state : %s", new Object[]{this.state}));
        }
        this.loan = null;
        this.state = damaged ? EBookState.DAMAGED : EBookState.AVAILABLE;
    }

    @Override
    public void lose() {
        if (this.state != EBookState.ON_LOAN) {
            throw new RuntimeException(String.format("Illegal operation in state : %s", new Object[]{this.state}));
        }
        this.state = EBookState.LOST;
    }

    @Override
    public void repair() {
        if (this.state != EBookState.DAMAGED) {
            throw new RuntimeException(String.format("Illegal operation in state : %s", new Object[]{this.state}));
        }
        this.state = EBookState.AVAILABLE;
    }

    @Override
    public void dispose() {
        if (this.state != EBookState.AVAILABLE && this.state != EBookState.DAMAGED && this.state != EBookState.LOST) {
            throw new RuntimeException(String.format("Illegal operation in state : %s", new Object[]{this.state}));
        }
        this.state = EBookState.DISPOSED;
    }

    @Override
    public EBookState getState() {
        return this.state;
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

    //toString for printing out
    public String toString() {
        return String.format("Id: %d\nAuthor: %s\nTitle: %s\nCall Number %s", this.id, this.author, this.title, this.callNumber);
    }
}
