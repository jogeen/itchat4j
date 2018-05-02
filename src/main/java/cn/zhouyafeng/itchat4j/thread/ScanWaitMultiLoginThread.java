package cn.zhouyafeng.itchat4j.thread;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.controller.MultiLoginController;
import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.service.IMutilLoginService;
import cn.zhouyafeng.itchat4j.utils.SleepUtils;

public class ScanWaitMultiLoginThread implements Runnable {
	private static Logger LOG = LoggerFactory.getLogger(ScanWaitMultiLoginThread.class);
	private MultiLoginController multiLoginController = MultiLoginController.getInstance();

	@Override
	public void run() {
		Map<String, IMutilLoginService> waitServiceMap = multiLoginController.getWaitServiceMap();
		Map<String, IMutilLoginService> onLineServiceMap = multiLoginController.getOnLineServiceMap();
		while (true) {
			if (waitServiceMap.isEmpty()) {
				continue;
			}
			Set<String> keySet = waitServiceMap.keySet();
			for (String key : keySet) {
				IMutilLoginService mutilLoginService = waitServiceMap.get(key);
				MultiCore core = mutilLoginService.getMultiCore();
				if (!core.isAlive()) {
					mutilLoginService.login();
					if (!mutilLoginService.webWxInit()) {
						LOG.info("6. 微信初始化异常");
						waitServiceMap.remove(key);
						continue;
					}
					LOG.info("6. 开启微信状态通知");
					mutilLoginService.wxStatusNotify();
					LOG.info(String.format("欢迎回来， %s", core.getNickName()));
					LOG.info("9. 获取联系人信息");
					mutilLoginService.webWxGetContact();
					LOG.info("10. 获取群好友及群好友列表");
					mutilLoginService.WebWxBatchGetContact();
					core.setAlive(true);
					waitServiceMap.remove(key);
					onLineServiceMap.put(key, mutilLoginService);
					LOG.info(("登陆成功"));
				} else {
					waitServiceMap.remove(key);
					LOG.info("4. 登陆超时，请重新扫描二维码图片");
				}

			}
			SleepUtils.sleep(1000); // 休眠10秒
		}
	}

}
