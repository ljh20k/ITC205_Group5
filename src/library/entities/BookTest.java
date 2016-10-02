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
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class BookTest {

    // setup
    private int id;
    private String author;
    private String title;
    private String callNumber;
    private ILoan loan;
    private EBookState state;

    @Test
    public void testBorrow() throws Exception {

    }

    @Test
    public void testGetLoan() throws Exception {

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