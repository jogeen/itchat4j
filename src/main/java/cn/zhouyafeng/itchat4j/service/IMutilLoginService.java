package cn.zhouyafeng.itchat4j.service;

import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.QRType;

/**
 * @author www.jogeen.top
 * @date 2018年4月28日
 * @version 1.0
 */
public interface IMutilLoginService extends ILoginService{

	
	/**
	 * 获取
	 * @return
	 */
	MultiCore getMultiCore();

	String getQRForType(String qrPath, QRType QRType);

	void handleMsg(); 

}
