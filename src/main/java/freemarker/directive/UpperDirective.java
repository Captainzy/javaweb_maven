package freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UpperDirective implements TemplateDirectiveModel{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// Check if no parameters were given:
		for (Object entry : params.entrySet()) { 
			Entry<String,Object> m = (Entry<String, Object>)entry;
			System.out.println(m.getKey()+" : "+m.getValue());
		}
        if (loopVars.length != 0) {
                throw new TemplateModelException(
                    "This directive doesn't allow loop variables.");
        }
        
        // If there is non-empty nested content:
        if (body != null) {
            // Executes the nested body. Same as <#nested> in FTL, except
            // that we use our own writer instead of the current output writer.
            body.render(new UpperDirectiveWriter(env.getOut()));
        } else {
            throw new RuntimeException("missing body");
        }
	}
	
	private static class UpperDirectiveWriter extends Writer{

		private final Writer out ;
		public UpperDirectiveWriter(Writer writer){
			this.out = writer;
		}
		@Override
		public void close() throws IOException {
			out.close();
			
		}

		@Override
		public void flush() throws IOException {
			out.flush();
		}

		@Override
		public void write(char[] chars, int off, int len) throws IOException {
			char[] charArr = new char[len];
			for(int i = 0;i<len;i++){
				charArr[i] = Character.toUpperCase(chars[i]);
			}
			this.out.write(charArr);
		}
		
	}
	
}
