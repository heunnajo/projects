/**
 * (c)BOC
 */
package taxinvoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import taxinvoice.adpater.DoubleAdapter;

/**
 * 단가
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UnitPrice {

    /**
     * 물품 단가
     */
    @XmlElement(name = "UnitAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double unitAmount;

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

}
