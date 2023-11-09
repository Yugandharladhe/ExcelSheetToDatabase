package com.task.backend.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.task.backend.entities.Employee;

@Service
public class ExcelSheetToObject {

	public List<Employee> getListOfObjects(InputStream is)
	{
		List<Employee> emps=new ArrayList<>();
		
		try
		{
			XSSFWorkbook workbook=new XSSFWorkbook(is);
			XSSFSheet sheet=workbook.getSheet("sheet1");
			int rowNumber=0;
			Iterator<Row> it=sheet.iterator();
			
			while(it.hasNext())
			{
				Row row=it.next();
				
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cellIt=row.cellIterator();
				int cid=0;
				
				Employee emp=new Employee();
				
				while(cellIt.hasNext())
				{
					Cell cell=cellIt.next();
					switch(cid)
					{
						case 0:
							int id=(int) cell.getNumericCellValue();
							emp.setId(id);
							break;
						case 1:
							String dept=cell.getStringCellValue();
							emp.setDepartment(dept);
							break;
						case 2:
							String name=cell.getStringCellValue();
							emp.setName(name);
							break;
						case 3:
							double salary=cell.getNumericCellValue();
							emp.setSalary(salary);
							break;
					}
					
					cid++;
				}
				
				emps.add(emp);
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return emps;
	}
}
