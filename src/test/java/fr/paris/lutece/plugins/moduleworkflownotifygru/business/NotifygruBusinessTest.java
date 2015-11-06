package fr.paris.lutece.plugins.moduleworkflownotifygru.business;

import fr.paris.lutece.plugins.workflow.module.notifygru.business.TaskNotifyGruConfig;
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
        notifygru.setLabelLink(TITLE1 );
        notifygru.setIdTask( IDTASK1 );

      
        
    }

}