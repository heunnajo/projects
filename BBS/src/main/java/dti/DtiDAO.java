package dti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.net.*;
import java.io.*;
import org.json.JSONException;
import org.json.JSONObject;

public class DtiDAO {
	private Connection conn;
	private ResultSet rs;
	//private PreparedStatement pstmt;글쓰기의 경우, 3개의 함수마다 충돌이 일어나지 않도록 각 함수별로 만들어준다!
	
	public DtiDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		} catch(Exception e) {
			e.printStackTrace();//오류 발생시 오류 뭔지 출력
		}
		/*실행, 확인 완료
		JSONObject obj = new JSONObject();
		obj.put("name", "foo");
		obj.put("num", "100");
		obj.put("city", "Seoul");
		System.out.print("obj"+obj);
		System.out.print("obj.toString()"+obj.toString());
		*/
	}
	
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();//오류 발생시 오류 뭔지 출력
		}
		return "";
	}
	
	public int getNext() {
		String SQL = "SELECT CONVERSATION_ID FROM xxsb_dti_main ORDER BY CONVERSATION_ID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;//현재가 첫번째 게시글인 경우
		} catch(Exception e) {
			e.printStackTrace();//오류 발생시 오류 뭔지 출력
		}
		return -1; //데이터베이스 오류
	}
	public int getCount() {
		
		String SQL = "SELECT COUNT(*) FROM xxsb_dti_main";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/*public int getAvailableCount() {
		String SQL = "SELECT COUNT(*) FROM DTI WHERE bbsAvailable = 1";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}*/
//	public int write(String conversationID, String supBuyType, String direction) {
//		String SQL = "INSERT INTO xxsb_dti_main VALUES (?, ?, ?)";
	public int write(String conversationID, String supBuyType, String direction, String dtiDate, String dtiType,
			String taxDemand, String supComRegno, String supRepName, String supComName, String supComAddr,
			String byrComRegno, String byrRepName, String byrComName, String byrComAddr, String byrEmail,
			String supAmount, String taxAmount, String totalAmount, String DttYn, String amendCode,String oriIssueId) {
		String SQL = "INSERT INTO xxsb_dti_main VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,conversationID);
			pstmt.setString(2,supBuyType);//AR:매출 AP:매입
			pstmt.setString(3,direction);//1:역발행 2:정발행
			pstmt.setString(4,dtiDate);
			pstmt.setString(5,dtiType);
			pstmt.setString(6,taxDemand);
			pstmt.setString(7,supComRegno);
			pstmt.setString(8,supRepName);
			pstmt.setString(9,supComName);
			pstmt.setString(10,supComAddr);
			pstmt.setString(11,byrComRegno);
			pstmt.setString(12,byrRepName);
			pstmt.setString(13,byrComName);
			pstmt.setString(14,byrComAddr);
			pstmt.setString(15,byrEmail);
			pstmt.setString(16,supAmount);
			pstmt.setString(17,taxAmount);
			pstmt.setString(18,totalAmount);
			pstmt.setString(19,DttYn);
			pstmt.setString(20,amendCode);
			pstmt.setString(21,oriIssueId);
			
			//json으로 예쁘게 담는다.
			//dtiInvoice.jsp 에서 입력받은 데이터
			/*
			JSONObject jsonObj = new JSONObject(); 
			jsonObj.put("conversationID", conversationID); 
			jsonObj.put("supBuyType", supBuyType); 
			jsonObj.put("direction", direction); 
			jsonObj.put("dtiDate", dtiDate); 
			jsonObj.put("dtiType", dtiType); 
			jsonObj.put("taxDemand", taxDemand); 
			jsonObj.put("supComRegno", supComRegno); 
			jsonObj.put("supRepName", supRepName); 
			jsonObj.put("supComName", supComName); 
			jsonObj.put("supComAddr", supComAddr); 
			jsonObj.put("byrComRegno", byrComRegno);
			jsonObj.put("byrRepName", byrRepName);
			jsonObj.put("byrComName", byrComName); 
			jsonObj.put("byrComAddr", byrComAddr);
			jsonObj.put("byrEmail", byrEmail);
			jsonObj.put("supAmount", supAmount);
			jsonObj.put("taxAmount", taxAmount);
			jsonObj.put("totalAmount", totalAmount);
			jsonObj.put("DttYn", DttYn);
			jsonObj.put("amendCode", amendCode);
			jsonObj.put("oriIssueId", oriIssueId);
			*/
			//테스트 데이터
			String[] arrConvId = {"11111111192208758882201504011710003"}; 
			JSONObject jsonObj = new JSONObject(); 
			jsonObj.put("MessageId", "3267cab1-3ea6-4aa6-b988-7d27d6d5ac89"); 
			jsonObj.put("Signal", "ARISSUE"); 
			jsonObj.put("RequestTime", "20150401105301"); 
			jsonObj.put("SendComRegno", "1111111119"); 
			jsonObj.put("ReceiveComRegno", "2208758882"); 
			jsonObj.put("AuthToken", "dXVCcE96ZVlXVHBPeklGcitHQlErcmROYjhEeDFXYi8rVXJmVmZPQk1LTT0K"); 
			jsonObj.put("ServiceCode", "DTI"); 
			jsonObj.put("SystemType", "OAPI"); 
			jsonObj.put("ConversationId", arrConvId); 
			jsonObj.put("SMTPEmail", ""); 
			jsonObj.put("RValue", "lSH2WInYgvm7lhfuSp6ptCZlbtY="); // 서명모듈 이용해서 발행할 경우에만 필요
			jsonObj.put("CertPassword", "Ygvm7lhfuSp6p"); // 암호화된 인증서의 비밀번호
			jsonObj.put("SystemId", ""); 
			jsonObj.put("PlatformCode", ""); // 허브업체의 구분코드
			jsonObj.put("SignedXML", ""); 
			System.out.println("jsonObj"+jsonObj);
			
			//송신측, 요청 객체
			URL url = new URL("http://demoapi.smartbill.co.kr/sb-api/request/"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("POST"); 
			conn.setUseCaches(false); 
			conn.setDoInput(true); 
			conn.setDoOutput(true); 
			conn.setConnectTimeout(10000); 
			conn.setRequestProperty("Content-Type", "application/json");//(K,V) 타입으로 RequestProperty에 저장. 
			conn.setRequestProperty("Accept", "application/json");
			
			DataOutputStream os = new DataOutputStream(conn.getOutputStream()); 
			os.write(jsonObj.toString().getBytes()); 
			os.flush(); 
			os.close();
			
			//수신측, 응답 객체
			BufferedReader br; 
			String response = ""; 
			if(null != conn){ 
				br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
				String line = null; 
				while((line = br.readLine()) != null){ 
					response += line + "\n"; 
				} 
			}
			JSONObject jsonResponse = new JSONObject(response); 
			if("30000".equalsIgnoreCase(jsonResponse.getString("ResultCode"))){ 
			 System.out.println(jsonResponse.getString("정상적으로 처리되었습니다")); 
			} 
			else{ 
			 System.out.println(jsonResponse.getString("ResultMessage")); 
			}
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();//오류 발생시 오류 뭔지 출력
		}
		return -1; //데이터베이스 오류
	}
	/*
	public static void main(String[] args) {
      JSONObject obj = new JSONObject();

      obj.put("name", "foo");
      obj.put("num", "100");
      obj.put("city", "Seoul");

      System.out.print("obj"+obj);
      System.out.print("obj.toString()"+obj.toString());
   }
   */
}
