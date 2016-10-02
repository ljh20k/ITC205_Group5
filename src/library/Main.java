package library;

import library.daos.*;
import library.hardware.CardReader;
import library.hardware.Display;
import library.hardware.Printer;
import library.hardware.Scanner;

import java.util.Calendar;
import java.util.Date;

import library.interfaces.IMainListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.panels.MainPanel;

public class Main implements IMainListener {

    private CardReader reader;
    private Scanner scanner;
    private Printer printer;
    private Display display;
    private IBookDAO bookDAO;
    private ILoanDAO loanDAO;
    private IMemberDAO memberDAO;

    public Main() {
        reader = new CardReader();
        scanner = new Scanner();
        printer = new Printer();
        display = new Display();
        memberDAO = new MemberDao(new MemberHelper());
        bookDAO = new BookDao(new BookHelper());
        loanDAO = new LoanDao(new LoanHelper());
        setupTestData();
    }

    public void showGUI() {
        reader.setVisible(true);
        scanner.setVisible(true);
        printer.setVisible(true);
        display.setVisible(true);
    }

    @Override
    public void borrowBooks() {
        BorrowUC_CTL ctl = new BorrowUC_CTL(this.reader, this.scanner, this.printer, this.display,
                this.bookDAO, this.loanDAO, this.memberDAO);
        reader.setEnabled(true);
        reader.addListener(ctl);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ctl.initialise();
            }
        });
    }

    private void setupTestData() {
        IBook[] book = new IBook[15];
        IMember[] member = new IMember[6];

        book[0] = bookDAO.addBook("Hermann Hesse", "Demian", "D3E5M2");
        book[1] = bookDAO.addBook("Hermann Hesse", "Narcissus and Goldmund", "N5A1R5");
        book[2] = bookDAO.addBook("Hermann Hesse", "Beneath the Wheel", "B6E7N9");
        book[3] = bookDAO.addBook("Hermann Hesse", "Knulp: Three Tales From the Life of Knulp", "K4N4U2");
        book[4] = bookDAO.addBook("Bernard Werber", "Empire of the Ants", "E2M5P2");
        book[5] = bookDAO.addBook("Bernard Werber", "L'Empire des anges", "L2E5P1");
        book[6] = bookDAO.addBook("Bernard Werber", "Nous les dieux", "N7O7U2");
        book[7] = bookDAO.addBook("Bernard Werber", "La Trilogie des Fourmis ", "L2A8T5");
        book[8] = bookDAO.addBook("Henrik Ibsen", "The Feast at Solhaug", "T1H6E3");
        book[9] = bookDAO.addBook("Henrik Ibsen", "Peer Gynt", "P2E3E2");
        book[10] = bookDAO.addBook("Henrik Ibsen", "Catiline", "C6A2T6");
        book[11] = bookDAO.addBook("Henrik Ibsen", "Little Eyolf", "L9I3T2");
        book[12] = bookDAO.addBook("Plato", "Trial Of Socrates", "T7R9I8");
        book[13] = bookDAO.addBook("Plato", "Philosophy Of Plato", "P5H1I8");
        book[14] = bookDAO.addBook("Plato", "La Republique De Platon", "L2A6R7");

        member[0] = memberDAO.addMember("Ju Hun", "Lee", "045123698", "juhun@hotmail.com");
        member[1] = memberDAO.addMember("Kamal", "Raj Kandel", "0145782694", "kamal@gmail.com");
        member[2] = memberDAO.addMember("Amar", "Adhikari", "0985632168", "amar@gmail.com");
        member[3] = memberDAO.addMember("Matthew", "Arts", "0551479832", "matthew@naver.com");
        member[4] = memberDAO.addMember("Kate", "Svechnikova", "0695121020", "kate@hotmail.com");
        member[5] = memberDAO.addMember("Akiko", "Shimomura", "0897545152", "akiko@gmail.com");

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        //create a member with overdue loans
        for (int i = 0; i < 2; i++) {
            ILoan loan = this.loanDAO.createLoan(member[1], book[i]);
            this.loanDAO.commitLoan(loan);
        }
        cal.setTime(now);
        cal.add(Calendar.DATE, ILoan.LOAN_PERIOD + 1);

        Date checkDate = cal.getTime();
        loanDAO.updateOverDueStatus(checkDate);

        //create a member with maxed out unpaid fines
        member[2].addFine(10.0f);

        //create a member with maxed out loans
        for (int i = 2; i < 7; i++) {
            ILoan loan = loanDAO.createLoan(member[3], book[i]);
            loanDAO.commitLoan(loan);
        }

        //a member with a fine, but not over the limit
        member[4].addFine(5.0f);

        //a member with a couple of loans but not over the limit
        for (int i = 7; i < 9; i++) {
            ILoan loan = loanDAO.createLoan(member[5], book[i]);
            loanDAO.commitLoan(loan);
        }
    }


    public static void main(String[] args) {

        // start the GUI
        Main main = new Main();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main.display.setDisplay(new MainPanel(main), "Main Menu");
                main.showGUI();
            }
        });
    }


}
