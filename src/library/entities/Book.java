package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook{

    private int id;
    private String author;
    private String title;
    private String callNumber;

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
