package cn.zhouyafeng.itchat4j.thread;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.controller.MultiLoginController;
import cn.zhouyafeng.itchat4j.core.MultiCore;
import cn.zhouyafeng.itchat4j.service.IMutilLoginService;
import cn.zhouyafeng.itchat4j.utils.SleepUtils;

/**
 * 检查微信在线状态
 * <p>
 * 如何来感知微信状态？
 * 微信会有心跳包，LoginServiceImpl.syncCheck()正常在线情况下返回的消息中retcode报文应该为"0"，心跳间隔一般在25秒，
 * 那么可以通过最后收到正常报文的时间来作为判断是否在线的依据。若报文间隔大于60秒，则认为已掉线。
 * </p>
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年5月17日 下午10:53:15
 * @version 1.0
 *
 */
public class MessageReceivThread implements Runnable {
	private static Logger LOG = LoggerFactory.getLogger(MessageReceivThread.class);
	private MultiLoginController multiLoginController = MultiLoginController.getInstance();

	@Override
	public void run() {
		Map<String, IMutilLoginService> serviceMap = multiLoginController.getOnLineServiceMap();
		while (true) {
			if(!serviceMap.isEmpty()) {
				Set<String> keySet = serviceMap.keySet();
				for (String key : keySet) {
					IMutilLoginService mutilLoginService = serviceMap.get(key);
					mutilLoginService.startReceiving();
				}
			}
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
