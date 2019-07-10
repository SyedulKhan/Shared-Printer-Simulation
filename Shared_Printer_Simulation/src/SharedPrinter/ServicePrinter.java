/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedPrinter;

/**
 *
 * @author Syedu
 */
public interface ServicePrinter extends Printer {
    
    public final int FULL_PAPER_TRAY = 250;
    public final int FULL_TONER_LEVEL = 500;
    public final int MIN_TONER_LEVEL = 10;
    public final int SHEETS_PER_PACK = 50;
    public final int PAGES_PER_TONER_CARTRIDGE = 500;
    
    @Override
    public void printDocument(Document document);
    public void replaceTonerCartridge();
    public void refillPaperTray();
     
}
