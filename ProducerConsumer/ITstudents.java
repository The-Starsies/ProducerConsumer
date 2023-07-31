package ProducerConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.security.pkcs11.wrapper.Functions;

public class ITstudents {
    //private int contents;

    String Stdntnname ;
    int StdntId ;
    String StdntPrgrmme ;
    List<String> Courses=new ArrayList<String>();
    List<String> Marks=new ArrayList<String>();
    List<String> Marks2=new ArrayList<String>();
    private boolean available = false;

    public ITstudents(String Stdntnname2 ,int StdntId2 , String StdntPrgrmme2 ,List<String> Courses2 ,List<String> Marks2) {

    	this.Stdntnname =Stdntnname2;
        this.StdntId  = StdntId2 ;
        this.StdntPrgrmme = StdntPrgrmme2 ;this.Courses = Courses2 ;this.Marks = Marks2;
    }
    public ITstudents(String Stdntnname2 ,String StdntId2 , String StdntPrgrmme2 ,String Courses2 ,String Marks2) {

    	this.Stdntnname =Stdntnname2;
        this.StdntId  = Integer.valueOf(StdntId2) ;
        this.StdntPrgrmme = StdntPrgrmme2 ;

        StringBuilder sb=new StringBuilder(Courses2);
        sb.deleteCharAt(Courses2.length()-1);sb.deleteCharAt(0);
       // System.out.print("\n"+sb);

        StringBuilder sb2=new StringBuilder(Marks2);
        sb2.deleteCharAt(sb2.length()-1);sb2.deleteCharAt(0);

        String[] elements = sb2.toString().split(",");
        this.Courses = Arrays.asList( sb.toString()) ;
        this.Marks = (Arrays.asList(elements));
    //    System.out.print("\n"+Marks.toString()+"\n");



    }




    public synchronized int getID() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        available = false;
        notifyAll();
        return StdntId;
    }

    public synchronized String getSname() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        available = false;
        notifyAll();
        return Stdntnname;
    }

    public synchronized String getProgamms() {
    	 while (available == false) {
             try {
                 wait();
             } catch (InterruptedException e) { }
         }
        notifyAll();
        return StdntPrgrmme;
    }

    public synchronized List<String> getCourses() {
    	 while (available == false) {
             try {
                 wait();
             } catch (InterruptedException e) { }
         }
        notifyAll();
        return Courses;
    }

    public synchronized List<String> getMarks() {
    	 while (available == false) {
             try {
                 wait();
             } catch (InterruptedException e) { }
         }
        notifyAll();
        return this.Marks;
    }

    public synchronized void put(String Stdntnname2 ,int StdntId2 , String StdntPrgrmme2 ,List<String> Courses2 ,List<String> Marks2) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
       // contents = value;
        Stdntnname =Stdntnname2;
        StdntId  = StdntId2 ;
        StdntPrgrmme = StdntPrgrmme2 ;Courses = Courses2 ;Marks = Marks2;

        available = true;
        notifyAll();
    }
}
