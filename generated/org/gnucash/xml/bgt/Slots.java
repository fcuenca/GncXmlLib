//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:04:34 PM EST 
//


package org.gnucash.xml.bgt;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;group ref="{http://www.gnucash.org/XML/gnc}KvpSlot" maxOccurs="unbounded"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "kvpSlot"
})
@XmlRootElement(name = "slots")
public class Slots {

    @XmlElement(name = "slot", required = true)
    protected List<org.gnucash.xml.lot.Slots.Slot> kvpSlot;

    /**
     * Gets the value of the kvpSlot property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kvpSlot property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKvpSlot().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.gnucash.xml.lot.Slots.Slot }
     * 
     * 
     */
    public List<org.gnucash.xml.lot.Slots.Slot> getKvpSlot() {
        if (kvpSlot == null) {
            kvpSlot = new ArrayList<org.gnucash.xml.lot.Slots.Slot>();
        }
        return this.kvpSlot;
    }

}
