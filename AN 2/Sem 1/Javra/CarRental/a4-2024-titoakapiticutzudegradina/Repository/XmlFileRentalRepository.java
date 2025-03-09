package Repository;

import Domain.Car;
import Domain.Rental;
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

public class XmlFileRentalRepository extends FileRepository<Integer, Rental> {
    public XmlFileRentalRepository(String filename) throws ParserConfigurationException, IOException, SAXException{
        super(filename);
    }

    @Override
    protected void readFromFile() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.filename);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document parsedDocument = (Document) documentBuilder.parse(xmlFile);
        NodeList parsedRentalNodeList = parsedDocument.getElementsByTagName("rental");

        for(int i = 0; i < parsedRentalNodeList.getLength(); i++){
            Element rentalElement = (Element) parsedRentalNodeList.item(i);

            Integer id = Integer.parseInt(rentalElement.getElementsByTagName("id").item(0).getTextContent());
            Integer carId = Integer.parseInt(rentalElement.getElementsByTagName("carId").item(0).getTextContent());
            String clientName = rentalElement.getElementsByTagName("clientName").item(0).getTextContent();
            String clientPhoneNumber = rentalElement.getElementsByTagName("clientPhoneNumber").item(0).getTextContent();
            Integer daysForRental = Integer.parseInt(rentalElement.getElementsByTagName("daysForRental").item(0).getTextContent());

            Car car = new Car(carId, "mock", "mock", 0, 0);
            Rental rental = new Rental(id, car, clientName, clientPhoneNumber, daysForRental);
            XmlFileCarRepository xmlCarRepo = new XmlFileCarRepository("data/car.xml");
            for(Car actualCar : xmlCarRepo.getAll()){
                if(actualCar.getId().equals(carId)){
                    rental.setCar(actualCar);
                    break;
                }
            }
            super.add(id, rental);
        }
    }

    @Override
    protected void writeToFile(){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("rentals");
            document.appendChild(rootElement);
            Iterator<Rental> rentalIterator = super.iterator();

            while(rentalIterator.hasNext()){
                Rental rental = rentalIterator.next();
                Element rentalElement = document.createElement("rental");

                Element idElement = document.createElement("id");
                idElement.appendChild(document.createTextNode(rental.getId().toString()));
                rentalElement.appendChild(idElement);

                Element carIdElement = document.createElement("carId");
                carIdElement.appendChild(document.createTextNode(rental.getCar().getId().toString()));
                rentalElement.appendChild(carIdElement);

                Element clientNameElement = document.createElement("clientName");
                clientNameElement.appendChild(document.createTextNode(rental.getNameForRental()));
                rentalElement.appendChild(clientNameElement);

                Element clientPhoneNumberElement = document.createElement("clientPhoneNumber");
                clientPhoneNumberElement.appendChild(document.createTextNode(rental.getPhoneNumber()));
                rentalElement.appendChild(clientPhoneNumberElement);

                Element daysForRentalElement = document.createElement("daysForRental");
                daysForRentalElement.appendChild(document.createTextNode(Integer.toString(rental.getDaysForRental())));
                rentalElement.appendChild(daysForRentalElement);


                rootElement.appendChild(rentalElement);

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
