package cn.zhouyafeng.itchat4j.core;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.utils.SleepUtils;

public class MultiCoreManager {

	private static Logger LOG = LoggerFactory.getLogger(MultiCoreManager.class);

	private static MultiCoreManager instance;

	private static boolean isChecking = true;

	private static Thread checkThread = null;

	private static Map<String, MultiCore> multiCoreMap = new ConcurrentHashMap<>();

	private MultiCoreManager() {
	}

	public static MultiCoreManager getInstance() {
		if (instance == null) {
			synchronized (Core.class) {
				instance = new MultiCoreManager();
			}
		}
		return instance;
	}

	public void putMultiCore(String openId, MultiCore multiCore) {
		multiCoreMap.put(openId, multiCore);
	}

	public MultiCore getMultiCore(String openId) {
		return multiCoreMap.get(openId);
	}

	public void startCheckLoginStatus() {
		synchronized (checkThread) {
			if (checkThread != null) {
				LOG.info("已存在检测线程");
				return;
			} else {
				checkThread = new Thread(new Runnable() {
					@Override
					public void run() {
						while (!multiCoreMap.isEmpty()) {
							if (!isChecking) {
								LOG.info("登录线程检测开关已关闭");
								break;
							}
							Set<String> keySet = multiCoreMap.keySet();
							for (String openId : keySet) {
								MultiCore multiCore = multiCoreMap.get(openId);
								long t1 = System.currentTimeMillis(); // 秒为单位
								if (t1 - multiCore.getLastNormalRetcodeTime() > 60 * 1000) { // 超过60秒，判为离线
									multiCore.setAlive(false);
									LOG.info("微信已离线");
								}
							}
							SleepUtils.sleep(10 * 1000); // 休眠10秒
						}
					}
				});
			}
		}
	}

	public void stopCheckLoginStatus() {
		isChecking = false;
	}

}
