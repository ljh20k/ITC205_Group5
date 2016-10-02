package library.entities;

import library.daos.*;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.ILoan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamal on 2/10/2016.
 */
public class MemberTest {
   private String fName = "FName";
   private String lName = "lName";
    private String phone = "phone";
    private String email = "email";
    private int id = 1;
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
            member = new Member("kamal", "kandel", "045123698", "kamal_kandel62@hotmail.com", 20);
            state = EBookState.AVAILABLE;

            this.loan = this.loanDAO.createLoan(member, book);
            this.loanDAO.commitLoan(loan);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHasOverDueLoans() throws Exception {

    }

    @Test
    public void testHasReachedLoanLimit() throws Exception {


    }

    @Test
    public void testHasFinesPayable() throws Exception {

    }

    @Test
    public void testHasReachedFineLimit() throws Exception {

    }

    @Test
    public void testGetFineAmount() throws Exception {

    }

    @Test
    public void testAddFine() throws Exception {

    }

    @Test
    public void testPayFine() throws Exception {

    }

    @Test
    public void testAddLoan() throws Exception {

    }

    @Test
    public void testGetLoans() throws Exception {

    }

    @Test
    public void testRemoveLoan() throws Exception {

    }

    @Test
    public void testGetState() throws Exception {

    }

    @Test
    public void testGetFirstName() throws Exception {
        String actual = member.getFirstName();
        assertEquals(fName, actual);
    }

    @Test
    public void testSetFirstName() throws Exception {

    }

    @Test
    public void testGetLastName() throws Exception {
        String actual = member.getLastName();
        assertEquals(lName, actual);


    }

    @Test
    public void testSetLastName() throws Exception {

    }

    @Test
    public void testGetContactPhone() throws Exception {
        String actual = member.getContactPhone();
        assertEquals(phone, actual);

    }

    @Test
    public void testSetContactPhone() throws Exception {

    }

    @Test
    public void testGetEmailAddress() throws Exception {
         String actual = member.getEmailAddress();
        assertEquals(email, actual);

    }

    @Test
    public void testSetEmailAddress() throws Exception {

    }

    @Test
    public void testGetID() throws Exception {
        int actual = member.getID();
        assertEquals(id, actual);

    }

    @Test
    public void testSetID() throws Exception {

    }

}