package com.example.helper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Employee;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {


    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


  
    public static List<Employee> convertExcelToListOfEmployee(InputStream inputStream) {
        List<Employee> list = new ArrayList<>();

        try {


        	Workbook workbook = WorkbookFactory.create(inputStream);
        	Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cellId = 0;

                Employee employee = new Employee();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cellId) {
                        case 0:
                        	employee.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                        	employee.setEmployeeName(cell.getStringCellValue());
                            break;
                        case 2:
                        	employee.setEmail(cell.getStringCellValue());
                            break;
                        case 3:
                        	employee.setAddress(cell.getStringCellValue());
                            break;
                        case 4:
                          	employee.setSalary((int) cell.getNumericCellValue());
                            break;
                        case 5:
                          	employee.setBloodGroup(cell.getStringCellValue());
                            break;
                        case 6:	
                          	employee.setReportingManager(cell.getStringCellValue());
                            break;
                      
                        default:
                            break;
                    }
                    cellId++;

                }

                list.add(employee);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }




}
