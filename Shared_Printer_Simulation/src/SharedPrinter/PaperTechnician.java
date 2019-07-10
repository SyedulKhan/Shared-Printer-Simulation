package SharedPrinter;

import java.util.concurrent.Semaphore;

public class PaperTechnician extends Thread {

    //private final Semaphore semaphore;
    private final ThreadGroup threadGroup;
    private LaserPrinter printer = new LaserPrinter("Printer1", 0, 0, 0);
    private final int seconds = 5000;

    public PaperTechnician(ThreadGroup threadGroup, LaserPrinter printer, String paperTechnicianName) {
        super(paperTechnicianName);
        this.threadGroup = threadGroup;
        this.printer = printer;
        //this.semaphore = semaphore;
    }

    @Override
    public void run() 
    {
        try {
            //semaphore.tryAcquire();
            for (int i = 0; i < 3; i++) {
                printer.refillPaperTray();                              
                sleep((int) (Math.random() * seconds));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + " has finished using the printer..." + "\n");
        //semaphore.release();

    }
}
