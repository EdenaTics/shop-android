package mg.edena.shop.model.api;

public class ResultBean {
	private int status;
	private String mess;
	private String result;

	public ResultBean(){}

	public ResultBean(int status, String mess, String result) {
		this.status = status;
		this.mess = mess;
		this.result = result;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
