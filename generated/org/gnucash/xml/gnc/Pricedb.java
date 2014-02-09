//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.gnc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.gnucash.xml.price.Commodity;
import org.gnucash.xml.price.Currency;
import org.gnucash.xml.price.Id;
import org.gnucash.xml.price.Time;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.gnucash.org/XML/gnc}Price" maxOccurs="unbounded"/>
 *       &lt;attribute name="version" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="1"/>
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
    "price"
})
@XmlRootElement(name = "pricedb")
public class Pricedb {

    @XmlElement(namespace = "", required = true)
    protected List<Pricedb.Price> price;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the price property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the price property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pricedb.Price }
     * 
     * 
     */
    public List<Pricedb.Price> getPrice() {
        if (price == null) {
            price = new ArrayList<Pricedb.Price>();
        }
        return this.price;
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
     *         &lt;element ref="{http://www.gnucash.org/XML/price}id"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}commodity"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}currency"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}time"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}source" minOccurs="0"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}type" minOccurs="0"/>
     *         &lt;element ref="{http://www.gnucash.org/XML/price}value"/>
     *       &lt;/sequence>
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
        "commodity",
        "currency",
        "time",
        "source",
        "type",
        "value"
    })
    public static class Price {

        @XmlElement(namespace = "http://www.gnucash.org/XML/price", required = true)
        protected Id id;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price", required = true)
        protected Commodity commodity;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price", required = true)
        protected Currency currency;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price", required = true)
        protected Time time;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price")
        protected String source;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String type;
        @XmlElement(namespace = "http://www.gnucash.org/XML/price", required = true)
        protected String value;

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
         * Gets the value of the commodity property.
         * 
         * @return
         *     possible object is
         *     {@link Commodity }
         *     
         */
        public Commodity getCommodity() {
            return commodity;
        }

        /**
         * Sets the value of the commodity property.
         * 
         * @param value
         *     allowed object is
         *     {@link Commodity }
         *     
         */
        public void setCommodity(Commodity value) {
            this.commodity = value;
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
         * Gets the value of the time property.
         * 
         * @return
         *     possible object is
         *     {@link Time }
         *     
         */
        public Time getTime() {
            return time;
        }

        /**
         * Sets the value of the time property.
         * 
         * @param value
         *     allowed object is
         *     {@link Time }
         *     
         */
        public void setTime(Time value) {
            this.time = value;
        }

        /**
         * Gets the value of the source property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSource() {
            return source;
        }

        /**
         * Sets the value of the source property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSource(String value) {
            this.source = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

}