//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.sx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}last" minOccurs="0"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}rem-occur"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}instanceCount"/>
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
    "last",
    "remOccur",
    "instanceCount"
})
@XmlRootElement(name = "deferredInstance")
public class DeferredInstance {

    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected Last last;
    @XmlElement(name = "rem-occur", namespace = "http://www.gnucash.org/XML/sx")
    protected int remOccur;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected int instanceCount;

    /**
     * Gets the value of the last property.
     * 
     * @return
     *     possible object is
     *     {@link Last }
     *     
     */
    public Last getLast() {
        return last;
    }

    /**
     * Sets the value of the last property.
     * 
     * @param value
     *     allowed object is
     *     {@link Last }
     *     
     */
    public void setLast(Last value) {
        this.last = value;
    }

    /**
     * Gets the value of the remOccur property.
     * 
     */
    public int getRemOccur() {
        return remOccur;
    }

    /**
     * Sets the value of the remOccur property.
     * 
     */
    public void setRemOccur(int value) {
        this.remOccur = value;
    }

    /**
     * Gets the value of the instanceCount property.
     * 
     */
    public int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Sets the value of the instanceCount property.
     * 
     */
    public void setInstanceCount(int value) {
        this.instanceCount = value;
    }

}