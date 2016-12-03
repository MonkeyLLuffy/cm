package cn.dlb.cm.tool;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import cn.dlb.cm.entity.ContractRecord;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
    
    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public static List<ContractRecord> importExcel(InputStream inputStream) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		List<ContractRecord> result = new ArrayList<ContractRecord>();

	    POIFSFileSystem fs;
	    HSSFWorkbook wb;
	    HSSFSheet sheet;
	    HSSFRow row;
        try {
            fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);// Sheet的下标是从0开始， 获取第一张Sheet表
        // 得到总行数,总列数
        int rowNum = sheet.getLastRowNum();        
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        
        Map<String, String> chineseToFieldMap = XMLReader.loadconfig();
		Map<Integer, String> fieldOrderMap = new HashMap<Integer, String>();
		// 将上传的excel的title与java中的字段对应起来
		for (int i = 0; i < colNum; i++) {
			Cell cell = row.getCell(i);
			String content = cell.getRichStringCellValue().getString();
			if (chineseToFieldMap.get(content) != null) {
				//得到对应字段是第几列的单元格
				fieldOrderMap.put(i, chineseToFieldMap.get(content));
			}
		}
		
		
		// 获取指定单元格的对象引用
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			ContractRecord contractRowItem = new ContractRecord();
			while (j < colNum) {
				Cell cell = row.getCell(j);
				String content = cell.getRichStringCellValue().getString();
				System.out.println(content);
				if (content == null||content.length() == 0) {
					continue;
				}
				//通过反射将英文字段和单元格对应
				Class contractClass = (Class) contractRowItem.getClass();
				Field f1 = contractClass.getDeclaredField(fieldOrderMap.get(j));
				f1.setAccessible(true);
				if (fieldOrderMap.get(j) == null ||fieldOrderMap.get(j).length() == 0) {
					continue;//为空就跳过
				}
				if (fieldOrderMap.get(j).equals("deadline")||fieldOrderMap.get(j).equals("signingDate")) {
					Date deadlineDate = DateFormate.parse(content);
					f1.set(contractRowItem, deadlineDate);
				}else {
					f1.set(contractRowItem, content);
				}
				
			}
			contractRowItem.setState(1);
			contractRowItem.setImportDate(new Date());
			result.add(contractRowItem);
			
		}
        return result;
    }
    
    
    
    /**
	 * 方法简述：从数据库导出Excel文件
	 * @param os
	 *            文件输出流
	 * @param ContractRecord
	 *            合同对象数组
	 * @return 时间：2016-11-22 作者：刘群
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static boolean exportExcel(OutputStream os, List<ContractRecord> contracts)
			throws IOException {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("合同数据");
		
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        
        HSSFCell ID = row.createCell(0);
        ID.setCellValue("流程编号"); 
        
        HSSFCell contractNum = row.createCell(1);
        contractNum.setCellValue("合同编号"); 
        
        HSSFCell contractName = row.createCell(2);
        contractName.setCellValue("合同名称"); 
        
        HSSFCell partyA = row.createCell(3);
        partyA.setCellValue("合同甲方"); 
        
        HSSFCell partyB = row.createCell(3);
        partyB.setCellValue("合同乙方");
        
        HSSFCell contractType = row.createCell(4);
        contractType.setCellValue("合同类型"); 
        
        HSSFCell signingDate = row.createCell(5);
        signingDate.setCellValue("签订日期"); 
        
        HSSFCell deadline = row.createCell(6);
        deadline.setCellValue("合同期限"); 
        
        HSSFCell contractAmount = row.createCell(6);
        contractAmount.setCellValue("合同金额"); 
        
        HSSFCell depart = row.createCell(7);
        depart.setCellValue("经办部门"); 
        
        HSSFCell operator = row.createCell(8);
        operator.setCellValue("经办人"); 
        
        HSSFCell remark = row.createCell(9);
        remark.setCellValue("备注"); 
        
		int r = 1;
		for (ContractRecord contract : contracts) {
	        HSSFRow contentRow = sheet.createRow(0);

			ID = contentRow.createCell(0);
			ID.setCellValue(contract.getId());
			
			
			contractNum = contentRow.createCell(1);
			contractNum.setCellValue(contract.getContractNum());
			
			contractName = contentRow.getCell(2);
			contractName.setCellValue(contract.getContractName());

			partyA = contentRow.getCell(3);
			partyA.setCellValue(contract.getPartyA());
			
			
			partyB = contentRow.getCell(4);
			partyB.setCellValue(contract.getPartyA());
			
			contractType = contentRow.getCell(5);
			contractType.setCellValue(contract.getPartyB());
			
			signingDate = contentRow.getCell(6);
			signingDate.setCellValue(DateFormate.formate(contract.getSigningDate()));

			deadline = contentRow.getCell(6);
			deadline.setCellValue(DateFormate.formate(contract.getDeadline()));

			contractAmount = contentRow.getCell(6);
			contractAmount.setCellValue(contract.getContractAmount());

			depart = contentRow.getCell(6);
			depart.setCellValue(contract.getDepart());

			operator = contentRow.getCell(6);
			operator.setCellValue(contract.getOperator());

			remark = contentRow.getCell(6);
			remark.setCellValue(contract.getRemark());
			
		}
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write(os);
		os.close();
		System.out.println("导出成功");

		return true;
	}
}