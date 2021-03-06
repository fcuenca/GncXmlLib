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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.gnucash.xml.recurrence.Start;


/**
 * <p>Java class for RecurrenceContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecurrenceContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.gnucash.org/XML/recurrence}mult"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/recurrence}period_type"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/recurrence}start"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/recurrence}weekend_adj" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="1.0.0"/>
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
@XmlType(name = "RecurrenceContent", propOrder = {
    "mult",
    "periodType",
    "start",
    "weekendAdj"
})
public class RecurrenceContent {

    @XmlElement(namespace = "http://www.gnucash.org/XML/recurrence")
    protected int mult;
    @XmlElement(name = "period_type", namespace = "http://www.gnucash.org/XML/recurrence", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String periodType;
    @XmlElement(namespace = "http://www.gnucash.org/XML/recurrence", required = true)
    protected Start start;
    @XmlElement(name = "weekend_adj", namespace = "http://www.gnucash.org/XML/recurrence")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String weekendAdj;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the mult property.
     * 
     */
    public int getMult() {
        return mult;
    }

    /**
     * Sets the value of the mult property.
     * 
     */
    public void setMult(int value) {
        this.mult = value;
    }

    /**
     * Gets the value of the periodType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * Sets the value of the periodType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodType(String value) {
        this.periodType = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link Start }
     *     
     */
    public Start getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link Start }
     *     
     */
    public void setStart(Start value) {
        this.start = value;
    }

    /**
     * Gets the value of the weekendAdj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeekendAdj() {
        return weekendAdj;
    }

    /**
     * Sets the value of the weekendAdj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeekendAdj(String value) {
        this.weekendAdj = value;
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
