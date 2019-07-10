package SharedPrinter;

//Ctrl 4 to show output window
import java.util.concurrent.Semaphore;

public class PrintingSystem {

    public static void main(String[] args) {
        //Initialising Thread Groups
        ThreadGroup groups[] = new ThreadGroup[3];
        groups[0] = Thread.currentThread().getThreadGroup();
        groups[1] = new ThreadGroup(groups[0], "Students");
        groups[2] = new ThreadGroup(groups[0], "Technicians");

        //Initialising the printer
        LaserPrinter printer = new LaserPrinter("Printer1", 250, 500, 0);

        //Initialising the 4 students
        Student student1 = new Student(groups[1], printer, "Student A");
        Student student2 = new Student(groups[1], printer, "Student B");
        Student student3 = new Student(groups[1], printer, "Student C");
        Student student4 = new Student(groups[1], printer, "Student D");

        //Initialising the 2 technicians
        PaperTechnician paperTechnician = new PaperTechnician(groups[2], printer, "Paper Technician");
        TonerTechnician tonerTechnician = new TonerTechnician(groups[2], printer, "Toner Technician");

        System.out.println("Shared Printer Program" + "\n");
        System.out.println(printer.toString() + "\n");

        //Starts the 6 threads
        student1.start();
        student2.start();
        student3.start();
        student4.start();
        paperTechnician.start();
        tonerTechnician.start();
        

        //Waits for all 6 threads to terminate
        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();
            paperTechnician.join();
            tonerTechnician.join();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        System.out.println("All " + groups[1].getName() + " and " + groups[2].getName() + " have left" + "\n");
        //Prints out the final printer status
        System.out.println("FINAL PRINTER STATUS:");
        System.out.println(printer.toString() + "\n");

        //End
        System.out.println("END OF SIMULATION");

    }
}
