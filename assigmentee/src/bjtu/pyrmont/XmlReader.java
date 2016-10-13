package bjtu.pyrmont;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

	public static String[] servletName = new String[2];
	public static String[] url_pattern = new String[2];
	public static String[] servlet_class = new String[2];
	
	public void xmlreader() {
		// TODO Auto-generated method stub
		int n=0;int m=0;
		Element element = null;
		  // ����ʹ�þ���·��
		  File f = new File("web.xml");

		  // documentBuilderΪ������ֱ��ʵ����(��XML�ļ�ת��ΪDOM�ļ�)
		  DocumentBuilder db = null;
		  DocumentBuilderFactory dbf = null;
		  try {
		   // ����documentBuilderFactory����
		   dbf = DocumentBuilderFactory.newInstance();
		   // ����db������documentBuilderFatory�����÷���documentBuildr����
		   db = dbf.newDocumentBuilder();

		   // �õ�һ��DOM�����ظ�document����
		   Document dt = db.parse(f);
		   // �õ�һ��elment��Ԫ��
		   element = dt.getDocumentElement();
		
		   NodeList childNodes = element.getChildNodes();

		   // ������Щ�ӽڵ�
		   for (int i = 0; i < childNodes.getLength(); i++) {
		    // ���ÿ����Ӧλ��i�Ľ��
		    Node node1 = childNodes.item(i);
		    if ("servlet".equals(node1.getNodeName())) {
		     NodeList nodeDetail = node1.getChildNodes();

		     for (int j = 0; j < nodeDetail.getLength(); j++) {
		      // ���<Accounts>Ԫ��ÿһ���ڵ�
		      Node detail = nodeDetail.item(j);
		      if ("servlet-name".equals(detail.getNodeName())) // ���code
		    	  servletName[n] = detail.getTextContent();
		      else if ("servlet-class".equals(detail.getNodeName())) {// ���pass
		    	  servlet_class[n] = detail.getTextContent();
		      }
		     }
		     n++;
		    }
		    
		    if ("servlet-mapping".equals(node1.getNodeName())) {
		    	 NodeList nodeDetail = node1.getChildNodes();
		    	 
		    	 for (int j = 0; j < nodeDetail.getLength(); j++) {
				      // ���<Accounts>Ԫ��ÿһ���ڵ�
				      Node detail = nodeDetail.item(j);
				      if ("servlet-name".equals(detail.getNodeName())) { // ���code
				    	  servletName[m] =  detail.getTextContent();

				      }
				      else if ("url-pattern".equals(detail.getNodeName())) {// ���pass
				    	  url_pattern[m] = detail.getTextContent();

				      }
		    	 }
		    	 
		    	 m++;
		    }

		   }
		  }

		  catch (Exception e) {
		   e.printStackTrace();
		  }

	}

}
