import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Rate> getCurrencyListFromSource(String source) {
        List<Rate> rates = new ArrayList();
        try {
            File xmlSource = new File(source);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(source);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Cube");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE && !nNode.hasChildNodes()) {
                    Element eElement = (Element) nNode;
                    rates.add(new Rate(eElement.getAttribute("currency"), Double.valueOf(eElement.getAttribute("rate"))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }
}
