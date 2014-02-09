//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.bt_prox;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gnucash.xml.bt_prox package. 
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

    private final static QName _DueDay_QNAME = new QName("http://www.gnucash.org/XML/bt-prox", "due-day");
    private final static QName _DiscDay_QNAME = new QName("http://www.gnucash.org/XML/bt-prox", "disc-day");
    private final static QName _CutoffDay_QNAME = new QName("http://www.gnucash.org/XML/bt-prox", "cutoff-day");
    private final static QName _Discount_QNAME = new QName("http://www.gnucash.org/XML/bt-prox", "discount");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gnucash.xml.bt_prox
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/bt-prox", name = "due-day")
    public JAXBElement<Integer> createDueDay(Integer value) {
        return new JAXBElement<Integer>(_DueDay_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/bt-prox", name = "disc-day")
    public JAXBElement<Integer> createDiscDay(Integer value) {
        return new JAXBElement<Integer>(_DiscDay_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/bt-prox", name = "cutoff-day")
    public JAXBElement<Integer> createCutoffDay(Integer value) {
        return new JAXBElement<Integer>(_CutoffDay_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/bt-prox", name = "discount")
    public JAXBElement<String> createDiscount(String value) {
        return new JAXBElement<String>(_Discount_QNAME, String.class, null, value);
    }

}