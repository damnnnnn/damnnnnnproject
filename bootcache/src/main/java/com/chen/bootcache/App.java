package com.chen.bootcache;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main( String[] args )
	    {
	    	
	    	
	    	String  tableallInfo=TableFixed.MENU_TABINFO;
	    	String tableName=tableallInfo.substring(0,tableallInfo.indexOf(";"));
	    	
	    	System.out.println(tableName);
	    	
	    	String columns=tableallInfo.substring(tableallInfo.indexOf(";")+1);
	    	
	    	System.out.println(columns);
	    	
	    	String[] sscolumn=columns.split(",");
	    	
	    	CacheManager cm = new CacheManager();
	    	List<Map<String,Object>>  lists=cm.cachedatas(tableName);
	   
	   	 System.out.println("表的长度:"+lists.size());
	    	
	    	for(Map<String,Object>  maps:lists)
	    	{
	   	         for(String  str:sscolumn)
	   	         {
	   	        	System.out.println(maps.get(str).toString());
	   	         }
	    	}
	    }
}
