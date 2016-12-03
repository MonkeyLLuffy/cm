package cn.dlb.cm.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import cn.dlb.cm.entity.ContractRecord;

public class EcxcelOperation {

	/**
	 * 方法简述：导入Excel文件到数据库
	 * @return 
	 * 时间：2016-11-22 作者：刘群
	 */
	public static List<ContractRecord> importExcel(InputStream instream) {

		List<ContractRecord> result = new ArrayList<ContractRecord>();
		jxl.Workbook readwb = null;
		try {
			// 构建Workbook对象, 只读Workbook对象，直接从输入流创建Workbook
			readwb = Workbook.getWorkbook(instream);
			
			// Sheet的下标是从0开始， 获取第一张Sheet表
			Sheet readsheet = readwb.getSheet(0);
			
			// 获取Sheet表中所包含的总列数
			int rsColumns = readsheet.getColumns();
			
			Map<String, String> chineseToFieldMap = XMLReader.loadconfig();
			Map<Integer, String> fieldOrderMap = new HashMap<Integer, String>();
			// 将上传的excel的title与java中的字段对应起来
			for (int i = 0; i < rsColumns; i++) {
				Cell cell = readsheet.getCell(i, 0);
				if (chineseToFieldMap.get(cell.getContents()) != null) {
					//得到对应字段是第几列的单元格
					fieldOrderMap.put(i, chineseToFieldMap.get(cell.getContents()));
				}
			}
			// 获取Sheet表中所包含的总行数
			int rsRows = readsheet.getRows();
			// 获取指定单元格的对象引用
			for (int i = 1; i < rsRows; i++) {
				
				ContractRecord contractRowItem = new ContractRecord();
				for (int j = 0; j < rsColumns; j++) {
					System.out.println(readsheet.getCell(j, i).getContents());
					if (readsheet.getCell(j, i).getContents() == null||readsheet.getCell(j, i).getContents().length() == 0) {
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
						Date deadlineDate = DateFormate.parse(readsheet.getCell(j, i).getContents());
						f1.set(contractRowItem, deadlineDate);
					}else {
						f1.set(contractRowItem, readsheet.getCell(j, i).getContents());
					}
				}
				contractRowItem.setState(1);
				contractRowItem.setImportDate(new Date());
				result.add(contractRowItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
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
			throws IOException, RowsExceededException, WriteException {
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label ID = new Label(0, 0, "流程编号");
		sheet.addCell(ID);
		Label contractNum = new Label(1, 0, "合同编号");
		sheet.addCell(contractNum);
		Label contractName = new Label(2, 0, "合同名称");
		sheet.addCell(contractName);
		Label partyA = new Label(3, 0, "合同甲方");
		sheet.addCell(partyA);
		Label partyB = new Label(4, 0, "合同乙方");
		sheet.addCell(partyB);
		Label contractType = new Label(5, 0, "合同类型");
		sheet.addCell(contractType);
		Label signingDate = new Label(6, 0, "签订日期");
		sheet.addCell(signingDate);
		Label deadline = new Label(7, 0, "合同期限");
		sheet.addCell(deadline);
		Label contractAmount = new Label(8, 0, "合同金额");
		sheet.addCell(contractAmount);
		Label depart = new Label(9, 0, "经办部门");
		sheet.addCell(depart);
		Label operator = new Label(10, 0, "经办人");
		sheet.addCell(operator);
		Label remark = new Label(11, 0, "备注");
		sheet.addCell(remark);
		
		int r = 1;
		for (ContractRecord contract : contracts) {
			int c = 0;

			ID = new Label(0, r, contract.getId());
			sheet.addCell(ID);
			contractNum = new Label(1, r, contract.getContractNum());
			sheet.addCell(contractNum);
			contractName = new Label(2, r, contract.getContractName());
			sheet.addCell(contractName);
			partyA = new Label(3, r, contract.getPartyA());
			sheet.addCell(partyA);
			partyB = new Label(4, r, contract.getPartyB());
			sheet.addCell(partyB);
			contractType = new Label(5, r, contract.getContractType());
			sheet.addCell(contractType);
			signingDate = new Label(6, r, DateFormate.formate(contract.getSigningDate()));
			sheet.addCell(signingDate);
			deadline = new Label(7, r, DateFormate.formate(contract.getDeadline()));
			sheet.addCell(deadline);
			contractAmount = new Label(8, r, contract.getContractAmount());
			sheet.addCell(contractAmount);
			depart = new Label(9, r, contract.getDepart());
			sheet.addCell(depart);
			operator = new Label(10, r, contract.getOperator());
			sheet.addCell(operator);
			remark = new Label(11, r, contract.getRemark());
			sheet.addCell(remark);
		}
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();

		return true;
	}

	public static void main(String[] args) throws RowsExceededException,
			WriteException {
		
		  try { 
			  FileOutputStream fo = new FileOutputStream("test.xlsx");
			  exportExcel(fo, null);
		  
		  } 
		  catch (IOException e) { 
			  e.printStackTrace();
		  }
		  
		  FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File("test.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<ContractRecord> list = importExcel(inputStream);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}

	}
}
