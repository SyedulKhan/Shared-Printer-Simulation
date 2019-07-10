package SharedPrinter;

import java.util.concurrent.Semaphore;

public class Student extends Thread {

    private final ThreadGroup threadGroup;
    private LaserPrinter printer = new LaserPrinter("Printer1", 0, 0, 0);
    private final int seconds = 2000;
    //private final Semaphore semaphore;
    private final Document[] documents = new Document[5];

    public Student(ThreadGroup threadGroup, LaserPrinter printer, String studentName) {
        super(studentName);
        this.threadGroup = threadGroup;
        this.printer = printer;
        //this.semaphore = semaphore;

        documents[0] = new Document(getName(), "CW1", 10);
        documents[1] = new Document(getName(), "CW2", 50);
        documents[2] = new Document(getName(), "CW3", 40);
        documents[3] = new Document(getName(), "CW4", 100);
        documents[4] = new Document(getName(), "CW5", 20);
    }

    @Override
    public void run() {
        try {
            //semaphore.acquire();
            System.out.println(getName() + " is using the printer...");
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + " is about to print " + documents[i].getDocumentName());
                printer.printDocument(documents[i]);
                sleep((int) (Math.random() * seconds));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + " has finished using the printer..." + "\n");
        //semaphore.release();
    }

}
