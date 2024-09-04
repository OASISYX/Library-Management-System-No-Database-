package com.book.dao;

import java.util.ArrayList;

import java.util.List;


import com.book.pojo.User;
import com.book.util.CommonUtil;



public class BorrowerDAO {
	// Column names for the borrower data
	public static final Object[] columnNames = { "Student ID", "Name", "gender", "Major", "User Name", "Password" };
	// Borrower data stored in a list of object arrays
	public static final List<Object[]> data = new ArrayList<>();


	static {
		add(new Object[] { "BC209436", "YANG XI", "Female", "BIDA", "yangxi", "000000" });
		add(new Object[] { "BB192832", "Emily", "Female", "Finc", "emily", "000000" });
		add(new Object[] { "BB053732", "John", "Male", "BIDA", "john", "970432" });
		add(new Object[] { "BB757917", "Sophia", "Female", "FINC", "sophia", "731053" });
		add(new Object[] { "BB273758", "Emma", "Male", "ACCT", "emma", "480500" });
		add(new Object[] { "BC341263", "James", "Male", "MKTG", "james", "248493" });
		add(new Object[] { "BC141244", "Ava", "Male", "FINC", "ava", "317972" });
		add(new Object[] { "BB976702", "Benjamin", "Male", "MGMT", "benjamin", "508073" });
		add(new Object[] { "BC530467", "William", "Male", "FINC", "william", "530706" });
	}


	public static Object[] findById(String id) {
		for (Object[] d : data) {
			if (d[0].equals(id)) {
				return d;
			}
		}
		return null;
	}
	//Adding user
	public static void add(Object[] obj) {
		data.add(obj);
		User user1 = new User((String) obj[0],(String) obj[4],(String) obj[5]);
		UserDAO.BORROWERS.put((String) obj[4], user1);
	}
	//Remove data
	public static void remove(Object id){
		for (int i = 0;i < data.size();i++){
			if(data.get(i)[0].equals(id)){
				data.remove(i);
				break;
			}
		}
	}
	//Multi-criteria search
	public static Object[][] searchMulti(int[] cols,String[] texts){
		List<Object[]> result = new ArrayList<>();
		for (Object[] d : data){
			boolean hit = true;
			for (int i = 0; i < cols.length; i++){
				if (CommonUtil.isNotEmpty(texts[i])){
					if(!d[cols[i]].toString().equals(texts[i])){
						hit = false;
						break;
					}
				}
			}
			if(hit){
				result.add(d);
			}
		}
		return CommonUtil.toArray(result);
	}

}
