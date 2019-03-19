package com.wx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wx.domain.Job;
import com.wz.util.JDBCUtil;

public class JobDao {
	/**
	 * 分页查询工作表
	 * 
	 * @return
	 */
	public List<Job> queryBypage(Job conJob) {
		Connection conn = null;
		StringBuilder sql = new StringBuilder(
				"select * from job3 where position like ?");
		if (conJob.getCompensation() != null
				&& !"".equals(conJob.getCompensation()))
			sql.append(" and compensation like ?");
		if (conJob.getInfo() != null && !"".equals(conJob.getInfo()))
			sql.append(" and Relevant_information like ?");
		if (conJob.getWages() !=null && !"".equals(conJob.getWages()))
			sql.append(" and wages like ?");
		PreparedStatement pstmt;
		List<Job> jobs = new ArrayList<Job>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + conJob.getPosition() + "%");
			if (conJob.getCompensation() != null && !"".equals(conJob.getCompensation())) {//第二个条件不为空
				pstmt.setString(2, "%" + conJob.getCompensation() + "%");
				if (conJob.getInfo() != null && !"".equals(conJob.getInfo())) {
					pstmt.setString(3, "%" + conJob.getInfo() + "%");
					if(conJob.getWages() !=null && !"".equals(conJob.getWages())){
						pstmt.setString(4, "%" + conJob.getWages() + "%");
					}
				}
			}else if(conJob.getInfo() != null && !"".equals(conJob.getInfo())){
				pstmt.setString(2, "%" + conJob.getInfo() + "%");
				if(conJob.getWages() !=null && !"".equals(conJob.getWages())){
					pstmt.setString(3, "%" + conJob.getWages() + "%");
				}
			}else if(conJob.getWages() !=null && !"".equals(conJob.getWages())){
				pstmt.setString(2, "%" + conJob.getWages() + "%");
			}
			
			ResultSet rs = pstmt.executeQuery();// 执行查询
			// System.out.println("============================");
			while (rs.next()) {
				Job job = new Job();
				job.setPosition(rs.getString(1));
				job.setCompany(rs.getString(2));
				job.setCompensation(rs.getString(3));
				job.setWages(rs.getString(4));
				//job.setDate(rs.getString(5));
				job.setInfo(rs.getString(6));
				job.setOrder(rs.getInt(7));
				jobs.add(job);
			}
			// System.out.println("============================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}

	public static void main(String[] args) {
		/*
		 * Job job = new Job(); job.setPosition("经理");
		 * job.setCompensation("上海"); job.setPages(0); new
		 * JobDao().queryBypage(job);
		 */
	}

}
