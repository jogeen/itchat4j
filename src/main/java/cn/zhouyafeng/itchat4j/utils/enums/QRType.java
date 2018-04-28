package cn.zhouyafeng.itchat4j.utils.enums;

public enum QRType {
	OPEN_SHOW("直接打开展示"), BASE64_STRING("BASE64字符串");

	private String msg;

	QRType(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
}
