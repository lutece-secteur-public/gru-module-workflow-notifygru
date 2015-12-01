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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

/**
 * 
 * @author 
 *
 */
public class Resource
{
    private int _nId;
    private String _strIdUser;
    private String _strEmail;
    private String _strNotificationType;
    private int _nIdDemand;
    private int _nIdDemandType;
    private int _nDemandMaxStep;
    private int _nDemandUserCurrentStep;
    private String _strStatusText;
    private int _nIdStatusCrm;
    private String _strPhoneNumber;
    private String _strLastName;
    private String _strFirstName;

    /**
     * @return the _nId
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * @param nId the _nId to set
     */
    public void setId( int nId )
    {
        this._nId = nId;
    }

    /**
     * @return the _strIdUser
     */
    public String getIdUser(  )
    {
        return _strIdUser;
    }

    /**
     * @param strIdUser the _strIdUser to set
     */
    public void setIdUser( String strIdUser )
    {
        this._strIdUser = strIdUser;
    }

    /**
     * @return the _strEmail
     */
    public String getEmail(  )
    {
        return _strEmail;
    }

    /**
     * @param strEmail the _strEmail to set
     */
    public void setEmail( String strEmail )
    {
        this._strEmail = strEmail;
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
     * @return the _nIdDemand
     */
    public int getIdDemand(  )
    {
        return _nIdDemand;
    }

    /**
     * @param nIdDemand the _nIdDemand to set
     */
    public void setIdDemand( int nIdDemand )
    {
        this._nIdDemand = nIdDemand;
    }

    /**
     * @return the _nIdDemandType
     */
    public int getIdDemandType(  )
    {
        return _nIdDemandType;
    }

    /**
     * @param nIdDemandType the _nIdDemandType to set
     */
    public void setIdDemandType( int nIdDemandType )
    {
        this._nIdDemandType = nIdDemandType;
    }

    /**
     * @return the _nDemandMaxStep
     */
    public int getDemandMaxStep(  )
    {
        return _nDemandMaxStep;
    }

    /**
     * @param nDemandMaxStep the _nDemandMaxStep to set
     */
    public void setDemandMaxStep( int nDemandMaxStep )
    {
        this._nDemandMaxStep = nDemandMaxStep;
    }

    /**
     * @return the _nDemandUserCurrentStep
     */
    public int getDemandUserCurrentStep(  )
    {
        return _nDemandUserCurrentStep;
    }

    /**
     * @param nDemandUserCurrentStep the _nDemandUserCurrentStep to set
     */
    public void setDemandUserCurrentStep( int nDemandUserCurrentStep )
    {
        this._nDemandUserCurrentStep = nDemandUserCurrentStep;
    }

    /**
     * @return the _strStatusText
     */
    public String getStatusText(  )
    {
        return _strStatusText;
    }

    /**
     * @param strStatusText the _strStatusText to set
     */
    public void setStatusText( String strStatusText )
    {
        this._strStatusText = strStatusText;
    }

    /**
     * @return the _nIdStatusCrm
     */
    public int getIdStatusCrm(  )
    {
        return _nIdStatusCrm;
    }

    /**
     * @param nIdStatusCrm the _nIdStatusCrm to set
     */
    public void setIdStatusCrm( int nIdStatusCrm )
    {
        this._nIdStatusCrm = nIdStatusCrm;
    }

    /**
     * @return the _strPhoneNumber
     */
    public String getPhoneNumber(  )
    {
        return _strPhoneNumber;
    }

    /**
     * @param strPhoneNumber the _strPhoneNumber to set
     */
    public void setPhoneNumber( String strPhoneNumber )
    {
        this._strPhoneNumber = strPhoneNumber;
    }

    /**
     * @return the _strLastName
     */
    public String getLastName(  )
    {
        return _strLastName;
    }

    /**
     * @param strLastName the _strLastName to set
     */
    public void setLastName( String strLastName )
    {
        this._strLastName = strLastName;
    }

    /**
     * @return the _strFirstName
     */
    public String getFirstName(  )
    {
        return _strFirstName;
    }

    /**
     * @param strFirstName the _strFirstName to set
     */
    public void setFirstName( String strFirstName )
    {
        this._strFirstName = strFirstName;
    }
}
