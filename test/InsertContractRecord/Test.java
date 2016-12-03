package InsertContractRecord;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dlb.cm.controller.UserController;
import cn.dlb.cm.entity.ContractRecord;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.SystemCode;

public class Test {
	/**
	 * private String id;
	private String contractNum;
	private String contractName;
	private String partyA;
	private String partyB;
	private String contractType;
	private Date signingDate;
	private Date deadline;
	private Date importDate;
	private Date exportDate;
	private Date updateDate;
	private String contractAmount;
	private String depart;
	private String operator;
	private String remark;
	private ContractFile contractFile;
	private User importUser;
	private int state;
	 * 方法描述:
	 * 时间:2016-11-25
	 * 作者:路遥
	 */
	ApplicationContext app = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
	SessionFactory factory = (SessionFactory) app.getBean("factoryBean");
	Session session = factory.openSession();
	
	@org.junit.Test
	public void InsertContractRecord(){
		int i=5;
		
		for(;i<10;i++){
			ContractRecord con=new ContractRecord();
			con.setId("WX-"+i);
			con.setContractNum("LY-"+i);
			con.setContractName("DLB-"+i);
			con.setPartyA("广州万象物流");
			con.setPartyB("杭州联报速递");
			con.setContractType(i+"哈哈"+"j");
			con.setSigningDate(new Date());
			con.setDeadline(new Date());
			con.setImportDate(new Date());
			con.setContractAmount(i+"2"+i);
			con.setDepart("广州万象物流");
			con.setOperator("路遥"+i);
			con.setRemark("hjskhkhjkdshakj黄金客户即可"+i+"觉得卡萨");
			con.setState(1);
			Transaction transaction=session.beginTransaction();
			session.save(con);
			session.flush();
			transaction.commit();
		}
		
		
		for(;i<15;i++){
			ContractRecord con=new ContractRecord();
			con.setId("WX-"+i);
			con.setContractNum("LY-"+i);
			con.setContractName("DLB-"+i);
			con.setPartyA("长沙联报供应链管理");
			con.setPartyB("杭州联报速递");
			con.setContractType(i+"哈哈"+"j");
			con.setSigningDate(new Date());
			con.setDeadline(new Date());
			con.setImportDate(new Date());
			con.setContractAmount(i+"2"+i);
			con.setDepart("长沙联报供应链管理");
			con.setOperator("路遥"+i);
			con.setRemark("hjskhkhjkdshakj黄金客户即可"+i+"觉得卡萨");
			con.setState(1);
			Transaction transaction=session.beginTransaction();
			session.save(con);
			session.flush();
			transaction.commit();
		}
		
		session.close();
	}
	
	@org.junit.Test
	public void addContractType(){
		
			SystemCode code1=new SystemCode();
			code1.setCodeType("contractType");
			code1.setCodeName("配送合同");
			
			SystemCode code2=new SystemCode();
			code2.setCodeType("contractType");
			code2.setCodeName("配送合同");
			
			SystemCode code3=new SystemCode();
			code3.setCodeType("contractType");
			code3.setCodeName("运输合同");
			
			SystemCode code4=new SystemCode();
			code4.setCodeType("contractType");
			code4.setCodeName("仓储合同");
			
			SystemCode code5=new SystemCode();
			code5.setCodeType("contractType");
			code5.setCodeName("购销合同");
			
			SystemCode code6=new SystemCode();
			code6.setCodeType("contractType");
			code6.setCodeName("技术合同");
			
			SystemCode code7=new SystemCode();
			code7.setCodeType("contractType");
			code7.setCodeName("合作合同");
	
			SystemCode code8=new SystemCode();
			code8.setCodeType("contractType");
			code8.setCodeName("协议合同");
			
			SystemCode code9=new SystemCode();
			code9.setCodeType("contractType");
			code9.setCodeName("服务合同");
			
			SystemCode code10=new SystemCode();
			code10.setCodeType("contractType");
			code10.setCodeName("许可合同");
			
			SystemCode code11=new SystemCode();
			code11.setCodeType("contractType");
			code11.setCodeName("劳务合同");
			
			SystemCode code12=new SystemCode();
			code12.setCodeType("contractType");
			code12.setCodeName("工程建设合同");
			
			Transaction transaction=session.beginTransaction();
			session.save(code1);
			session.save(code2);
			session.save(code3);
			session.save(code4);
			session.save(code5);
			session.save(code6);
			session.save(code7);
			session.save(code8);
			session.save(code9);
			session.save(code10);
			session.save(code11);
			session.save(code12);
			session.flush();
			transaction.commit();
			session.close();
	}
	
	@org.junit.Test
	public void addDepart(){
		SystemCode code1=new SystemCode();
		code1.setCodeType("depart");
		code1.setCodeName("万向发展");
		
		SystemCode code2=new SystemCode();
		code2.setCodeType("depart");
		code2.setCodeName("万象配送");
		
		SystemCode code3=new SystemCode();
		code3.setCodeType("depart");
		code3.setCodeName("上海递拎宝");
		
		SystemCode code4=new SystemCode();
		code4.setCodeType("depart");
		code4.setCodeName("广州万象");
		
		SystemCode code5=new SystemCode();
		code5.setCodeType("depart");
		code5.setCodeName("云南万象");
		
		SystemCode code6=new SystemCode();
		code6.setCodeType("depart");
		code6.setCodeName("杭州联报");
		
		SystemCode code7=new SystemCode();
		code7.setCodeType("depart");
		code7.setCodeName("北京联报");

		SystemCode code8=new SystemCode();
		code8.setCodeType("depart");
		code8.setCodeName("天津联报");
		
		SystemCode code9=new SystemCode();
		code9.setCodeType("depart");
		code9.setCodeName("重庆联报");
		
		SystemCode code10=new SystemCode();
		code10.setCodeType("depart");
		code10.setCodeName("江苏联报");
		
		SystemCode code11=new SystemCode();
		code11.setCodeType("depart");
		code11.setCodeName("长沙联报");
		
		SystemCode code12=new SystemCode();
		code12.setCodeType("depart");
		code12.setCodeName("辽宁联报");
		
		SystemCode code13=new SystemCode();
		code13.setCodeType("depart");
		code13.setCodeName("贵阳联报");
		
		SystemCode code14=new SystemCode();
		code14.setCodeType("depart");
		code14.setCodeName("成都报网");
		
		SystemCode code15=new SystemCode();
		code15.setCodeType("depart");
		code15.setCodeName("福州报网");
		
		SystemCode code16=new SystemCode();
		code16.setCodeType("depart");
		code16.setCodeName("武汉报网");
		
		SystemCode code17=new SystemCode();
		code17.setCodeType("depart");
		code17.setCodeName("济南报网");
		
		SystemCode code18=new SystemCode();
		code18.setCodeType("depart");
		code18.setCodeName("河南报网");
		
		SystemCode code19=new SystemCode();
		code19.setCodeType("depart");
		code19.setCodeName("石家庄报网");
		
		SystemCode code20=new SystemCode();
		code20.setCodeType("depart");
		code20.setCodeName("天津理物");
		
		SystemCode code21=new SystemCode();
		code21.setCodeType("depart");
		code21.setCodeName("江西传世");
		
		
		Transaction transaction=session.beginTransaction();
		session.save(code1);
		session.save(code2);
		session.save(code3);
		session.save(code4);
		session.save(code5);
		session.save(code6);
		session.save(code7);
		session.save(code8);
		session.save(code9);
		session.save(code10);
		session.save(code11);
		session.save(code12);
		session.save(code13);
		session.save(code14);
		session.save(code15);
		session.save(code16);
		session.save(code17);
		session.save(code18);
		session.save(code19);
		session.save(code20);
		session.save(code21);
		session.flush();
		transaction.commit();
		session.close();
	}
	
	@org.junit.Test
	public void downloadHistory(){
		
		
	}
}
