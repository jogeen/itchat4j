package cn.zhouyafeng.itchat4j.face.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

public class DefaultMsgHandlerFaceImpl implements IMsgHandlerFace {
	
	private static Logger LOG = LoggerFactory.getLogger(DefaultMsgHandlerFaceImpl.class);

	@Override
	public String textMsgHandle(BaseMsg msg) {
		LOG.info("进入textMsgHandle方法");
		LOG.info(String.format("用户%s收到来之%s的消息:%s", msg.getToUserName(), msg.getFromUserName(),msg.getText()));
		return null;
	}

	@Override
	public String picMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public String nameCardMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sysMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String verifyAddFriendMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mediaMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
