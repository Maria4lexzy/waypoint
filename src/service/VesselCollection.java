package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Vessel;

public class VesselCollection {private List<Vessel> vessels = new ArrayList<>();

  public void loadFromXML(String filename) {
    try {
      // for parsing xml
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(filename);

//      todo: maybe use jackson xml parser
      NodeList vesselNodes = doc.getElementsByTagName("vessel");
      for (int i = 0; i < vesselNodes.getLength(); i++) {
        Element vesselElement = (Element) vesselNodes.item(i);
        String name = vesselElement.getAttribute("name");
        double lat = Double.parseDouble(vesselElement.getAttribute("latitude"));
        double lon = Double.parseDouble(vesselElement.getAttribute("longitude"));
        vessels.add(new Vessel(name, lat, lon));
      }
    } catch (Exception e) {
      System.err.println("Error reading XML file: " + e.getMessage());
    }
  }

  public List<Vessel> getVessels() {
    return vessels;
  }
}
