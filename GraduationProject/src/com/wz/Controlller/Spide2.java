package com.wz.Controlller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wx.domain.Job;
import com.wz.util.JDBCUtil;;

public class Spide2 {

	static String url = "https://search.51job.com/list/000000,000000,0000,00,9,99,%2520,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
	static int i = 0;

	// 用来打开网页链接
	public static String getHtml(String urlStr, String encoding) {
		URL url = null;
		StringBuffer html = new StringBuffer();
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			// IO流,通道
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "GBK"));
			// StringBuffer html = new StringBuffer();
			String tmp = "";
			while ((tmp = reader.readLine()) != null) {
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

	public static void message(String url) {
		String html = getHtml(url, "GBK");
		Document doc =null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		// 获取下一页衔接
		Elements pageList = doc.getElementsByClass("bk");
		String nextPageUrl = pageList.get(1).getAllElements().attr("href");
		HashMap<Integer, Job> hMap = new HashMap<Integer, Job>();
		// Elements els =document.getElementsByClass("e1");
		for (int j = 1; j < 51; j++) {//通过分析网页可以知道每一页只能展示50条职位信息
			Job job = new Job();
			Document document = Jsoup.parse(html.toString());//通过document类获取整个HTML
			Elements els1 = document.getElementsByClass("t1");//获取t1的节点
			String str1 = els1.get(j).text();
			job.setPosition(str1);
			String secondPageUrl = els1.get(j).getElementsByTag("a").attr("href");//获取二级界面的url地址
			Document doc1 =null;
			try {
				doc1 = Jsoup.connect(secondPageUrl).get();
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
			String html1 = getHtml(secondPageUrl, "GBK");
			Document document1 = Jsoup.parse(html1.toString());
			Elements els1s = document1.getElementsByClass("sp4");
			String str11 = els1s.text();
			job.setInfo(str11);
			/*Element els12 = document1.getElementsByClass("sp4").get(1);
			String str12 = els12.text();
			if(str12!="大专"&&str12!="本科"&&str12!="中专"&&str12!="高中"&&str12!="初中及以下")
				str12="无要求";
			if(str12.equals("大专")||str12.equals("本科")||str12.equals("中专")||str12.equals("高中")||str12.equals("初中及以下")){
				job.setEducation(str12);
			}
			else{
				str12 = "无要求";
				job.setEducation(str12);
			}*/
			Elements els2 = document.getElementsByClass("t2");
			String str2 = els2.get(j).text();
			job.setCompany(str2);
			Elements els3 = document.getElementsByClass("t3");
			String str3 = els3.get(j).text();
			job.setCompensation(str3);
			Elements els4 = document.getElementsByClass("t4");
			String str4 = els4.get(j).text();
			job.setWages(str4);
			Elements els5 = document.getElementsByClass("t5");
			String str5 = els5.get(j).text();
			job.setDate(str5);
			hMap.put(j, job);
		}
		Set<Integer> keys = hMap.keySet();

		for (Integer key : keys) {
			Job value = hMap.get(key);
			Connection conn = null;
			try {
				PreparedStatement ps = null;
				conn = JDBCUtil.getConnection();
				String sql = "insert into jobb(position,company,compensation,wages,date,Relevant_information)values(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, value.getPosition());//设置工作名称类的值
				ps.setString(2, value.getCompany());//设置公司名称类的值
				ps.setString(3, value.getCompensation());
				ps.setString(4, value.getWages());
				ps.setString(5, value.getDate());
				ps.setString(6, value.getInfo());
				//ps.setString(7, value.getEducation());
				ps.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库访问失败");
			}
			System.out.println( value.toString());
		}
		if (!"".equals(nextPageUrl)) {
			message(nextPageUrl);
		} else {
			System.out.println("浏览结束");
		}
	}

	public static void main(String[] args) {
		message(url);
	}

}
