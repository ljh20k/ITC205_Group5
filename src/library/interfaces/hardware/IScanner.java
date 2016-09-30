package library.interfaces.hardware;

public interface IScanner {

    void addListener(IScannerListener listener);

    void setEnabled(boolean enabled);

}
