package com.inno.exports;

import java.io.File;
import java.sql.ResultSet;
import java.util.Collection;
/*
 * SuperExport is an interface which provides following overloaded methods to help implementing 
   classes to write their own implementation
 * 
 * @author Srikanth.P
 */

public interface SuperExport<T> {
	/*
	 * This method will export the data encapsulated in  object of type java.util.ResultSet
	   to an excel spreadsheet
	 *@param object {@type java.util.ResultSet} 
	   object that needs to be exported
	 *@param Filename
	   the output file name
	 *@throws exception to avoid handling Exceptions
	 *@return File type instance that has exported data
	 */
	 File Export(ResultSet rs,String Filename) throws Exception;
	 /*
		 * This method will export the data encapsulated in  object 
		   to an excel spreadsheet
		 *@param object  
		   object that needs to be exported
		 *@param Filename
		   the output file name
		 *@throws exception to avoid handling Exceptions
		 *@return File type instance that has exported data
		 */
	 File Export(T object,String Filename) throws Exception;
	 /*
		 * This method will export the data encapsulated in  collection of objects 
		   to an excel spreadsheet
		 *@param collection of objects {List/Set}  
		   objects that are needs to be exported
		 *@param Filename
		   the output file name
		 *@throws exception to avoid handling Exceptions
		 *@return File type instance that has exported data
		 */
	 File Export(Collection<T> object,String Filename) throws Exception;


}
