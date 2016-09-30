package library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.IBorrowUIListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.ICardReaderListener;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.interfaces.hardware.IScannerListener;

public class BorrowUC_CTL implements ICardReaderListener, 
									 IScannerListener, 
									 IBorrowUIListener {
	
	private ICardReader reader;
	private IScanner scanner; 
	private IPrinter printer; 
	private IDisplay display;
	//private String state;
	private int scanCount = 0;
	private IBorrowUI ui;
	private EBorrowState state; 
	private IBookDAO bookDAO;
	private IMemberDAO memberDAO;
	private ILoanDAO loanDAO;
	
	private List<IBook> bookList;
	private List<ILoan> loanList;
	private IMember borrower;
	
	private JPanel previous;

	public BorrowUC_CTL(ICardReader reader, IScanner scanner, 
			IPrinter printer, IDisplay display,
			IBookDAO bookDAO, ILoanDAO loanDAO, IMemberDAO memberDAO ) {
		this.ui = new BorrowUC_UI(this);
		this.reader = reader;
//		Add reader listener
		reader.addListener(this);
		this.scanner = scanner;
//		Add scanner listener
		scanner.addListener(this);
		this.printer = printer;
		this.display = display;
		this.bookDAO = bookDAO;
		this.memberDAO = memberDAO;
		state = EBorrowState.CREATED;
	}
	
	public void initialise() {
		previous = display.getDisplay();
		display.setDisplay((JPanel) ui, "Borrow UI");		
	}
	
	public void close() {
		display.setDisplay(previous, "Main Menu");
	}

	@Override
	public void cardSwiped(int memberID) {
		IMember member = memberDAO.getMemberByID(memberID);
		if (member != null) {
			scanner.setEnabled(true);
		}
	}
	
	@Override
	public void bookScanned(int barcode) {
		IBook tempBook = bookDAO.getBookByID(barcode);
		ui.displayErrorMessage("");
		if (tempBook == null) {
			ui.displayErrorMessage(barcode + " is not found");
		}
		else if (state != EBorrowState.BORROWING_RESTRICTED.SCANNING_BOOKS) {
			throw new RuntimeException("Error operation in state");
		}
		else if (tempBook.getState() != EBookState.AVAILABLE) {
			ui.displayErrorMessage(String.format("Book is not available: ",new Object[]{tempBook.getID(), tempBook.getState()}));
		}
		else if (bookList.contains(tempBook)) {
			ui.displayErrorMessage("book " + tempBook.getID() + " is already scanned");
		}
		else {
			bookList.add(tempBook);
			ILoan loan = loanDAO.createLoan(borrower, tempBook);
			loanList.add(loan);
			ui.displayScannedBookDetails(tempBook.toString());
			ui.displayPendingLoan(buildLoanListDisplay(loanList));
		}

		scanCount = scanCount + 1;
		if (scanCount >= 5) {
				setState(EBorrowState.CONFIRMING_LOANS);
		}
	}


	private void setState(EBorrowState state) {

		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void cancelled() {
		setState(EBorrowState.CANCELLED);
	}
	
	@Override
	public void scansCompleted() {
		setState(EBorrowState.CONFIRMING_LOANS);
	}

	@Override
	public void loansConfirmed() {
		setState(EBorrowState.COMPLETED);
	}

	@Override
	public void loansRejected() {
		System.out.println("loansRejected");
		setState(EBorrowState.SCANNING_BOOKS);
	}

	private String buildLoanListDisplay(List<ILoan> loans) {
		StringBuilder bld = new StringBuilder();
		for (ILoan ln : loans) {
			if (bld.length() > 0) bld.append("\n\n");
			bld.append(ln.toString());
		}
		return bld.toString();		
	}

}
