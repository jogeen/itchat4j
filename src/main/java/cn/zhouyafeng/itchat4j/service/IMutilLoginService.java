package cn.zhouyafeng.itchat4j.service;

import cn.zhouyafeng.itchat4j.core.MultiCore;

public interface IMutilLoginService extends ILoginService{

	
	/**
	 * 获取
	 * @return
	 */
	MultiCore getMultiCore(); 

}
