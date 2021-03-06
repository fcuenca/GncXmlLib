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
import org.gnucash.xml.sx.DeferredInstance;
import org.gnucash.xml.sx.End;
import org.gnucash.xml.sx.Id;
import org.gnucash.xml.sx.Last;
import org.gnucash.xml.sx.Schedule;
import org.gnucash.xml.sx.Slots;
import org.gnucash.xml.sx.Start;
import org.gnucash.xml.sx.TemplAcct;


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
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}id"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}name"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}enabled"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}autoCreate"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}autoCreateNotify"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}advanceCreateDays"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}advanceRemindDays"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}instanceCount"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}start"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}last" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;sequence>
 *             &lt;element ref="{http://www.gnucash.org/XML/sx}num-occur"/>
 *             &lt;element ref="{http://www.gnucash.org/XML/sx}rem-occur"/>
 *           &lt;/sequence>
 *           &lt;element ref="{http://www.gnucash.org/XML/sx}end"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}templ-acct"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}schedule"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}deferredInstance" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.gnucash.org/XML/sx}slots" minOccurs="0"/>
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
    "name",
    "enabled",
    "autoCreate",
    "autoCreateNotify",
    "advanceCreateDays",
    "advanceRemindDays",
    "instanceCount",
    "start",
    "last",
    "numOccur",
    "remOccur",
    "end",
    "templAcct",
    "schedule",
    "deferredInstance",
    "slots"
})
@XmlRootElement(name = "schedxaction")
public class Schedxaction {

    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    protected Id id;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String enabled;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String autoCreate;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String autoCreateNotify;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected int advanceCreateDays;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected int advanceRemindDays;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected int instanceCount;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    protected Start start;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected Last last;
    @XmlElement(name = "num-occur", namespace = "http://www.gnucash.org/XML/sx")
    protected Integer numOccur;
    @XmlElement(name = "rem-occur", namespace = "http://www.gnucash.org/XML/sx")
    protected Integer remOccur;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected End end;
    @XmlElement(name = "templ-acct", namespace = "http://www.gnucash.org/XML/sx", required = true)
    protected TemplAcct templAcct;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx", required = true)
    protected Schedule schedule;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected List<DeferredInstance> deferredInstance;
    @XmlElement(namespace = "http://www.gnucash.org/XML/sx")
    protected Slots slots;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnabled(String value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the autoCreate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCreate() {
        return autoCreate;
    }

    /**
     * Sets the value of the autoCreate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCreate(String value) {
        this.autoCreate = value;
    }

    /**
     * Gets the value of the autoCreateNotify property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCreateNotify() {
        return autoCreateNotify;
    }

    /**
     * Sets the value of the autoCreateNotify property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCreateNotify(String value) {
        this.autoCreateNotify = value;
    }

    /**
     * Gets the value of the advanceCreateDays property.
     * 
     */
    public int getAdvanceCreateDays() {
        return advanceCreateDays;
    }

    /**
     * Sets the value of the advanceCreateDays property.
     * 
     */
    public void setAdvanceCreateDays(int value) {
        this.advanceCreateDays = value;
    }

    /**
     * Gets the value of the advanceRemindDays property.
     * 
     */
    public int getAdvanceRemindDays() {
        return advanceRemindDays;
    }

    /**
     * Sets the value of the advanceRemindDays property.
     * 
     */
    public void setAdvanceRemindDays(int value) {
        this.advanceRemindDays = value;
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
     * Gets the value of the numOccur property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumOccur() {
        return numOccur;
    }

    /**
     * Sets the value of the numOccur property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumOccur(Integer value) {
        this.numOccur = value;
    }

    /**
     * Gets the value of the remOccur property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRemOccur() {
        return remOccur;
    }

    /**
     * Sets the value of the remOccur property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRemOccur(Integer value) {
        this.remOccur = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link End }
     *     
     */
    public End getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link End }
     *     
     */
    public void setEnd(End value) {
        this.end = value;
    }

    /**
     * Gets the value of the templAcct property.
     * 
     * @return
     *     possible object is
     *     {@link TemplAcct }
     *     
     */
    public TemplAcct getTemplAcct() {
        return templAcct;
    }

    /**
     * Sets the value of the templAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplAcct }
     *     
     */
    public void setTemplAcct(TemplAcct value) {
        this.templAcct = value;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setSchedule(Schedule value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the deferredInstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deferredInstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeferredInstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeferredInstance }
     * 
     * 
     */
    public List<DeferredInstance> getDeferredInstance() {
        if (deferredInstance == null) {
            deferredInstance = new ArrayList<DeferredInstance>();
        }
        return this.deferredInstance;
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
