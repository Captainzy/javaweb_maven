package netty.appFramework.common;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class NoticeTimerJob extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
		//这里是需要实现的业务代码
	}

}
