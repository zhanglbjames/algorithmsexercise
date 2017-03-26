package zss.exception;

/**
* @author zhanglbjames@163.com
*/
public class KeyExceedException extends Exception {

	private static final long serialVersionUID = 5503507013717097354L;
	
	KeyExceedException(){};
	
	KeyExceedException(String message){
		super(message);
	}

}
