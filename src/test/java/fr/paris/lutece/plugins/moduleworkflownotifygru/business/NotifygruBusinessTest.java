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

package fr.paris.lutece.plugins.moduleworkflownotifygru.business;

import fr.paris.lutece.plugins.workflow.module.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.module.notifygru.business.NotifygruHome;
import fr.paris.lutece.test.LuteceTestCase;


public class NotifygruBusinessTest extends LuteceTestCase
{
    private final static String TITLE1 = "Title1";
    private final static String TITLE2 = "Title2";
    private final static int IDTASK1 = 1;
    private final static int IDTASK2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        TaskNotifyGruConfig notifygru = new TaskNotifyGruConfig();
        notifygru.setTitle( TITLE1 );
        notifygru.setIdTask( IDTASK1 );

        // Create test
        NotifygruHome.create( notifygru );
        TaskNotifyGruConfig notifygruStored = NotifygruHome.findByPrimaryKey( notifygru.getId( ) );
        assertEquals( notifygruStored.getTitle() , notifygru.getTitle( ) );
        assertEquals( notifygruStored.getIdTask() , notifygru.getIdTask( ) );

        // Update test
        notifygru.setTitle( TITLE2 );
        notifygru.setIdTask( IDTASK2 );
        NotifygruHome.update( notifygru );
        notifygruStored = NotifygruHome.findByPrimaryKey( notifygru.getId( ) );
        assertEquals( notifygruStored.getTitle() , notifygru.getTitle( ) );
        assertEquals( notifygruStored.getIdTask() , notifygru.getIdTask( ) );

        // List test
        NotifygruHome.getNotifygrusList();

        // Delete test
        NotifygruHome.remove( notifygru.getId( ) );
        notifygruStored = NotifygruHome.findByPrimaryKey( notifygru.getId( ) );
        assertNull( notifygruStored );
        
    }

}