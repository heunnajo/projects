/**
 * (c)BOC
 */
package taxinvoice;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * KEC표준전자(세금)계산서
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlRootElement(name = "TaxInvoice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"exchangedDocument", "taxInvoiceDocument",
	"taxInvoiceSettlement", "taxInvoiceTradeLineItem"})
//"etcNo1", "etcNo2", "etcNo3", "etcNo4"})
public class TaxInvoice implements Serializable {

	/**
	 * 관리정보
	 */
	@XmlElement(name = "ExchangedDocument")
	private ExchangedDocument exchangedDocument;

	/**
	 * 기본정보
	 */
	@XmlElement(name = "TaxInvoiceDocument")
	private TaxInvoiceDocument taxInvoiceDocument;

	/**
	 * 계산서 정보
	 */
	@XmlElement(name = "TaxInvoiceTradeSettlement")
	private TaxInvoiceTradeSettlement taxInvoiceSettlement;

	/**
	 * 상품정보
	 */
	@XmlElement(name = "TaxInvoiceTradeLineItem")
	private List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItem;

	/**
	 * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
	 */
	//@XmlElement(name = "EtcNo1")
	//private String etcNo1;
	/**
	 * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
	 */
	//@XmlElement(name = "EtcNo2")
	//private String etcNo2;
	/**
	 * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
	 */
	//@XmlElement(name = "EtcNo3")
	//private String etcNo3;
	/**
	 * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
	 */
	//@XmlElement(name = "EtcNo4")
	//private String etcNo4;
	public ExchangedDocument getExchangedDocument() {
		return exchangedDocument;
	}

	public void setExchangedDocument(ExchangedDocument exchangedDocument) {
		this.exchangedDocument = exchangedDocument;
	}

	public TaxInvoiceDocument getTaxInvoiceDocument() {
		return taxInvoiceDocument;
	}

	public void setTaxInvoiceDocument(TaxInvoiceDocument taxInvoiceDocument) {
		this.taxInvoiceDocument = taxInvoiceDocument;
	}

	public TaxInvoiceTradeSettlement getTaxInvoiceSettlement() {
		return taxInvoiceSettlement;
	}

	public void setTaxInvoiceSettlement(TaxInvoiceTradeSettlement taxInvoiceSettlement) {
		this.taxInvoiceSettlement = taxInvoiceSettlement;
	}

	public List<TaxInvoiceTradeLineItem> getTaxInvoiceTradeLineItem() {
		return taxInvoiceTradeLineItem;
	}

	public void setTaxInvoiceTradeLineItem(List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItem) {
		this.taxInvoiceTradeLineItem = taxInvoiceTradeLineItem;
	}

	public static void main(String[] args) throws Exception {
		
		TaxInvoice ti = new TaxInvoice();
		JAXBContext jaxbContext = null;
		Marshaller jaxbMarshaller = null;

		StringWriter writer = new StringWriter();

		jaxbContext = JAXBContext.newInstance(TaxInvoice.class);
		jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		//jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0 http://www.kec.or.kr/standard/Tax/TaxInvoiceSchemaModule_1.0.xsd");

		jaxbMarshaller.marshal(ti.createForText(), writer);
		
		System.out.println(writer.toString());
		
	}
	
	
	private TaxInvoice createForText() throws Exception {

        TaxInvoice etax = new TaxInvoice();

        // EXCHANGE DOCUMENT
        ExchangedDocument exchangedDocument = new ExchangedDocument();
        exchangedDocument.setIssueDateTime(Calendar.getInstance().getTime());

        etax.setExchangedDocument(exchangedDocument);
		// end of EXCHANGE DOCUMENT

        // INVOICE SETTLEMENT
        TaxInvoiceTradeSettlement taxInvoiceTradeSettlement = new TaxInvoiceTradeSettlement();

        InvoicerParty supplyer = new InvoicerParty();

        supplyer.setId("1111111119");
        supplyer.setTypeCode("서비스");
        supplyer.setNameText("비지니스온(스마트빌)test");
        supplyer.setClassfigicationCode("서비스");

        SpecifiedPerson specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText("대표자");
        supplyer.setSpecifiedPerson(specifiedPerson);

        DefinedContact definedContract = new DefinedContact();
        definedContract.setPersonNameText("비지니스온(스마트빌)test");
        definedContract.setTelephoneCommuncation("031-123-1234");
        definedContract.setUriCommunication("jh.seo@businesson.co.kr");
        supplyer.setDefinedContact(definedContract);

        SpecifiedAddress specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText("서울시 서초 어쩌구");
        supplyer.setSpecifiedAddress(specifiedAddress);

        taxInvoiceTradeSettlement.setInvoicerParty(supplyer);

        SpecifiedOrganization specifiedOrganization = new SpecifiedOrganization();

        specifiedOrganization.setTaxRegistrationId(null);

        supplyer.setSpecifiedOrganization(null);

        InvoiceeParty buyer = new InvoiceeParty();
        buyer.setId("3118503753");
        buyer.setTypeCode("발전설비유지외");
        buyer.setNameText("당진사업처");
        buyer.setClassfigicationCode("제조,건설,서비스");

        SpecifiedOrganization specifiedOrganization2 = new SpecifiedOrganization();
        specifiedOrganization2.setBusinessTypeCode(null);
        
        buyer.setSpecifiedOrganization(specifiedOrganization2);
        

        specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText("최외근");
        buyer.setSpecifiedPerson(specifiedPerson);

        definedContract = new DefinedContact();
        definedContract.setDepartmentNameText("기획처/정보화전략팀");
        definedContract.setPersonNameText("김철수");
        definedContract.setTelephoneCommuncation("111-222-4444");
        definedContract.setUriCommunication("korea@korea.net");

        //buyer.setPrimaryDefinedContract(definedContract);
        specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText("서울시 서초 어쩌구");
        buyer.setSpecifiedAddress(specifiedAddress);

        taxInvoiceTradeSettlement.setInvoiceeParty(buyer);

        SpecifiedMonetarySummation specifiedMonetarySummation = new SpecifiedMonetarySummation();
        specifiedMonetarySummation.setChargeTotalAmount(1000.0);
        specifiedMonetarySummation.setGrandTotalAmount(1100.0);
        specifiedMonetarySummation.setTaxTotalAmount(100.0);

        taxInvoiceTradeSettlement.setSpecifiedMonetarySummation(specifiedMonetarySummation);

        SpecifiedPaymentMeans specifiedPaymentMeans = new SpecifiedPaymentMeans();
        specifiedPaymentMeans.setTypeCode("10");
        specifiedPaymentMeans.setPaidAmount(1100.0);

        List<SpecifiedPaymentMeans> specifiedPaymentMeanses = new ArrayList<SpecifiedPaymentMeans>();

        specifiedPaymentMeanses.add(specifiedPaymentMeans);

        taxInvoiceTradeSettlement.setSpecifiedPaymentMeans(specifiedPaymentMeanses);

        etax.setTaxInvoiceSettlement(taxInvoiceTradeSettlement);

        // end of INVOICE SETTLEMENT
        // ITEM
        TaxInvoiceTradeLineItem taxInvoiceTradeLineItem = new TaxInvoiceTradeLineItem();

        taxInvoiceTradeLineItem.setSequenceNumeric(1);
        taxInvoiceTradeLineItem.setInvoiceAmount(1000.0);

        TotalTax totalTax = new TotalTax();
        totalTax.setCaculatedAmount(100.0);

        UnitPrice unitPrice = new UnitPrice();
        unitPrice.setUnitAmount(0.0);

        taxInvoiceTradeLineItem.setTotalTax(totalTax);
        taxInvoiceTradeLineItem.setUnitPrice(unitPrice);

        TaxInvoiceTradeLineItem taxInvoiceTradeLineItem2 = new TaxInvoiceTradeLineItem();

        taxInvoiceTradeLineItem2.setSequenceNumeric(2);
        taxInvoiceTradeLineItem2.setInvoiceAmount(0.0);

        TotalTax totalTax2 = new TotalTax();
        totalTax2.setCaculatedAmount(0.0);

        UnitPrice unitPrice2 = new UnitPrice();
        unitPrice2.setUnitAmount(0.0);

        taxInvoiceTradeLineItem2.setTotalTax(totalTax2);
        taxInvoiceTradeLineItem2.setUnitPrice(unitPrice2);

        List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItems = new ArrayList<TaxInvoiceTradeLineItem>();

        taxInvoiceTradeLineItems.add(taxInvoiceTradeLineItem);
        taxInvoiceTradeLineItems.add(taxInvoiceTradeLineItem2);

        etax.setTaxInvoiceTradeLineItem(taxInvoiceTradeLineItems);
		// END OF ITEM

        // INVOICE DOCUMENT
        TaxInvoiceDocument taxInvoiceDocument = new TaxInvoiceDocument();
        taxInvoiceDocument.setIssueId("201402284100000801x04a5x");
        taxInvoiceDocument.setTypeCode("0101");
//        taxInvoiceDocument.setDescriptionText("호이호이");
        //taxInvoiceDocument.setAmendmentStatusCode("01");
        taxInvoiceDocument.setPurposeCode("02");
        taxInvoiceDocument.setIssueDateTime(Calendar.getInstance().getTime());
        //taxInvoiceDocument.setOriginalIssueId("201402284100000801x04a5w");

        etax.setTaxInvoiceDocument(taxInvoiceDocument);
        // end of INVOICE DOCUMENT*/

        return etax;

    }

}
