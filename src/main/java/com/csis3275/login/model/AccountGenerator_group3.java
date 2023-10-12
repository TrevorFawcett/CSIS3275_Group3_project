package com.csis3275.login.model;

import java.util.ArrayList;
import java.util.Random;
import com.csis3275.login.service.UserServiceImpl_group3;
import com.csis3275.login.model.User_group3;

public class AccountGenerator_group3  {

	    //Generate 16 digit number 
	public long generateNumber() {
		Random rand = new Random();
		String card ="";
		
		for (int i = 0; i < 16; i++)
	    {
	        Long n = rand.nextLong(10) + 0;
	        card += Long.toString(n);
	    }
		
		return Long.parseLong(card);
	}
	
	//check database 
	public boolean checkDB(ArrayList<User_group3> userList, Long numberToCheck) {
		//ArrayList<User_group3> userList = userServiceImpl.readUsers();
		ArrayList<Long> accountNumbers = new ArrayList<Long>();
		
		for(int i = 0; i <userList.size(); i++)
		{
			accountNumbers.add(userList.get(i).getAccNumber());
		}
		
		
		if(accountNumbers.contains(numberToCheck)) {
			return true;
		}
		else {
			return false;
		}
		
	}
    
    
	
	
	
}
