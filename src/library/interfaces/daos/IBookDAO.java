package library.interfaces.daos;

import java.util.List;

import library.interfaces.entities.IBook;

public interface IBookDAO {

    IBook addBook(String author, String title, String callNo);

    IBook getBookByID(int id);

    List<IBook> listBooks();

    List<IBook> findBooksByAuthor(String author);

    List<IBook> findBooksByTitle(String title);

    List<IBook> findBooksByAuthorTitle(String author, String title);

}
