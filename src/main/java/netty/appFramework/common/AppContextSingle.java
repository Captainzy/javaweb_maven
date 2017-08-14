package netty.appFramework.common;

public enum AppContextSingle {
	APPCONTEXT;
	private AppContext appContext;
	private AppContextSingle(){
		appContext = new AppContext();
	}
	
	public AppContext getInstantce(){
		return appContext;
	}
}
