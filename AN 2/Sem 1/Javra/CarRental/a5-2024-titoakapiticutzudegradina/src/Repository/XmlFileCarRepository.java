package Repository;

import Domain.Car;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class XmlFileCarRepository extends FileRepository<Integer, Car>{
    public XmlFileCarRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.filename);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document parsedDocument = (Document) documentBuilder.parse(xmlFile);
        NodeList parsedCarNodeList = parsedDocument.getElementsByTagName("car");

        for(int i = 0; i < parsedCarNodeList.getLength(); i++){
            Element carElement = (Element) parsedCarNodeList.item(i);

            Integer id = Integer.parseInt(carElement.getElementsByTagName("id").item(0).getTextContent());
            String carBrand = carElement.getElementsByTagName("carBrand").item(0).getTextContent();
            String carModel = carElement.getElementsByTagName("carModel").item(0).getTextContent();
            Integer yearProduction = Integer.parseInt(carElement.getElementsByTagName("yearProduction").item(0).getTextContent());
            Double pricePerDay = Double.parseDouble(carElement.getElementsByTagName("pricePerDay").item(0).getTextContent());

            Car car = new Car(id, carBrand, carModel, yearProduction, pricePerDay);
            super.add(id, car);
        }
    }

    @Override
    protected void writeToFile(){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("cars");
            document.appendChild(rootElement);
            Iterator<Car> carIterator = super.iterator();
            while(carIterator.hasNext()){
                Car car = carIterator.next();
                Element carElement = document.createElement("car");

                Element idElement = document.createElement("id");
                idElement.appendChild(document.createTextNode(car.getId().toString()));
                carElement.appendChild(idElement);

                Element carBrandElement = document.createElement("carBrand");
                carBrandElement.appendChild(document.createTextNode(car.getCarBrand()));
                carElement.appendChild(carBrandElement);

                Element carModelElement = document.createElement("carModel");
                carModelElement.appendChild(document.createTextNode(car.getCarModel()));
                carElement.appendChild(carModelElement);

                Element yearProductionElement = document.createElement("yearProduction");
                yearProductionElement.appendChild(document.createTextNode(Integer.toString(car.getYearProduction())));
                carElement.appendChild(yearProductionElement);

                Element pricePerDayElement = document.createElement("pricePerDay");
                pricePerDayElement.appendChild(document.createTextNode(Double.toString(car.getPricePerDay())));
                carElement.appendChild(pricePerDayElement);

                rootElement.appendChild(carElement);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.filename));
            transformer.transform(domSource, streamResult);


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
