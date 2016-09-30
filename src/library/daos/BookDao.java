package library.daos;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao implements IBookDAO {
    private int nextId;
    private Map<Integer, IBook> bookMap;
    private IBookHelper helper;
//    private List<Book> bookTableInDb = new ArrayList<>();

    public BookDao(IBookHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException(String.format("BookDAO : constructor : helper cannot be null.", new Object[0]));
        }
        this.nextId = 1;
        this.helper = helper;
        this.bookMap = new HashMap<Integer, IBook>();
    }

    public BookDao(IBookHelper helper, Map<Integer, IBook> bookMap) {
        this(helper);
        if (helper == null) {
            throw new IllegalArgumentException(String.format("BookDAO : constructor : bookMap cannot be null.", new Object[0]));
        }
        this.bookMap = bookMap;
    }

    @Override
    public IBook addBook(String author, String title, String callNo) {
        int id = this.getNextId();
        IBook book = this.helper.makeBook(author, title, callNo, id);
        this.bookMap.put(id, book);
        return book;
    }

//    @Override
//    public IBook addBook(String author, String title, String callNo) {
//        Book book = new Book();
//        book.setAuthor(author);
//        book.setTitle(title);
//        book.setCallNumber(callNo);
//        book.setID(bookTableInDb.size());
//        bookTableInDb.add(book);
//        System.out.println(book.getID() + " " + book.getAuthor() + " fuck off");
//        return book;
//    }

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

    private int getNextId() {
        return this.nextId++;
    }

}
