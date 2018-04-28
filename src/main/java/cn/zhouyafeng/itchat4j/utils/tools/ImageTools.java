package cn.zhouyafeng.itchat4j.utils.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author www.jogeen.top
 * @date 2018年4月28日
 * @version 1.0
 */
@SuppressWarnings({ "restriction" })
public final class ImageTools {

	private ImageTools() {
		throw new RuntimeException("这是一个工具类，不能实例化");
	}

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author: www.jogeen.top
	 * @date 2018年4月28日
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null) {
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: www.jogeen.top
	 * @date 2018年4月28日
	 * @return base64编码字符串
	 */
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: www.jogeen.top
	 * @date 2018年4月28日
	 * @return base64编码字符串
	 */
	public static String getImageStr(InputStream imgFileInputStream) {
		byte[] data = null;
		try {
			data = new byte[imgFileInputStream.available()];
			imgFileInputStream.read(data);
			imgFileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

}
