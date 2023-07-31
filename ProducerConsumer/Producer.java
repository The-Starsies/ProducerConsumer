package ProducerConsumer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Producer extends Thread {

    private ITstudents student;
    private int number;
    List<String> Names=new ArrayList<String>();
    List<Integer> Ids=new ArrayList<Integer>();
   // List<String> programme=new ArrayList<ITstudents>();
    List<String> Courses=new ArrayList<String>();
    List<String> marks=new ArrayList<String>();
    List<ITstudents> students=new ArrayList<ITstudents>();
    int buffer ;
    buffer Ch = new buffer();
    public Producer(buffer number) {
    //	student = c;
        this.Ch = number;
        RandomDetails();
    }


    public void RandomDetails() {

    	//14 names
    	Names.add("Manny");
    	Names.add("Leyla");
    	Names.add("Stitch");Names.add("Lukas");Names.add("Majest");Names.add("Nkosi");
    	Names.add("Xeonn");Names.add("Dwight");Names.add("James");Names.add("Luka");
    	Names.add("Juice");Names.add("Lukic"); Names.add("bronny"); Names.add("Piastri");
    	Collections.shuffle(Names);

    	//Course Codes
    	Courses.add("CSC435");Courses.add("CSG415");Courses.add("CCR300");Courses.add("CSC811");
    	Courses.add("ACC111");Courses.add("ACF321");Courses.add("AED111");Courses.add("ADA111");
    	Collections.shuffle(Courses);
    //	Courses.add("BUS111");Courses.add("BCF321");Courses.add("BED111");Courses.add("BDA111");

    	int minVal = 10000000; int maxVal = 99999999; int minVal1 = 0;  int maxVal1 = 100;

	    //Creat unique ids for everyone
	    while (Ids.size() != 15) {
	        int randInt = ThreadLocalRandom.current().nextInt(minVal, maxVal);
	        if(!Ids.contains(randInt)) {
	        Ids.add(randInt);

	        }
	    }



    	while (marks.size() != 8) {
	        int randInt = ThreadLocalRandom.current().nextInt(minVal1, maxVal1);
	        marks.add(String.valueOf(randInt));
	    } }


    public void RandoProdcucer() {
    	int minVal1 = 0; int maxVal1 = 100;

	    if(!marks.isEmpty()) {
	    	marks.clear();
	    }

    	while (marks.size() != 8) {
	        int randInt = ThreadLocalRandom.current().nextInt(minVal1, maxVal1);
	        marks.add(String.valueOf(randInt));
	    }

    }

    @SuppressWarnings("resource")
	public synchronized void writeToXML(String xml , ITstudents g) {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("Student");

            // create data elements and place them under root
            e = dom.createElement("Name");
            e.appendChild(dom.createTextNode(g.Stdntnname));
            rootEle.appendChild(e);

            e = dom.createElement("id");
            e.appendChild(dom.createTextNode(Integer.toString(g.StdntId)));
            rootEle.appendChild(e);

            e = dom.createElement("programme");
            e.appendChild(dom.createTextNode(g.StdntPrgrmme));
            rootEle.appendChild(e);

            e = dom.createElement("courses");
            e.appendChild(dom.createTextNode(g.Courses.toString()));
            rootEle.appendChild(e);

            e = dom.createElement("marks");
            e.appendChild(dom.createTextNode(g.Marks.toString()));
            rootEle.appendChild(e);

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "students.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                                     new StreamResult(new FileOutputStream(xml)));


            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }


    @SuppressWarnings("resource")
	public synchronized void writeTobuffer(String xml , int g) {
    	File buffer = new File("/ProducerConsumer/ProducerConsumer/buffer.xml");
    	if(buffer.canRead()&&buffer.canWrite()) {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("class");

            // create data elements and place them under root
            e = dom.createElement("buffer");
            e.appendChild(dom.createTextNode(String.valueOf(g)));
            rootEle.appendChild(e);


            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                                     new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }}



    public void run() {
    	///ProducerConsumer/ProducerConsumer/buffer.xml

    //	Ch.setbuffer(0);
    	int val = 0 ;
    	if(Ch.checkbuffer() == "empty") {
        for (int i = 0; i < 11; i++) {
         //  cubbyhole.put(i);

            try {


    	        ITstudents c = new ITstudents(Names.get(i),Ids.get(i),"IT", Courses ,marks);
    	        students.add(c);
    	       // C.setbuffer(i);
    	        writeToXML( "C:\\Users\\blank\\workspace\\ProducerConsumer\\ProducerConsumer\\students"+i+".xml",  c);

    	       //System.out.println(marks);
    	        RandoProdcucer();

    	      //  C.checkbuffer();

            }finally { }
            val=i;

        }//
        Ch.setbuffer(val);
        Consumer c1 = new Consumer();
        c1.start();

    }}
}
