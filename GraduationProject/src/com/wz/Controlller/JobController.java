package com.wz.Controlller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.dao.JobDao;
import com.wx.domain.Job;
import com.wx.domain.JobS;

@WebServlet(value="/jobServlet")
public class JobController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private JobDao jobDao = new JobDao();
	
	/**
	 * 做两件事 
	 * 1.实时爬取数据  
	 * 2.从数据库中取得数据
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String keyword = req.getParameter("keyword");
		Map<String,Integer> result = Spide.get51Data(keyword);
		req.setAttribute("keyword", keyword);
		req.setAttribute("one", result.get("2千以下"));
		req.setAttribute("two", result.get("2-3千"));
		req.setAttribute("three", result.get("3-4.5千"));
		req.setAttribute("four", result.get("4.5-6千"));
		req.setAttribute("five", result.get("6-8千"));
		req.setAttribute("six", result.get("0.8-1万"));
		req.setAttribute("seven", result.get("1-1.5万"));
		req.setAttribute("eight", result.get("1.5-2万"));
		req.setAttribute("nine", result.get("2-3万"));
		req.setAttribute("ten", result.get("3-4万"));
		req.setAttribute("eleven", result.get("4-5万"));
		req.setAttribute("twelve", result.get("5万以上"));
		
		Job job = new Job();
		JobS jobS = new JobS();
		List<Job> jobList = new ArrayList<Job>();
		job.setPosition(keyword);
		String compensation = req.getParameter("compensation");		
		if(compensation != null && !"".equals(compensation)){
			job.setCompensation(compensation);
		}
		String info = req.getParameter("info");		
		if(info != null && !"".equals(info)){
			job.setInfo(info);
		}		
		String wage = req.getParameter("wages");		
		if(info != null && !"".equals(wage)){
			job.setWages(wage);
		}	
		jobList = jobDao.queryBypage(job);
		jobS.setList(jobList);
		req.setAttribute("jobS", jobS);
		req.getRequestDispatcher("chartsJsp.jsp").forward(req,resp);//转发
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
