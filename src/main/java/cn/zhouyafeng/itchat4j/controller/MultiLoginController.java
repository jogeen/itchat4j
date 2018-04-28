package cn.zhouyafeng.itchat4j.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.beans.User;
import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.service.IMutilLoginService;
import cn.zhouyafeng.itchat4j.service.impl.MutilLoginServiceImpl;
import cn.zhouyafeng.itchat4j.utils.enums.QRType;

/**
 * 多用户登录的控制类
 * @author www.jogeen.top
 * @date 2018年4月28日
 * @version 1.0
 */
public class MultiLoginController {
	private static Logger LOG = LoggerFactory.getLogger(MultiLoginController.class);

	/**
	 * 服务管理的map
	 */
	private Map<String, IMutilLoginService> waitServiceMap = new ConcurrentHashMap<>();
	private Map<String, IMutilLoginService> onLineServiceMap = new ConcurrentHashMap<>();
	private Map<String, Long> waitServiceTimeMap = new ConcurrentHashMap<>();
	private static MultiLoginController instance;
	private static String qrPath = "";

	public Map<String, IMutilLoginService> getWaitServiceMap() {
		return waitServiceMap;
	}

	public Map<String, IMutilLoginService> getOnLineServiceMap() {
		return onLineServiceMap;
	}

	public static MultiLoginController getInstance(String path) {
		if (instance == null) {
			synchronized (MultiLoginController.class) {
				instance = new MultiLoginController();
			}
		}
		if (qrPath != null) {
			qrPath = path;
		}
		return instance;
	}

	public static MultiLoginController getInstance() {
		if (instance == null) {
			synchronized (MultiLoginController.class) {
				instance = new MultiLoginController();
			}
		}
		return instance;
	}

	public String getLoginUuid() {
		IMutilLoginService mutilLoginService = new MutilLoginServiceImpl();
		String uuid = mutilLoginService.getUuid();
		if (LOG.isDebugEnabled()) {
			LOG.debug("发起一次登录uuid请求,{}", uuid);
		}
		if (uuid != null) {
			waitServiceMap.put(uuid, mutilLoginService);
			waitServiceTimeMap.put(uuid, System.currentTimeMillis());
		}
		return uuid;
	}

	public String getLoginQrCode(String uuid) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("发起一次登录二维码请求,{}", uuid);
		}
		IMutilLoginService mutilLoginService = getMutilLoginService(uuid);
		String qrString = mutilLoginService.getQRForType(qrPath, QRType.BASE64_STRING);
		return qrString;
	}

	public User getloginResult(String uuid) {
		IMutilLoginService mutilLoginService = getMutilLoginService(uuid);
		MultiCore multiCore = mutilLoginService.getMultiCore();
		if (multiCore.isAlive()) {
			JSONObject userSelf = multiCore.getUserSelf();
			User user = userSelf.toJavaObject(User.class);
			return user;
		}
		return null;
	}

	private IMutilLoginService getMutilLoginService(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid不能为空");
		}
		IMutilLoginService mutilLoginService = waitServiceMap.get(uuid);
		if (mutilLoginService == null) {
			LOG.debug("登录过期,{}", uuid);
			throw new RuntimeException("登录过去");
		}
		return mutilLoginService;
	}
}