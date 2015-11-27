package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

public class UserSms {
	private String _strPhoneNumber;
	private String _strMessage;
	/**
	 * @return the _strPhoneNumber
	 */
	public String getPhoneNumber() {
		return _strPhoneNumber;
	}
	/**
	 * @param _strPhoneNumber the _strPhoneNumber to set
	 */
	public void setPhoneNumber(String _strPhoneNumber) {
		this._strPhoneNumber = _strPhoneNumber;
	}
	/**
	 * @return the _strMessage
	 */
	public String getMessage() {
		return _strMessage;
	}
	/**
	 * @param _strMessage the _strMessage to set
	 */
	public void setMessage(String _strMessage) {
		this._strMessage = _strMessage;
	}
	
}
