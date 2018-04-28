package cn.zhouyafeng.itchat4j.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.core.MultiCoreManager;

/**
 * @author www.jogeen.top
 * @date 2018年4月28日
 * @version 1.0
 */
public class MultiLoginServiceManager {

	private static Logger LOG = LoggerFactory.getLogger(MultiCoreManager.class);

	private static MultiLoginServiceManager instance;

	private static Map<String, IMutilLoginService> multiCoreMap = new ConcurrentHashMap<>();

	private MultiLoginServiceManager() {
	}

	public static MultiLoginServiceManager getInstance() {
		if (instance == null) {
			synchronized (Core.class) {
				instance = new MultiLoginServiceManager();
			}
		}
		return instance;
	}

}
