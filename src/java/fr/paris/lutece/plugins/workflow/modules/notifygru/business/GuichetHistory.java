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
 * This is the business class for the object GuichetHistory
 */
public class GuichetHistory
{
    // Variables declarations 
    private String _strMessageGuichet;
    private String _strStatustextGuichet;
    private String _strSenderNameGuichet;
    private String _strSubjectGuichet;
    private int _nDemandMaxStepGuichet;
    private int _nDemandUserCurrentStepGuichet;
    private boolean _activeOngletGuichet;

    /**
        * Returns the MessageGuichet
        * @return The MessageGuichet
        */
    public String getMessageGuichet(  )
    {
        return _strMessageGuichet;
    }

    /**
     * Sets the MessageGuichet
     * @param strMessageGuichet The MessageGuichet
     */
    public void setMessageGuichet( String strMessageGuichet )
    {
        _strMessageGuichet = strMessageGuichet;
    }

    /**
     * Returns the StatustextGuichet
     * @return The StatustextGuichet
     */
    public String getStatustextGuichet(  )
    {
        return _strStatustextGuichet;
    }

    /**
     * Sets the StatustextGuichet
     * @param strStatustextGuichet The StatustextGuichet
     */
    public void setStatustextGuichet( String strStatustextGuichet )
    {
        _strStatustextGuichet = strStatustextGuichet;
    }

    /**
     * Returns the SenderNameGuichet
     * @return The SenderNameGuichet
     */
    public String getSenderNameGuichet(  )
    {
        return _strSenderNameGuichet;
    }

    /**
     * Sets the SenderNameGuichet
     * @param strSenderNameGuichet The SenderNameGuichet
     */
    public void setSenderNameGuichet( String strSenderNameGuichet )
    {
        _strSenderNameGuichet = strSenderNameGuichet;
    }

    /**
     * Returns the SubjectGuichet
     * @return The SubjectGuichet
     */
    public String getSubjectGuichet(  )
    {
        return _strSubjectGuichet;
    }

    /**
     * Sets the SubjectGuichet
     * @param strSubjectGuichet The SubjectGuichet
     */
    public void setSubjectGuichet( String strSubjectGuichet )
    {
        _strSubjectGuichet = strSubjectGuichet;
    }

    /**
     * Returns the DemandMaxStepGuichet
     * @return The DemandMaxStepGuichet
     */
    public int getDemandMaxStepGuichet(  )
    {
        return _nDemandMaxStepGuichet;
    }

    /**
     * Sets the DemandMaxStepGuichet
     * @param nDemandMaxStepGuichet The DemandMaxStepGuichet
     */
    public void setDemandMaxStepGuichet( int nDemandMaxStepGuichet )
    {
        _nDemandMaxStepGuichet = nDemandMaxStepGuichet;
    }

    /**
     * Returns the DemandUserCurrentStepGuichet
     * @return The DemandUserCurrentStepGuichet
     */
    public int getDemandUserCurrentStepGuichet(  )
    {
        return _nDemandUserCurrentStepGuichet;
    }

    /**
     * Sets the DemandUserCurrentStepGuichet
     * @param nDemandUserCurrentStepGuichet The DemandUserCurrentStepGuichet
     */
    public void setDemandUserCurrentStepGuichet( int nDemandUserCurrentStepGuichet )
    {
        _nDemandUserCurrentStepGuichet = nDemandUserCurrentStepGuichet;
    }

    /**
     * Returns the ActiveOngletGuichet
     * @return The ActiveOngletGuichet
     */
    public boolean isActiveOngletGuichet(  )
    {
        return _activeOngletGuichet;
    }

    /**
        * Returns the ActiveOngletGuichet
        * @return The ActiveOngletGuichet
        */
    public boolean getActiveOngletGuichet(  )
    {
        return _activeOngletGuichet;
    }

    /**
     * Sets the ActiveOngletGuichet
     * @param activeOngletGuichet The ActiveOngletGuichet
     */
    public void setActiveOngletGuichet( boolean activeOngletGuichet )
    {
        _activeOngletGuichet = activeOngletGuichet;
    }
}
