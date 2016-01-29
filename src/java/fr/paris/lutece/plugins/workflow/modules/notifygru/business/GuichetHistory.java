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
 * GuichetHistory
 */
public class GuichetHistory
{
    private String _strMessageGuichet;
    private String _strStatustextGuichet;
    private String _strSenderNameGuichet;
    private String _strSubjectGuichet;
    private int _nDemandMaxStepGuichet;
    private int _nDemandUserCurrentStepGuichet;
    private int _nDemandStateGuichet;
    private String _strLevelNotificationGuichet;

    public String getMessageGuichet(  )
    {
        return _strMessageGuichet;
    }

    public void setMessageGuichet( String strMessageGuichet )
    {
        this._strMessageGuichet = strMessageGuichet;
    }

    public String getStatustextGuichet(  )
    {
        return _strStatustextGuichet;
    }

    public void setStatustextGuichet( String strStatustextGuichet )
    {
        this._strStatustextGuichet = strStatustextGuichet;
    }

    public String getSenderNameGuichet(  )
    {
        return _strSenderNameGuichet;
    }

    public void setSenderNameGuichet( String strSenderNameGuichet )
    {
        this._strSenderNameGuichet = strSenderNameGuichet;
    }

    public String getSubjectGuichet(  )
    {
        return _strSubjectGuichet;
    }

    public void setSubjectGuichet( String strSubjectGuichet )
    {
        this._strSubjectGuichet = strSubjectGuichet;
    }

    public int getDemandMaxStepGuichet(  )
    {
        return _nDemandMaxStepGuichet;
    }

    public void setDemandMaxStepGuichet( int nDemandMaxStepGuichet )
    {
        this._nDemandMaxStepGuichet = nDemandMaxStepGuichet;
    }

    public int getDemandUserCurrentStepGuichet(  )
    {
        return _nDemandUserCurrentStepGuichet;
    }

    public void setDemandUserCurrentStepGuichet( int nDemandUserCurrentStepGuichet )
    {
        this._nDemandUserCurrentStepGuichet = nDemandUserCurrentStepGuichet;
    }

    public int getDemandStateGuichet(  )
    {
        return _nDemandStateGuichet;
    }

    public void setDemandStateGuichet( int nDemandStateGuichet )
    {
        this._nDemandStateGuichet = nDemandStateGuichet;
    }

    public String getLevelNotificationGuichet(  )
    {
        return _strLevelNotificationGuichet;
    }

    public void setLevelNotificationGuichet( String strLevelNotificationGuichet )
    {
        this._strLevelNotificationGuichet = strLevelNotificationGuichet;
    }
}
