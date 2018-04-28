package cn.zhouyafeng.itchat4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.controller.LoginController;
import cn.zhouyafeng.itchat4j.core.MsgCenter;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

public class Wechat {
	private static final Logger LOG = LoggerFactory.getLogger(Wechat.class);
	private IMsgHandlerFace msgHandler;

	public Wechat(IMsgHandlerFace msgHandler, String qrPath) {
		System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
		this.msgHandler = msgHandler;

		// 登陆
		LoginController login = new LoginController();
		login.login(qrPath);
	}

	public void start() {
		LOG.info("+++++++++++++++++++开始消息处理+++++++++++++++++++++");
		new Thread(new Runnable() {
			@Override
			public void run() {
				MsgCenter.handleMsg(msgHandler);
			}
		}).start();
	}
	public static void main(String[] args) {
		new Wechat(new IMsgHandlerFace() {
			
			@Override
			public String voiceMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String viedoMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String verifyAddFriendMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String textMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void sysMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String picMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String nameCardMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String mediaMsgHandle(BaseMsg msg) {
				// TODO Auto-generated method stub
				return null;
			}
		},"").start();
	}

}
