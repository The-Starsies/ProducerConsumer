package ProducerConsumer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
public class ProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {

    	buffer C = new buffer();
        Producer p1 = new Producer(C);
        Consumer c1 = new Consumer(C);
        p1.start();







    }
}
