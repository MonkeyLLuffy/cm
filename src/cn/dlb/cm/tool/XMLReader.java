package cn.dlb.cm.tool;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {
	private static String filename = "/contract.xml";
	private static String filePath;
	private static Map<String, String> config;
	private static XMLReader xmlReader = null;
	private XMLReader(){
		filePath = this.getClass().getClassLoader().getResource("/").getPath();
	}

	public static Map<String, String> loadconfig() {
		if (config == null){
			xmlReader = new XMLReader();
			config = xmlReader.getconfig();
			}
		return config;
	}
	private Map<String, String> getconfig() {
		
		Map<String, String> config = new HashMap<String, String>();
		try {
			File f = new File(filePath+filename);
			if (!f.exists()) {
				System.out.println("  Error : Config file doesn't exist!");
			}
			SAXReader reader = new SAXReader();
			Document doc;
			doc = reader.read(f);
			Element root = doc.getRootElement();
			Element data;
			Iterator<?> itr = root.elementIterator("VALUE");
			data = (Element) itr.next();
			config.put( data.elementText("id").trim(),"id");
			config.put(data.elementText("contractName").trim(),"contractName" );
			config.put( data.elementText("partyA").trim(),"partyA");
			config.put( data.elementText("partyB").trim(),"partyB");
			config.put( data.elementText("contractNum").trim(),"contractNum");
			config.put( data.elementText("contractType").trim(),"contractType");
			config.put( data.elementText("signingDate").trim(),"signingDate");
			config.put( data.elementText("deadline").trim(),"deadline");
			config.put( data.elementText("importDate").trim(),"importDate");
			config.put( data.elementText("contractAmount").trim(),"contractAmount");
			config.put( data.elementText("depart").trim(),"depart");
			config.put( data.elementText("operator").trim(),"operator");
			config.put( data.elementText("remark").trim(),"remark");
		} catch (Exception ex) {
			System.out.println("Error : " + ex.toString());
		}
		return config;

	}
}
