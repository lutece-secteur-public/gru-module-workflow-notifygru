package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

public class Validator {
	/**
	 * verify if an email address is correct
	 * @param email
	 * @return true if the email address is correct false otherwise
	 */
	public static boolean isEmailValid( String email ) 
	{
		try{
			if (email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) 
			{
	            return true;
			}
		}
		catch(Exception e){
			System.out.println("Email non valide");
		}
		return false;	
    }
	
	/**
	 * verify if the length of a SMS don't exceed 160 characters
	 * @param sms
	 * @return true if the length of a SMS don't exceed 160 characters otherwise false
	 */
	public static boolean isSMSvalid( String sms )  
	{
        if (sms.length() <= 160) {
            return true;
        }
        return false;
    }
	
	/**
	 * verify if all email address in a list are correct
	 * @param strRecipient
	 * @return true if all email address are correct otherwise false
	 */
	public static boolean isRecipientCcValid(String strRecipient)
	{	
		String[] emails= strRecipient.split(";");
		for(int i=0;i<emails.length;i++)
		{
			if(isEmailValid(emails[i]))
			{
				return true;
			}
			else
			{
				break;
			}
		}
		return false;
	}
	/**
	 * verify if the telephone number is valid 
	 * @param strNumTel
	 * @return true if the telephone number is valid false otherwise
	 */
	public static boolean isTelephonNumberValid(String strNumTel)
	{
		if(strNumTel.matches( "(0|\\+33|0033)[1-9][0-9]{8}" ))
		{
			return true;
		}
		return false;
	}
}
