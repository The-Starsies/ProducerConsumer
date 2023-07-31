package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;

public class buffer {


	// A HALF IMPLEMENTED BUFFER THAT LACKS A COMPLETE SOLUTIO TO RESOURCE LOCKING BUT A ATTEMPT WAS MADE TO TRY AND ADDRESS THE ISSUE BY USING THE SYCHRONIZED TAG
	 static int key = 0;
	 static boolean keyA = false ;
	 static List<Integer> keys=new ArrayList<Integer>();

	 // EMPTY COSTRUCTOR
	public buffer() {




	}
	public buffer(Producer p1, Consumer c1) {
		// TODO Auto-generated constructor stub
	}
	 synchronized  String checkbuffer() {


		if(getbuffer() != 0  ||  getbuffer() >= 10 ) {
		return "full" ; }

		else if(getbuffer() ==0 ) {

		return	"empty";	}else {


			return "full";}


	}
	 synchronized int getbuffer() {

		return keys.size();



	}

	 // THE FUNCTION TO TRY AND SET A BUFFER SIZE BY ADDING VALUES TO A LIST AND USING TO KEEP COUNT LIKE A STACK AND ALSO USING ITS INHERENT FUCNTIONS OF ADDING , CLEARING AND INSERTING
	 synchronized void setbuffer(int eyy) {
		 while (keyA == true) {
             try {
                 wait();
             } catch (InterruptedException e) { }
         }
		 keyA = true;
		 if(eyy==0) {

			 keys.clear();
			 key = eyy;

		 }else {
			 keys.clear();
		 for(int j = 1 ; j<=eyy; j++  ) {

			 keys.add(j);
		 }
		 	key = eyy ;

		 }
		 keyA = false;
		 notifyAll();

	}
}
