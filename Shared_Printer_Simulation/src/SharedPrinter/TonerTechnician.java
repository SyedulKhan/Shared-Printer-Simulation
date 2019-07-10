package SharedPrinter;

import java.util.concurrent.Semaphore;

public class TonerTechnician extends Thread {

    //private final Semaphore semaphore;
    private final ThreadGroup threadGroup;
    private LaserPrinter printer = new LaserPrinter("Printer1", 0, 0, 0);
    private final int seconds = 5000;

    public TonerTechnician(ThreadGroup threadGroup, LaserPrinter printer, String tonerTechnicianName) {
        super(tonerTechnicianName);
        this.threadGroup = threadGroup;
        this.printer = printer;
        //this.semaphore = semaphore;
    }

    @Override
    public void run() // ‘‘body’’ of the thread
    {   
        try {
            //semaphore.tryAcquire();
            for (int i = 0; i < 3; i++) {
                printer.replaceTonerCartridge();
                sleep((int) (Math.random() * seconds));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } 
        System.out.println(getName() + " has finished using the printer..." + "\n");
        //semaphore.release();

    }

}
