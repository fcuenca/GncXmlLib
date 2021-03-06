//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.trn;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gnucash.xml.trn package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Description_QNAME = new QName("http://www.gnucash.org/XML/trn", "description");
    private final static QName _Num_QNAME = new QName("http://www.gnucash.org/XML/trn", "num");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gnucash.xml.trn
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Slots }
     * 
     */
    public Slots createSlots() {
        return new Slots();
    }

    /**
     * Create an instance of {@link DatePosted }
     * 
     */
    public DatePosted createDatePosted() {
        return new DatePosted();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link Splits }
     * 
     */
    public Splits createSplits() {
        return new Splits();
    }

    /**
     * Create an instance of {@link DateEntered }
     * 
     */
    public DateEntered createDateEntered() {
        return new DateEntered();
    }

    /**
     * Create an instance of {@link Split }
     * 
     */
    public Split createSplit() {
        return new Split();
    }

    /**
     * Create an instance of {@link Id }
     * 
     */
    public Id createId() {
        return new Id();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/trn", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/trn", name = "num")
    public JAXBElement<String> createNum(String value) {
        return new JAXBElement<String>(_Num_QNAME, String.class, null, value);
    }

}
