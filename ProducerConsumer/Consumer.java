package ProducerConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Consumer extends Thread {

	public List<ITstudents> students=new ArrayList<ITstudents>();
	List<String> CourseLists=new ArrayList<String>();
    List<String> Markslist=new ArrayList<String>();
    private int number;
    String Name ;String ID ;String Programme;String Courses;String Marks ;File memory ;
    buffer Ch = new buffer();










    public Consumer(buffer number) { this.Ch = number;  }






    public Consumer() 				{}






    public void resultsSummary()  {

if (!students.isEmpty()) {

	System.out.println(students.size()) ;
    	 //Summarizes Results of each Student
    	// students.iterator();
    	 //for (int i=0 ; i<=Courses.length()) {

     //}
    	 for (int lk = 0 ;lk<=students.size();lk++) {
    		 ITstudents s=students.get(lk);
    		 int avg = 0 ;
    		 Markslist= s.getMarks();
    		 System.out.println(s.getMarks()) ;
    		 for(int i=0;i<= Markslist.size();i++) {

    			 avg += Integer.valueOf(Markslist.get(i));
    		 }
    		 avg =avg/8;

    		 System.out.println("Student Name : "+s.Stdntnname);
             System.out.println("ID : "+ s.StdntId);
             System.out.println("Programme : "+ s.StdntPrgrmme);
             System.out.println("Courses : "+ s.Courses);
             System.out.println("Marks : "+ s.Marks) ;
             if(avg>=50) {
            System.out.println("\t THE STUDENT PASSED : "+ avg +"Percent ") ;

             }else if(avg<50) {
            	 System.out.println("\t THE STUDENT PASSED : "+ avg +"Percent ") ;

             }

             System.out.println(avg);
         }
}

    }

    public void delete() {
    	// Deletes ALl XML FILES
   	 for (int i = 0; i <= 10; i++) {
            //  cubbyhole.put(i);

         /*      memory = new File ("C:\\Users\\blank\\workspace\\ProducerConsumer\\ProducerConsumer\\students"+i+".xml");
			if(memory.isFile()) {
			if(memory.canRead()&&memory.canExecute()){

				memory.delete();
			}
            } */


   	try {
        // creates a file object
        Path path = Paths.get("C:\\Users\\blank\\workspace\\ProducerConsumer\\ProducerConsumer\\students"+i+".xml");
       // path.c
        // deletes the file
        boolean value = Files.deleteIfExists(path);
        if(value) {
          System.out.println("Files have been successfully deleted.");
        }
        else {
          System.out.println("File doesn't exit");
        }
      } catch (Exception e) {
        e.getStackTrace();
      }

    }}

    public void run() {

    	System.out.print("\n"+Ch.getbuffer());

    	if(Ch.getbuffer()  != 0) {

        for (int i = 0; i < 10; i++) {


        	//STAGE OE
            try
            {
            //creating a constructor of file class and parsing an XML file
            File file = new File("C:\\Users\\blank\\workspace\\ProducerConsumer\\ProducerConsumer\\students"+i+".xml");

            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
           // doc.
        //    System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Student");

            // nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
            Node node = nodeList.item(itr);
          //  System.out.println("\nNode Name :" + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
            Element eElement = (Element) node;

             Name = eElement.getElementsByTagName("Name").item(0).getTextContent();
             ID = eElement.getElementsByTagName("id").item(0).getTextContent();
             Programme =eElement.getElementsByTagName("programme").item(0).getTextContent();
             Courses = eElement.getElementsByTagName("courses").item(0).getTextContent();
             Marks = eElement.getElementsByTagName("marks").item(0).getTextContent();


             System.out.println("\n---------------------------------------------------------------------");
            System.out.println("\nStudent Name : "+Name);
            System.out.println("ID : "+ ID);
            System.out.println("Programme : "+ Programme);
           // System.out.println("Courses : "+ Courses);
           // System.out.println("Marks : "+ Marks) ;



            	int avg = 0;
            	 StringBuilder sb=new StringBuilder(Courses);
                 sb.deleteCharAt(Courses.length()-1);sb.deleteCharAt(0);
                // System.out.print("\n"+sb);

                 StringBuilder sb2=new StringBuilder(Marks);
                 sb2.deleteCharAt(sb2.length()-1);sb2.deleteCharAt(0);

                 String[] elements = sb2.toString().split(",");
                 String[] elements2 = sb.toString().split(",");
                 List<String> courserr  = Arrays.asList( elements2) ;
                 List<String> carkos = (Arrays.asList(elements));
                 for (int lk = 0 ;lk<courserr.size();lk++) {
                	 System.out.print("\n Course : " + courserr.get(lk) +" Grade : " +carkos.get(lk) );
                	 avg+=Integer.valueOf(carkos.get(lk).trim());

                 }
                avg = avg/8;

                if(avg>=50) {
                    System.out.println("\t THE STUDENT PASSED WITH : "+ avg +" Percent") ;

                     }else if(avg<50) {
                    	 System.out.println("\t THE STUDENT FAILED WTIH : "+ avg +" Percent") ;

                     }





            }
            }
            }
            catch (Exception e)
            {
            e.printStackTrace();
            }
         //   resultsSummary();
            Ch.setbuffer(0);
            delete();
        }

    }else { System.out.println("\nbuffer is empty please run the producer !"); }

}

}