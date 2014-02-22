/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package net.newcapec.sca.util;

import java.text.SimpleDateFormat;
import org.apache.tuscany.das.rdb.Converter;

public class DateStringConverter implements Converter {

	private static SimpleDateFormat myFmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public DateStringConverter() {
		super();
	}

	//select
	public Object getPropertyValue(Object columnData) {

		return columnData;
	}
	//update
	public Object getColumnValue(Object propertyData) {
		java.sql.Time date = null;
		try
		{
			java.util.Date utilDate= myFmt.parse(propertyData.toString());
			java.sql.Time sTime=new java.sql.Time(utilDate.getTime());
			date = sTime;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return date;
	}

}
