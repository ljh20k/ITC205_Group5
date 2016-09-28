package library.daos;

import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.entities.IBook;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDAO {

    private List<Book> bookTableInDb = new ArrayList<>();

    @Override
    public IBook addBook(String author, String title, String callNo) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setCallNumber(callNo);
        book.setID(bookTableInDb.size());
        bookTableInDb.add(book);
        System.out.println(book.getID() + " " + book.getAuthor() + " fuck off");
        return book;
    }

    @Override
    public IBook getBookByID(int id) {
        return null;
    }

    @Override
    public List<IBook> listBooks() {
        return null;
    }

    @Override
    public List<IBook> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<IBook> findBooksByTitle(String title) {
        return null;
    }

    @Override
    public List<IBook> findBooksByAuthorTitle(String author, String title) {
        return null;
    }
}
