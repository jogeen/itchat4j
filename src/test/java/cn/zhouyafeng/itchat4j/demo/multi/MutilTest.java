package cn.zhouyafeng.itchat4j.demo.multi;

import java.util.Scanner;

import cn.zhouyafeng.itchat4j.controller.MultiLoginController;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.face.impl.DefaultMsgHandlerFaceImpl;

public class MutilTest {
	public static void main(String[] args) {
		IMsgHandlerFace msgHandler=new DefaultMsgHandlerFaceImpl();
		MultiLoginController instance = MultiLoginController.getInstance("",msgHandler);
		instance.init();
		instance.getLoginQrCode(instance.getLoginUuid());
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("输入login,生成新的登录二位码");
			String nextLine = scanner.nextLine();
			if("y".equals(nextLine)) {
				instance.getLoginQrCode( instance.getLoginUuid());
			}
		}
		
		
	}

}
