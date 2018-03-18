package gz.itcast.framework.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 配置管理类
 * 读取mystruts.xml文件的信息，然后封装到javabena中
 * @author APPle
 *
 */
public class ConfigurationManager {
	//封装ActionMapping的集合
	private Map<String,ActionMapping> actionMappings;
	
	public Map<String, ActionMapping> getActionMappings() {
		return actionMappings;
	}
	
	//构造方法
	public ConfigurationManager(){
		//封装所有的ActionMapping
		actionMappings = new HashMap<String,ActionMapping>();
		this.init();
	}
	
	/**
	 * 使用dom4j读取mystruts.xml文件，然后逐个对象进行封装 
	 */

	public void init(){
		try {
			//1)使用类路径方式读取mysturs.xml文件
			InputStream in = ConfigurationManager.class.getResourceAsStream("/mystruts.xml");
			
			//2)创建SAXREader
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);

			//3）读取所有action标签
			List<Element> actionList = doc.selectNodes("//action");
			Map<String,ActionMapping> amMap = new HashMap<String,ActionMapping>();
			for (Element acElem : actionList) {
				ActionMapping am = new ActionMapping();
				//封装ActionMapping
				//(必须有)
				am.setName(acElem.attributeValue("name"));
				am.setClassName(acElem.attributeValue("class"));
				//mehtod可选
				if(acElem.attributeValue("method")==null){
					//默认值  execute
					am.setMethod("execute");
				}else{
					am.setMethod(acElem.attributeValue("method"));
				}
				
				//封装Result
				List<Element> resultList = acElem.elements("result");
				Map<String,Result> rsMap = new HashMap<String,Result>();
				/**
				 *  *<action name="register" class="gz.itcast.web.RegisterAction" method="execute">
						<result name="success" type="redirect">/login.jsp</result>
					</action>
				 */
				for (Element rsElem : resultList) {
					Result rs = new Result();
					rs.setName(rsElem.attributeValue("name"));
					//type可选
					if(rsElem.attributeValue("type")==null){
						//跳转类型默认为转发
						rs.setType("dispatcher");
					}else{
						rs.setType(rsElem.attributeValue("type"));
					}
					rs.setPage(rsElem.getText().trim());
					rsMap.put(rs.getName(), rs);
				}//小for的括号
				
				//把封装好Rsult集合放入ActionMapping中
				am.setResults(rsMap);
				//把封装好的ActionMapping放入集合中
				amMap.put(am.getName(), am);
			}//大for的括号
			
			actionMappings = amMap;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
