//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.invoice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.gnucash.xml.gnc.OwnerContent;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.gnucash.xml.invoice package. 
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

    private final static QName _Id_QNAME = new QName("http://www.gnucash.org/XML/invoice", "id");
    private final static QName _ChargeAmt_QNAME = new QName("http://www.gnucash.org/XML/invoice", "charge-amt");
    private final static QName _Active_QNAME = new QName("http://www.gnucash.org/XML/invoice", "active");
    private final static QName _Owner_QNAME = new QName("http://www.gnucash.org/XML/invoice", "owner");
    private final static QName _Notes_QNAME = new QName("http://www.gnucash.org/XML/invoice", "notes");
    private final static QName _BillingId_QNAME = new QName("http://www.gnucash.org/XML/invoice", "billing_id");
    private final static QName _Billto_QNAME = new QName("http://www.gnucash.org/XML/invoice", "billto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.gnucash.xml.invoice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Guid }
     * 
     */
    public Guid createGuid() {
        return new Guid();
    }

    /**
     * Create an instance of {@link Opened }
     * 
     */
    public Opened createOpened() {
        return new Opened();
    }

    /**
     * Create an instance of {@link Postacc }
     * 
     */
    public Postacc createPostacc() {
        return new Postacc();
    }

    /**
     * Create an instance of {@link Terms }
     * 
     */
    public Terms createTerms() {
        return new Terms();
    }

    /**
     * Create an instance of {@link Posttxn }
     * 
     */
    public Posttxn createPosttxn() {
        return new Posttxn();
    }

    /**
     * Create an instance of {@link Postlot }
     * 
     */
    public Postlot createPostlot() {
        return new Postlot();
    }

    /**
     * Create an instance of {@link Slots }
     * 
     */
    public Slots createSlots() {
        return new Slots();
    }

    /**
     * Create an instance of {@link Posted }
     * 
     */
    public Posted createPosted() {
        return new Posted();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "id")
    public JAXBElement<String> createId(String value) {
        return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "charge-amt")
    public JAXBElement<String> createChargeAmt(String value) {
        return new JAXBElement<String>(_ChargeAmt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "active")
    public JAXBElement<Boolean> createActive(Boolean value) {
        return new JAXBElement<Boolean>(_Active_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OwnerContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "owner")
    public JAXBElement<OwnerContent> createOwner(OwnerContent value) {
        return new JAXBElement<OwnerContent>(_Owner_QNAME, OwnerContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "notes")
    public JAXBElement<String> createNotes(String value) {
        return new JAXBElement<String>(_Notes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "billing_id")
    public JAXBElement<String> createBillingId(String value) {
        return new JAXBElement<String>(_BillingId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OwnerContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gnucash.org/XML/invoice", name = "billto")
    public JAXBElement<OwnerContent> createBillto(OwnerContent value) {
        return new JAXBElement<OwnerContent>(_Billto_QNAME, OwnerContent.class, null, value);
    }

}
