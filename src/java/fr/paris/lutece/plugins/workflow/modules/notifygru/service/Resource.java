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

public class Resource
{
    private int _nId;
    private String _strIdUser;
    private String _strEmail;
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
     * @param _nId the _nId to set
     */
    public void setId( int _nId )
    {
        this._nId = _nId;
    }

    /**
     * @return the _strLastName
     */
    public String getLastName(  )
    {
        return _strLastName;
    }

    /**
     * @param _strLastName the _strLastName to set
     */
    public void setLastName( String _strLastName )
    {
        this._strLastName = _strLastName;
    }

    /**
     * @return the _strFistName
     */
    public String getFirstName(  )
    {
        return _strFirstName;
    }

    /**
     * @param _strFistName the _strFistName to set
     */
    public void setFirstName( String _strFirstName )
    {
        this._strFirstName = _strFirstName;
    }

    /**
     * @return the _nIdUser
     */
    public String getIdUser(  )
    {
        return _strIdUser;
    }

    /**
     * @param _nIdUser the _nIdUser to set
     */
    public void setIdUser( String _strIdUser )
    {
        this._strIdUser = _strIdUser;
    }

    /**
     * @return the _strEmail
     */
    public String getEmail(  )
    {
        return _strEmail;
    }

    /**
     * @param _strEmail the _strEmail to set
     */
    public void setEmail( String _strEmail )
    {
        this._strEmail = _strEmail;
    }

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
    
}