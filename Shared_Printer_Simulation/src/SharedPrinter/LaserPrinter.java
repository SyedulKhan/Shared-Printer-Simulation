package SharedPrinter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LaserPrinter implements ServicePrinter {

    private String printerID;
    private int paperLevel;
    private int tonerLevel;
    private int numOfDocumentsPrinted;
    private int numOfDocsToBePrinted;
    private int docsDone = 0;

    public LaserPrinter(String printerID, int paperLevel, int tonerLevel, int numOfDocumentsPrinted) {
        this.printerID = printerID;
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.numOfDocumentsPrinted = numOfDocumentsPrinted;
    }

    @Override
    public synchronized void printDocument(Document document) {

        numOfDocsToBePrinted = document.getNumOfPages();

        while (lowToner() || lowPaper()) {
            try {
                System.out.println(document.getUserID() + " is waiting");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(LaserPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        setPaperLevel(getPaperLevel() - numOfDocsToBePrinted);
        setTonerLevel(getTonerLevel() - numOfDocsToBePrinted);
        setNumOfDocumentsPrinted(getNumOfDocumentsPrinted() + numOfDocsToBePrinted);
        System.out.println(document.toString() + " : " + " is printed" + "\n");
        System.out.println(toString() + "\n");
        docsDone = docsDone + 1;
        notifyAll();

    }

    @Override
    public synchronized void replaceTonerCartridge() {
        while (!lowToner()) {
            try {
                if (docsDone == 20) {
                    numOfDocsToBePrinted = 500;
                } else {
                    wait(5000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LaserPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (docsDone < 20) {
            setTonerLevel(FULL_TONER_LEVEL);
            System.out.println("Toner Technician refilled cartridge" + "\n");
            System.out.println(toString() + "\n");
        }
        notifyAll();
    }

    @Override
    public synchronized void refillPaperTray() {
        while (!lowPaper()) {
            try {
                wait(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LaserPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setPaperLevel(FULL_PAPER_TRAY);
        System.out.println("Paper Technician refilled tray" + "\n");
        System.out.println(toString() + "\n");
        notifyAll();
    }

    public Boolean lowToner() {
        return getTonerLevel() - numOfDocsToBePrinted < MIN_TONER_LEVEL;
        //return getTonerLevel() < MIN_TONER_LEVEL;
    }

    public synchronized Boolean lowPaper() {
        return getPaperLevel() - numOfDocsToBePrinted < 0;
        //return getPaperLevel() < 200;
    }

    public synchronized String getPrinterID() {
        return printerID;
    }

    public synchronized void setPrinterID(String printerID) {
        this.printerID = printerID;
    }

    public synchronized int getPaperLevel() {
        return paperLevel;
    }

    public synchronized void setPaperLevel(int paperLevel) {
        this.paperLevel = paperLevel;
    }

    public synchronized int getTonerLevel() {
        return tonerLevel;
    }

    public synchronized void setTonerLevel(int tonerLevel) {
        this.tonerLevel = tonerLevel;
    }

    public synchronized int getNumOfDocumentsPrinted() {
        return numOfDocumentsPrinted;
    }

    public synchronized void setNumOfDocumentsPrinted(int numOfDocumentsPrinted) {
        this.numOfDocumentsPrinted = numOfDocumentsPrinted;
    }

    @Override
    public synchronized String toString() {
        return "[ "
                + "PrinterID: " + printerID + ", "
                + "Paper Level: " + getPaperLevel() + ", "
                + "Toner Level: " + getTonerLevel() + ", "
                + "Documents Printed: " + getNumOfDocumentsPrinted()
                + " ]";
    }

}
