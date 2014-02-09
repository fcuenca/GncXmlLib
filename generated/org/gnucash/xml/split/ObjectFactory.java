//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.split;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gnucash.xml.split package. 
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

    private final static QName _Action_QNAME = new QName("http://www.gnucash.org/XML/split", "action");
    private final static QName _Value_QNAME = new QName("http://www.gnucash.org/XML/split", "value");
    private final static QName _ReconciledState_QNAME = new QName("http://www.gnucash.org/XML/split", "reconciled-state");
    private final static QName _Quantity_QNAME = new QName("http://www.gnucash.org/XML/split", "quantity");
    private final static QName _Memo_QNAME = new QName("http://www.gnucash.org/XML/split", "memo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gnucash.xml.split
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link ReconcileDate }
     * 
     */
    public ReconcileDate createReconcileDate() {
        return new ReconcileDate();
    }

    /**
     * Create an instance of {@link Slots }
     * 
     */
    public Slots createSlots() {
        return new Slots();
    }

    /**
     * Create an instance of {@link Lot }
     * 
     */
    public Lot createLot() {
        return new Lot();
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
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/split", name = "action")
    public JAXBElement<String> createAction(String value) {
        return new JAXBElement<String>(_Action_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/split", name = "value")
    public JAXBElement<String> createValue(String value) {
        return new JAXBElement<String>(_Value_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/split", name = "reconciled-state")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createReconciledState(String value) {
        return new JAXBElement<String>(_ReconciledState_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/split", name = "quantity")
    public JAXBElement<String> createQuantity(String value) {
        return new JAXBElement<String>(_Quantity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/split", name = "memo")
    public JAXBElement<String> createMemo(String value) {
        return new JAXBElement<String>(_Memo_QNAME, String.class, null, value);
    }

}
