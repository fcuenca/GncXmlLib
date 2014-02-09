//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.gnc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.gnucash.xml.trn.Currency;
import org.gnucash.xml.trn.DateEntered;
import org.gnucash.xml.trn.DatePosted;
import org.gnucash.xml.trn.Id;
import org.gnucash.xml.trn.Slots;
import org.gnucash.xml.trn.Splits;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}id"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}currency"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}num" minOccurs="0"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}date-posted"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}date-entered"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}description" minOccurs="0"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}slots" minOccurs="0"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/trn}splits"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="2.0.0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "currency",
    "num",
    "datePosted",
    "dateEntered",
    "description",
    "slots",
    "splits"
})
@XmlRootElement(name = "transaction")
public class Transaction {

    @XmlElement(namespace = "http://www.gnucash.org/XML/trn", required = true)
    protected Id id;
    @XmlElement(namespace = "http://www.gnucash.org/XML/trn", required = true)
    protected Currency currency;
    @XmlElement(namespace = "http://www.gnucash.org/XML/trn")
    protected String num;
    @XmlElement(name = "date-posted", namespace = "http://www.gnucash.org/XML/trn", required = true)
    protected DatePosted datePosted;
    @XmlElement(name = "date-entered", namespace = "http://www.gnucash.org/XML/trn", required = true)
    protected DateEntered dateEntered;
    @XmlElement(namespace = "http://www.gnucash.org/XML/trn")
    protected String description;
    @XmlElement(namespace = "http://www.gnucash.org/XML/trn")
    protected Slots slots;
    @XmlElement(namespace = "http://www.gnucash.org/XML/trn", required = true)
    protected Splits splits;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Id }
     *     
     */
    public Id getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Id }
     *     
     */
    public void setId(Id value) {
        this.id = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

    /**
     * Gets the value of the num property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNum() {
        return num;
    }

    /**
     * Sets the value of the num property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNum(String value) {
        this.num = value;
    }

    /**
     * Gets the value of the datePosted property.
     * 
     * @return
     *     possible object is
     *     {@link DatePosted }
     *     
     */
    public DatePosted getDatePosted() {
        return datePosted;
    }

    /**
     * Sets the value of the datePosted property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatePosted }
     *     
     */
    public void setDatePosted(DatePosted value) {
        this.datePosted = value;
    }

    /**
     * Gets the value of the dateEntered property.
     * 
     * @return
     *     possible object is
     *     {@link DateEntered }
     *     
     */
    public DateEntered getDateEntered() {
        return dateEntered;
    }

    /**
     * Sets the value of the dateEntered property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateEntered }
     *     
     */
    public void setDateEntered(DateEntered value) {
        this.dateEntered = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the slots property.
     * 
     * @return
     *     possible object is
     *     {@link Slots }
     *     
     */
    public Slots getSlots() {
        return slots;
    }

    /**
     * Sets the value of the slots property.
     * 
     * @param value
     *     allowed object is
     *     {@link Slots }
     *     
     */
    public void setSlots(Slots value) {
        this.slots = value;
    }

    /**
     * Gets the value of the splits property.
     * 
     * @return
     *     possible object is
     *     {@link Splits }
     *     
     */
    public Splits getSplits() {
        return splits;
    }

    /**
     * Sets the value of the splits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Splits }
     *     
     */
    public void setSplits(Splits value) {
        this.splits = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
