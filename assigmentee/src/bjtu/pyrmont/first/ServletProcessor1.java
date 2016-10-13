package bjtu.pyrmont.first;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.IOException;
 
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import Servlet.LoginServlet;
import bjtu.pyrmont.Constants;
import bjtu.pyrmont.Request;
import bjtu.pyrmont.Response;
import bjtu.pyrmont.XmlReader;
 
public class ServletProcessor1 {
 
 public void process(Request request, Response response) {
 
	 XmlReader xr = new XmlReader();
	 xr.xmlreader();
	 
	 String uri = request.getUri();
	 String url_parttern = uri.substring(uri.lastIndexOf("/") + 1);
	 String servletName = null;
	 String servletClass = null;
     
	 
  
	 if ((xr.url_pattern[0]).equals("/"+url_parttern)||(xr.url_pattern[1]).equals("/"+url_parttern)) {
		 
		 if((xr.url_pattern[0]).equals("/"+url_parttern)) {
		 servletName = xr.servletName[0];
		 servletClass = xr.servlet_class[0];
		 }
		 
		 if((xr.url_pattern[1]).equals("/"+url_parttern)) {
			 servletName = xr.servletName[1];
			 servletClass = xr.servlet_class[1];
			 }
		// System.out.println("ok");
		 
	 //������������ڴ�ָ��JAR�ļ���Ŀ¼������
	 URLClassLoader loader = null;
	 try {
		 URLStreamHandler streamHandler = null;
		 //�����������
		 loader = new URLClassLoader(new URL[]{new URL(null, "file:" + Constants.WEB_SERVLET_ROOT, streamHandler)});
	 } catch (IOException e) {
		 System.out.println(e.toString());
	 }
  
	 Class<?> myClass = null;
	 try {
		 //���ض�Ӧ��servlet��
		 myClass = loader.loadClass(servletClass);
	 } catch (ClassNotFoundException e) {
		 System.out.println(e.toString());
	 }
 
	 Servlet servlet = null;
 
	 try {
		 //����servletʵ��
		 servlet = (Servlet) myClass.newInstance();
		 //ִ��ervlet��service����
		 servlet.service((ServletRequest) request,(ServletResponse) response);
	 } catch (Exception e) {
		 System.out.println(e.toString());
	 } catch (Throwable e) {
		 System.out.println(e.toString());
	 }
	 
	 }
 }
}