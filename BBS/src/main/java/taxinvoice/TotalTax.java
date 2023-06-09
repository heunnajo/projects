/*
 * (c)BOC
 */
package taxinvoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import taxinvoice.adpater.DoubleAdapter;

/**
 * 세액
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalTax {

    /**
     * 물품세액
     */
    @XmlElement(name = "CalculatedAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double caculatedAmount;

    public Double getCaculatedAmount() {
        return caculatedAmount;
    }

    public void setCaculatedAmount(Double caculatedAmount) {
        this.caculatedAmount = caculatedAmount;
    }

}
