package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;

public class buffer {

	 static int key = 0;
	 static boolean keyA = false ;
	 static List<Integer> keys=new ArrayList<Integer>();


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

	 synchronized void setbuffer(int eyy) {
		 while (keyA == true) {
             try {
                 wait();
             } catch (InterruptedException e) { }
         }

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

	}
}
