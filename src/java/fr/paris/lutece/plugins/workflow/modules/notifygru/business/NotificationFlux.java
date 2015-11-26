package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

public class NotificationFlux {
	private String _strUserGuid;
	private String _strLabelUserEmail;
	private String _strNotificationType;
    private String _strIdDemand;			      	
    private String _strIdDemandType;			
    private String _strDemandMaxStep; 
    private String _strDemandUserCurrentStep;
	private UserDashboard userDashboard;
	private UserEmail userEmail;
	private UserSms userSms;
	private BackofficeLogging backofficeLogging;
	/**
	 * @return the _strUserGuid
	 */
	public String getUserGuid() {
		return _strUserGuid;
	}
	/**
	 * @param _strUserGuid the _strUserGuid to set
	 */
	public void setUserGuid(String _strUserGuid) {
		this._strUserGuid = _strUserGuid;
	}
	/**
	 * @return the _strUserEmail
	 */
	public String getLabelUserEmail() {
		return _strLabelUserEmail;
	}
	/**
	 * @param _strUserEmail the _strUserEmail to set
	 */
	public void setLabelUserEmail(String _strLabelUserEmail) {
		this._strLabelUserEmail = _strLabelUserEmail;
	}
	/**
	 * @return the _strNotificationType
	 */
	public String getNotificationType() {
		return _strNotificationType;
	}
	/**
	 * @param _strNotificationType the _strNotificationType to set
	 */
	public void setNotificationType(String _strNotificationType) {
		this._strNotificationType = _strNotificationType;
	}
	/**
	 * @return the _strIdDemand
	 */
	public String getIdDemand() {
		return _strIdDemand;
	}
	/**
	 * @param _strIdDemand the _strIdDemand to set
	 */
	public void setIdDemand(String _strIdDemand) {
		this._strIdDemand = _strIdDemand;
	}
	/**
	 * @return the _strIdDemandType
	 */
	public String getIdDemandType() {
		return _strIdDemandType;
	}
	/**
	 * @param _strIdDemandType the _strIdDemandType to set
	 */
	public void setIdDemandType(String _strIdDemandType) {
		this._strIdDemandType = _strIdDemandType;
	}
	/**
	 * @return the _strDemandMaxStep
	 */
	public String getDemandMaxStep() {
		return _strDemandMaxStep;
	}
	/**
	 * @param _strDemandMaxStep the _strDemandMaxStep to set
	 */
	public void setDemandMaxStep(String _strDemandMaxStep) {
		this._strDemandMaxStep = _strDemandMaxStep;
	}
	/**
	 * @return the _strDemandUserCurrentStep
	 */
	public String getDemandUserCurrentStep() {
		return _strDemandUserCurrentStep;
	}
	/**
	 * @param _strDemandUserCurrentStep the _strDemandUserCurrentStep to set
	 */
	public void setDemandUserCurrentStep(String _strDemandUserCurrentStep) {
		this._strDemandUserCurrentStep = _strDemandUserCurrentStep;
	}
	/**
	 * @return the userDashboard
	 */
	public UserDashboard getUserDashboard() {
		return userDashboard;
	}
	/**
	 * @param userDashboard the userDashboard to set
	 */
	public void setUserDashboard(UserDashboard userDashboard) {
		this.userDashboard = userDashboard;
	}
	/**
	 * @return the userEmail
	 */
	public UserEmail getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(UserEmail userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userSms
	 */
	public UserSms getUserSms() {
		return userSms;
	}
	/**
	 * @param userSms the userSms to set
	 */
	public void setUserSms(UserSms userSms) {
		this.userSms = userSms;
	}
	/**
	 * @return the backofficeLogging
	 */
	public BackofficeLogging getBackofficeLogging() {
		return backofficeLogging;
	}
	/**
	 * @param backofficeLogging the backofficeLogging to set
	 */
	public void setBackofficeLogging(BackofficeLogging backofficeLogging) {
		this.backofficeLogging = backofficeLogging;
	}
	
}
