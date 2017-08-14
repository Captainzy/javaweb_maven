package freemarker.method;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FreemarkerMethodTest implements TemplateMethodModelEx {

	@Override
	public Object exec(List list) throws TemplateModelException {
		if(list != null && list.size()!=0){
			if("admin".equals(list.get(0).toString())){
				return "管理员";
			}else{
				return "普通用户";
			}
		}else{
			return "没有输入";
		}
	}

}
