import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//reference : https://velog.io/@duck-ach/22.-XML%EB%AC%B8%EC%84%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0-JAVA
//XML Implementation code
public class XMLMaker {

	public static void main(String[] args) {
		try {
			
			// Document 생성(문서 생성)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlStandalone(true);  // standalone="no" 제거
			
			// Document에 products 태그 추가
			Element TaxInvoice = document.createElement("TaxInvoice");//최상위 element
			document.appendChild(TaxInvoice);
			
			List<String> product1 = Arrays.asList("100", "새우깡", "1500");
			List<String> product2 = Arrays.asList("101", "양파링", "2000");
			List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
			
			List<List<String>> list = Arrays.asList(product1, product2, product3);
			
			for(List<String> line : list) {
				// 태그 생성
				Element product = document.createElement("product");//하위 element
				
                Element number = document.createElement("number");
				number.setTextContent(line.get(0)); // 0번째 배열 애들을 다 데려옴
				Element name = document.createElement("name");
				name.setTextContent(line.get(1)); // 1번째 배열 애들을 다 데려옴
				Element price = document.createElement("price");
				price.setTextContent(line.get(2)); // 2번째 배열 애들을 다 데려옴
				// 태그 배치
				TaxInvoice.appendChild(product);  // 변경
				product.appendChild(number);
				product.appendChild(name);
				product.appendChild(price);
			}
			
			// XML 생성
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");  // setOutputProperty  : 출력형태 만들 때 사용
			transformer.setOutputProperty("indent", "yes");  // indent  : 들여쓰기
			transformer.setOutputProperty("doctype-public", "yes");  // document.setXmlStandalone(true); 하면 개행이 안 되기 때문에 추가
			
			Source source = new DOMSource(document);
			File file = new File("C:\\storage", "product.xml");//주소, 파일명 지정
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}