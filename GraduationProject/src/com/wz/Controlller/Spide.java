package com.wz.Controlller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spide {
	/*public static void main(String[] args) throws IOException{
//		String uri = "https://search.51job.com/list/000000,000000,0000,00,9,${page},${keyword},2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=21&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
//		uri = uri.replace("${keyword}", "java");
		//List<HashMap<String, String >> resultlist = analyzeHtml(uri,"gbk");
		//System.out.println(resultlist);
//		Map<String,Integer> results = getRange(uri);
//		System.out.println(results);
		//URL url = new URL(uri);
		//������
		//System.out.println(html.toString());
		
		for(int i = 0;i<els.size();i++){
			System.out.println(els.get(i).text());
		}
	}*/
	
	/*
	 * ץȡ���ݽ���
	 */
	
	public static Map<String,Integer> get51Data(String keyword) throws UnsupportedEncodingException{
		String uri = "https://search.51job.com/list/000000,000000,0000,00,9,${page},${keyword},2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=21&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		//ͨ��������ַ�ĸ�ʽ�������������������ģ��ͱ�ѧ��������URLת����
		String bkeyword = URLEncoder.encode(URLEncoder.encode(keyword, "UTF-8"), "UTF-8");
		uri = uri.replace("${keyword}", bkeyword);
		Map<String, Integer> results = getRange(uri);//Map������ֵ�ԣ���uri��ֵ�����һһ��Ӧ
		return results;
	}
	
	
	
	//ץȡָ����URL��HTML
	public static Map<String,Integer> getRange(String url){
		Map<String , Integer> results = new LinkedHashMap<String,Integer>();
		for(int i = 1;i < 13;i++){
			String tmpUrl = "";
			if(i>9){
				tmpUrl = url.replace("${page}",""+ i);
			}else{
				tmpUrl = url.replace("${page}", "0"+i);
			}
			String html = getHtml(tmpUrl, "GBK");//����ַ�����ʽ��ΪGBK����
			Document document = Jsoup.parse(html.toString());//ʹ��jsoup����ַ���н��������document����
			Elements els =document.getElementsByClass("rt");//ͨ��elements�����ȡRT�ڵ�
			//ʹ��������ʽֻȡ��ֵ
			String str = els.get(0).text();//ͨ��������ҳ֪��Ӧ��ȡ��0��ֵ
			Pattern p = Pattern.compile("[^0-9]");//ͨ��pattern�࣬�Ը�����������ʽ���б��벢���丳��els
			Matcher m = p.matcher(str);//������Pattern������Ϊƥ��ģʽ���ַ���չ��ƥ����
			//�����в�Ϊ���ֵ��滻Ϊ��
			//System.out.println(m.replaceAll("").trim());
			String num = m.replaceAll("").trim();
			String type = document.getElementsByClass("dw_c_orange").get(1).text();
			//System.out.println(type+":"+num+":����λ");
			results.put(type, Integer.valueOf(num));//�����ͺ�ֵ����һһ��Ӧ
		}
		return results;
	}
	
	
	
	public static String getHtml(String urlStr,String encoding){
		URL url = null;
		StringBuffer html = new StringBuffer();
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			//IO��,ͨ��
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
			//StringBuffer html = new StringBuffer();
			String tmp = "";
			while((tmp = reader.readLine())!=null ){
				html.append(tmp);
			}
			reader.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html.toString();
	}		  	
}
