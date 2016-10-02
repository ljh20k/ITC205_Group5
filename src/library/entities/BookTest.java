package library.entities;

import library.Main;
import library.daos.*;
import library.hardware.CardReader;
import library.hardware.Display;
import library.hardware.Printer;
import library.hardware.Scanner;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class BookTest {

    // setup
    private int id;
    private String author;
    private String title;
    private String callNumber;
    private EBookState state;
    private Book book;
    private ILoan loan;
    private Member member;
    private IBookDAO bookDAO;
    private ILoanDAO loanDAO;
    private IMemberDAO memberDAO;

    @Before
    public void setUp() throws Exception {
        memberDAO = new MemberDao(new MemberHelper());
        bookDAO = new BookDao(new BookHelper());
        loanDAO = new LoanDao(new LoanHelper());
        this.createMockData();
    }

    private void createMockData() {
        book = new Book("TEST", "BOOK", "TEST", 20);
        member = new Member("Ju Hun", "Lee", "045123698", "juhun@hotmail.com", 20);
        state = EBookState.AVAILABLE;

        this.loan = this.loanDAO.createLoan(member, book);
        this.loanDAO.commitLoan(loan);
    }

    @Test
    public void testBorrowNullException() throws Exception {
        //if(loan == null) { throw new RuntimeException(); }
        loan = null;

        try {
            this.book.borrow(loan);
        } catch(NullPointerException e) {
            assertThat(e, instanceOf(NullPointerException.class));
        }
    }

    @Test
    public void testBorrowArgumentException() throws Exception {
        //if(state != EBookState.Available) { throw new RuntimeException(); }
        state = EBookState.ON_LOAN;

        try {
            book.borrow(loan);
        } catch(RuntimeException e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void testGetLoan() throws Exception {
        String loan = book.getLoan().toString();
        System.out.println(book.getLoan().toString());
        book.setID(1);
        assertSame(loan,book.getLoan().toString());
        book.getLoan();
    }

    @Test
    public void testReturnBook() throws Exception {

    }

    @Test
    public void testLose() throws Exception {

    }

    @Test
    public void testRepair() throws Exception {

    }

    @Test
    public void testDispose() throws Exception {

    }

    @Test
    public void testGetState() throws Exception {

    }

    @Test
    public void testGetAuthor() throws Exception {

    }

    @Test
    public void testGetTitle() throws Exception {

    }

    @Test
    public void testGetCallNumber() throws Exception {

    }

    @Test
    public void testGetID() throws Exception {

    }

    @Test
    public void testSetID() throws Exception {

    }

    @Test
    public void testSetAuthor() throws Exception {

    }

    @Test
    public void testSetTitle() throws Exception {

    }

    @Test
    public void testSetCallNumber() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}