package threads;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lsea.Bike;

import java.util.*;
/**
 * Class that is used to override the thread class and get own run method. Here is made the multhreading used in LSEA(main class)<br>
 * This class is running the Bike method rateBikes, which is rating all of the bikes in the list of bikes offers<br>
 * Implements Runnable<br>
 * All setters and getters are automatically generated by lombok.<br>
 * Both NoArgsConstructor and AllArgsConstructor are also generated automatically.
 *
 * @author Patryk Dunajewski
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ThreadOne implements Runnable{

    /*** thread_Bikes-> list with all offers of bikes (private list(bike))*/
    private List<Bike> thread_Bikes;
    /*** thread_ID-> ID of the thread in range (0;numberThreads-1) (private int)*/
    private int thread_ID;
    /*** listA -object of the class of ALRates, so the list with bikes and rates(private ALRates)*/
    private ALRates listA;
    /*** listB -object of the class of ALRates, so the list with bikes and rates(private ALRates)*/
    private ALRates listB;
    /*** listC -object of the class of ALRates, so the list with bikes and rates(private ALRates)*/
    private ALRates listC;
    /*** listD -object of the class of ALRates, so the list with bikes and rates(private ALRates)*/
    private ALRates listD;
    /*** listE -object of the class of ALRates, so the list with bikes and rates(private ALRates)*/
    private ALRates listE;
    /*** numberThreads-> maximum nuber of threads that will be used in multithreading(private int)*/
    private final int numberThreads=4;

    /***Override of the run method in thread, so mine thread is created.<br>
     * Bike.rateBikes method is used here to rate bikes and then add it to one of five lists<br>
     * 1.listA - rate >=180<br>
     * 2.listB - rate >=135<br>
     * 3.listC - rate >=90<br>
     * 4.listD - rate >=45<br>
     * 5.listE - else<br>
     * This method is used in LSEA
     */
    @Override
    public void run() {

        System.out.println("Thread number " + this.getThread_ID() + " just started.");
        //int nextT = this.getBikes().size()/this.getNumberThreads();
        //for(int i = this.getThread_ID() * nextT; i<nextT*(this.getThread_ID() + 1); i+=1) {
        for(int i=this.getThread_ID();i<this.getThread_Bikes().size();i+=this.getNumberThreads()){
            RateArr a =this.getThread_Bikes().get(i).rateBikes();
            if(a.getRate() >= 180) this.getListA().add(a);
            else if(a.getRate() >= 135) this.getListB().add(a);
            else if(a.getRate() >= 90) this.getListC().add(a);
            else if(a.getRate() >= 45) this.getListD().add(a);
            else this.getListE().add(a);
            //System.out.println(i);
        }
		/*
		if((this.getBikes().size()%this.getNumberThreads() != 0) && (this.getThread_ID() == this.getNumberThreads() - 1)) {
			for( int i = nextT*this.getNumberThreads(); i<this.getBikes().size(); i++) {
				RateArr a = this.getBikes().get(i).rateBikes();
				if(a.getRate() >= 180) this.getListA().add(a);
				else if(a.getRate() >= 135) this.getListB().add(a);
				else if(a.getRate() >= 90) this.getListC().add(a);
				else if(a.getRate() >= 45) this.getListD().add(a);
				else this.getListE().add(a);
			}
		}*/
        System.out.println("Thread number " + this.getThread_ID() + " ended its work.");
    }
}