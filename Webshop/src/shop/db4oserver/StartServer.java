/*
 * author: Benjamin
 */

package shop.db4oserver;

import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBKeyword;
import shop.dto.DBOrder;
import shop.dto.DBTrack;

import com.db4o.*;
import com.db4o.constraints.UniqueFieldValueConstraint;
import com.db4o.cs.*;
import com.db4o.cs.config.*;
import com.db4o.messaging.*;

/**
* starts a db4o server with the settings from {@link ServerInfo}. <br>
* <br>
* This is a typical setup for a long running server. <br>
* <br>
* The Server may be stopped from a remote location by running StopServer. The
* StartServer instance is used as a MessageRecipient and reacts to receiving an
* instance of a StopServer object. <br>
* <br>
* Note that all user classes need to be present on the server side and that all
* possible Db4o.configure() calls to alter the db4o configuration need to be
* executed on the client and on the server.
*/
public class StartServer implements ServerInfo, MessageRecipient {
    /**
     * setting the value to true denotes that the server should be closed
     */
    private boolean stop = false;
    /**
     * starts a db4o server using the configuration from {@link ServerInfo}.
     */
    public static void main(String[] args){
    	System.out.println("Starting Server");
        new StartServer().runServer();
    }
    /**
     * opens the ObjectServer, and waits forever until close() is called or a
     * StopServer message is being received.
     */
    public void runServer() {
        synchronized (this) {
            ServerConfiguration config = Db4oClientServer.newServerConfiguration();
            
            config.common().messageLevel(0);
            
            // Klasse Address
            config.common().objectClass(DBAddress.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBAddress.class).cascadeOnDelete(true);
    		
    		// Klasse Album
    		config.common().objectClass(DBAlbum.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBAlbum.class).cascadeOnDelete(false);
    		
    		// Klasse Category
    		config.common().objectClass(DBCategory.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBCategory.class).cascadeOnDelete(true);
    		
    		// Klasse Customer
    		config.common().objectClass(DBCustomer.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBCustomer.class).cascadeOnDelete(true);
    		
    		// Klasse Items
    		config.common().objectClass(DBItems.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBItems.class).cascadeOnDelete(false);
    		

    		// Klasse Keyword
    		config.common().objectClass(DBKeyword.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBKeyword.class).cascadeOnDelete(false);
    		
    		// Klasse Order
    		config.common().objectClass(DBOrder.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBOrder.class).cascadeOnDelete(true);


    		// Klasse Track
    		config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
    		config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
    		
    		config.common().activationDepth(5);
    		
            
            // Using the messaging functionality to redirect all
            // messages to this.processMessage
            config.networking().messageRecipient(this);
            
            //Öffnen des DB4O-Server
            ObjectServer db4oServer = Db4oClientServer.openServer(config, FILE, PORT);
            db4oServer.grantAccess(USER, PASS);
            
            System.out.println("DB-File: " + FILE + " Port:" +  PORT);
            
            // to identify the thread in a debugger
            Thread.currentThread().setName(this.getClass().getName());
            
            // We only need low priority since the db4o server has
            // it's own thread.
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            
            try {
                if (!stop) {
                    // wait forever for notify() from close()
                    this.wait(Long.MAX_VALUE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db4oServer.close();
        }
    }
    /**
     * messaging callback
     *
     * @see com.db4o.messaging.MessageRecipient#processMessage(MessageContext,
     *      Object)
     */
    public void processMessage(MessageContext con, Object message) {
        if (message instanceof StopServer) {
            close();
        }
    }
    /**
     * closes this server.
     */
    public void close() {
        synchronized (this) {
            stop = true;
            this.notify();
        }
    }
}
