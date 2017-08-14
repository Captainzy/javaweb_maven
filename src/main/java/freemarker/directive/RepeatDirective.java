package freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class RepeatDirective implements TemplateDirectiveModel {

	private static final String COUNT_NAME = "count";
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(params.isEmpty()){
			throw new TemplateModelException("There is no params");
		}
		int count = 0;
		for(Object obj : params.entrySet()){
			Entry<String,Object> entry = (Entry<String, Object>) obj;
			if(this.COUNT_NAME.equals(entry.getKey().toString())){
				count = Integer.valueOf(entry.getValue().toString());
			}
		}
		System.out.println(params.get("flag"));
		if(body!=null){
			Writer out = env.getOut();
			for(int i=0;i<count;i++){
				
				if (loopVars.length > 0) {
					for(int j = 0;j<loopVars.length;j++){
						loopVars[j] = new SimpleNumber(j + i + 1);
					}
	            }
				
				body.render(out);
			}
		}
	}

}
