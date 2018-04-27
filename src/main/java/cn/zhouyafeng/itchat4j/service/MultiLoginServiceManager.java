package cn.zhouyafeng.itchat4j.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.core.MultiCoreManager;

public class MultiLoginServiceManager {

	private static Logger LOG = LoggerFactory.getLogger(MultiCoreManager.class);

	private static MultiLoginServiceManager instance;

	private static boolean isChecking = true;

	private static Thread checkThread = null;

	private static Map<String, MultiCore> multiCoreMap = new ConcurrentHashMap<>();

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
