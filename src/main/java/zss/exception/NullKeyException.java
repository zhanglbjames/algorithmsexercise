package zss.exception;

/**
* @author zhanglbjames@163.com
*/
public class NullKeyException extends Exception{

	private static final long serialVersionUID = 7295130746383289034L;

	NullKeyException(){}

	NullKeyException(String message){
		super(message);
	}

}
