//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.recurrence;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gnucash.xml.recurrence package. 
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

    private final static QName _PeriodType_QNAME = new QName("http://www.gnucash.org/XML/recurrence", "period_type");
    private final static QName _WeekendAdj_QNAME = new QName("http://www.gnucash.org/XML/recurrence", "weekend_adj");
    private final static QName _Mult_QNAME = new QName("http://www.gnucash.org/XML/recurrence", "mult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gnucash.xml.recurrence
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Start }
     * 
     */
    public Start createStart() {
        return new Start();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/recurrence", name = "period_type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPeriodType(String value) {
        return new JAXBElement<String>(_PeriodType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/recurrence", name = "weekend_adj")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createWeekendAdj(String value) {
        return new JAXBElement<String>(_WeekendAdj_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/recurrence", name = "mult")
    public JAXBElement<Integer> createMult(Integer value) {
        return new JAXBElement<Integer>(_Mult_QNAME, Integer.class, null, value);
    }

}
