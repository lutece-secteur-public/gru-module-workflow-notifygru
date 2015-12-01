/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;
/**
 * 
 * @author 
 *
 */
public class NotificationFlux
{
    private String _strUserGuid;
    private String _strLabelUserEmail;
    private String _strNotificationType;
    private String _strIdDemand;
    private String _strIdDemandType;
    private String _strDemandMaxStep;
    private String _strDemandUserCurrentStep;
    private UserDashboard _userDashboard;
    private UserEmail _userEmail;
    private UserSms _userSms;
    private BackofficeLogging _backofficeLogging;

    /**
     * @return the _strUserGuid
     */
    public String getUserGuid(  )
    {
        return _strUserGuid;
    }

    /**
     * @param strUserGuid the _strUserGuid to set
     */
    public void setUserGuid( String strUserGuid )
    {
        this._strUserGuid = strUserGuid;
    }

    /**
     * @return the _strLabelUserEmail
     */
    public String getLabelUserEmail(  )
    {
        return _strLabelUserEmail;
    }

    /**
     * @param strLabelUserEmail the _strLabelUserEmail to set
     */
    public void setLabelUserEmail( String strLabelUserEmail )
    {
        this._strLabelUserEmail = strLabelUserEmail;
    }

    /**
     * @return the _strNotificationType
     */
    public String getNotificationType(  )
    {
        return _strNotificationType;
    }

    /**
     * @param strNotificationType the _strNotificationType to set
     */
    public void setNotificationType( String strNotificationType )
    {
        this._strNotificationType = strNotificationType;
    }

    /**
     * @return the _strIdDemand
     */
    public String getIdDemand(  )
    {
        return _strIdDemand;
    }

    /**
     * @param strIdDemand the _strIdDemand to set
     */
    public void setIdDemand( String strIdDemand )
    {
        this._strIdDemand = strIdDemand;
    }

    /**
     * @return the _strIdDemandType
     */
    public String getIdDemandType(  )
    {
        return _strIdDemandType;
    }

    /**
     * @param strIdDemandType the _strIdDemandType to set
     */
    public void setIdDemandType( String strIdDemandType )
    {
        this._strIdDemandType = strIdDemandType;
    }

    /**
     * @return the _strDemandMaxStep
     */
    public String getDemandMaxStep(  )
    {
        return _strDemandMaxStep;
    }

    /**
     * @param strDemandMaxStep the _strDemandMaxStep to set
     */
    public void setDemandMaxStep( String strDemandMaxStep )
    {
        this._strDemandMaxStep = strDemandMaxStep;
    }

    /**
     * @return the _strDemandUserCurrentStep
     */
    public String getDemandUserCurrentStep(  )
    {
        return _strDemandUserCurrentStep;
    }

    /**
     * @param strDemandUserCurrentStep the _strDemandUserCurrentStep to set
     */
    public void setDemandUserCurrentStep( String strDemandUserCurrentStep )
    {
        this._strDemandUserCurrentStep = strDemandUserCurrentStep;
    }

    /**
     * @return the userDashboard
     */
    public UserDashboard getUserDashboard(  )
    {
        return _userDashboard;
    }

    /**
     * @param userDashboard the _userDashboard to set
     */
    public void setUserDashboard( UserDashboard userDashboard )
    {
        this._userDashboard = userDashboard;
    }

    /**
     * @return the userEmail
     */
    public UserEmail getUserEmail(  )
    {
        return _userEmail;
    }

    /**
     * @param userEmail the _userEmail to set
     */
    public void setUserEmail( UserEmail userEmail )
    {
        this._userEmail = userEmail;
    }

    /**
     * @return the _userSms
     */
    public UserSms getUserSms(  )
    {
        return _userSms;
    }

    /**
     * @param userSms the userSms to set
     */
    public void setUserSms( UserSms userSms )
    {
        this._userSms = userSms;
    }

    /**
     * @return the backofficeLogging
     */
    public BackofficeLogging getBackofficeLogging(  )
    {
        return _backofficeLogging;
    }

    /**
     * @param backofficeLogging the backofficeLogging to set
     */
    public void setBackofficeLogging( BackofficeLogging backofficeLogging )
    {
        this._backofficeLogging = backofficeLogging;
    }
}
