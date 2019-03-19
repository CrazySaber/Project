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
		//打开链接
		//System.out.println(html.toString());
		
		for(int i = 0;i<els.size();i++){
			System.out.println(els.get(i).text());
		}
	}*/
	
	/*
	 * 抓取数据解析
	 */
	
	public static Map<String,Integer> get51Data(String keyword) throws UnsupportedEncodingException{
		String uri = "https://search.51job.com/list/000000,000000,0000,00,9,${page},${keyword},2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=21&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		//通过分析网址的格式，发现如果输入的是中文，就必学经过两次URL转换。
		String bkeyword = URLEncoder.encode(URLEncoder.encode(keyword, "UTF-8"), "UTF-8");
		uri = uri.replace("${keyword}", bkeyword);
		Map<String, Integer> results = getRange(uri);//Map函数键值对，将uri的值与序号一一对应
		return results;
	}
	
	
	
	//抓取指定的URL的HTML
	public static Map<String,Integer> getRange(String url){
		Map<String , Integer> results = new LinkedHashMap<String,Integer>();
		for(int i = 1;i < 13;i++){
			String tmpUrl = "";
			if(i>9){
				tmpUrl = url.replace("${page}",""+ i);
			}else{
				tmpUrl = url.replace("${page}", "0"+i);
			}
			String html = getHtml(tmpUrl, "GBK");//将网址编码格式改为GBK编码
			Document document = Jsoup.parse(html.toString());//使用jsoup对网址进行解析，获得document对象
			Elements els =document.getElementsByClass("rt");//通过elements对象获取RT节点
			//使用正则表达式只取数值
			String str = els.get(0).text();//通过分析网页知道应该取第0个值
			Pattern p = Pattern.compile("[^0-9]");//通过pattern类，对给定的正则表达式进行编译并将其赋予els
			Matcher m = p.matcher(str);//它依据Pattern对象做为匹配模式对字符串展开匹配检查
			//将所有不为数字的替换为空
			//System.out.println(m.replaceAll("").trim());
			String num = m.replaceAll("").trim();
			String type = document.getElementsByClass("dw_c_orange").get(1).text();
			//System.out.println(type+":"+num+":条岗位");
			results.put(type, Integer.valueOf(num));//将类型和值进行一一对应
		}
		return results;
	}
	
	
	
	public static String getHtml(String urlStr,String encoding){
		URL url = null;
		StringBuffer html = new StringBuffer();
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			//IO流,通道
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
