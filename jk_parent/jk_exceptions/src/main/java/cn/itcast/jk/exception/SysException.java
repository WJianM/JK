package cn.itcast.jk.exception;

public class SysException extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}


	// 提供一个有参的构造方法
	public SysException(String message) {
		this.message = message;
	}
}
