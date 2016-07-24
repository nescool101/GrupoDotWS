package com.test.utilities;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.test.mongocontext.MovieDao;


/**
 * Class with The utilities in the app
 * 
 * @author NESTOR version 1.0 23/07/2016
 */
public class Utilities {

  
  /**Method that stablish the beancontent
   * 
   * @return movieContext
   */
  public static MovieDao getBeanContext() {
    ClassPathXmlApplicationContext context = getContextCRUD();
    MovieDao movieContext = context.getBean(MovieDao.class);
    return movieContext;

  }
  /**Method that stablish the ClassPathXmlApplicationContext
   * 
   * @return context
   */

  public static ClassPathXmlApplicationContext getContextCRUD() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        new ClassPathResource("spring-config.xml").getPath());
    return context;
  }
  /**Method that convert string to xml
   * 
   * @return doc
   */
  public static Document convertStringToXml(String xml)
      throws SAXException, IOException, ParserConfigurationException {
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        .parse(new InputSource(new StringReader(xml)));
    return doc;
  }
}
