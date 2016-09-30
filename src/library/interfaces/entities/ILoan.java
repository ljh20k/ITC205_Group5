package library.interfaces.entities;

import java.util.Date;

public interface ILoan {

    static final int LOAN_PERIOD = 14;

    void commit(int id);

    void complete();

    boolean isOverDue();

    boolean checkOverDue(Date currentDate);

    IMember getBorrower();

    IBook getBook();

    int getID();


}
