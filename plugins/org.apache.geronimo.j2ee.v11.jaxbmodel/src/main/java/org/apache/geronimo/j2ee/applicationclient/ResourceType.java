//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.17 at 01:03:24 AM CST 
//


package org.apache.geronimo.j2ee.applicationclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.geronimo.j2ee.connector.ConnectorType;


/**
 * <p>Java class for resourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="external-rar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="internal-rar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://geronimo.apache.org/xml/ns/j2ee/connector-1.1}connector"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resourceType", propOrder = {
    "externalRar",
    "internalRar",
    "connector"
})
public class ResourceType {

    @XmlElement(name = "external-rar")
    protected String externalRar;
    @XmlElement(name = "internal-rar")
    protected String internalRar;
    @XmlElement(namespace = "http://geronimo.apache.org/xml/ns/j2ee/connector-1.1", required = true)
    protected ConnectorType connector;

    /**
     * Gets the value of the externalRar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalRar() {
        return externalRar;
    }

    /**
     * Sets the value of the externalRar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalRar(String value) {
        this.externalRar = value;
    }

    /**
     * Gets the value of the internalRar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalRar() {
        return internalRar;
    }

    /**
     * Sets the value of the internalRar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalRar(String value) {
        this.internalRar = value;
    }

    /**
     * Gets the value of the connector property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectorType }
     *     
     */
    public ConnectorType getConnector() {
        return connector;
    }

    /**
     * Sets the value of the connector property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectorType }
     *     
     */
    public void setConnector(ConnectorType value) {
        this.connector = value;
    }

}
