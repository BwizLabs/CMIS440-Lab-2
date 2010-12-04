package russell_cmis440_lab2;

import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JOptionPane;


/**
* Program Name: CMIS440 Lab 2 Client/Server Word Length Counter
* @author Brandon R Russell
* @Course CMIS440
* Date: Nov 19, 2010
*/

/** Creates an ArrayBlockingQueue of FileStats. Buffer between client and server.
* Basically this class creates an ArrayBlockingQueue of FileStats with a length
* of 100. I tested different array lengths, but didn't see any difference in
* performance or stability so I decided 100 was a solid number to use. There is
* only a set and get method here that either puts a FileStats object onto the
* end of the Array or takes one from the front.
*|----------------------------------------------------------------------------|
*|                           CRC: Buffer                                      |
*|----------------------------------------------------------------------------|
*|Initially created and passed as reference              DfcClient            |
*|Fill buffer with FileStats object                      FileProcessor        |
*|Send FileStats object from Buffer to Server            FileStatsProcessor   |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates word counter threads to process file input
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/





public class Buffer implements IBuffer {

    private final ArrayBlockingQueue<FileStats> mySharedBuffer;
    private final int MYARRAYSIZE = 10;
    /**Buffer Constructor;initialize ArrayBlockingQueue
    * @TheCs Cohesion - Buffer Constructor;initialize ArrayBlockingQueue.
    * Completeness - Completely constructs buffer;initializes ArrayBlockingQueue
    * Convenience - Simply constructs buffer;initializes ArrayBlockingQueue.
    * Clarity - It is simple to understand that this is the buffer Constructor
    *           ;initializes ArrayBlockingQueue.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    */
    public Buffer(){
        
        mySharedBuffer = new ArrayBlockingQueue<FileStats>(MYARRAYSIZE);
        
    }

    /**Puts a new FileStats object onto the end of the ArrayBlockingQueue
    * @TheCs Cohesion - Puts a new FileStats object onto the end of the
    *                   ArrayBlockingQueue.
    * Completeness - Completely puts a new FileStats object onto the end of
    *                the ArrayBlockingQueue.
    * Convenience - Simply puts a new FileStats object onto the end of the
    *               ArrayBlockingQueue.
    * Clarity - It is simple to understand that this puts a new FileStats object
    *           onto the end of the ArrayBlockingQueue.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @param newFileStatBuffer a new FileStats object to put in queue.
    * @exception InterruptedException if thread is interrupted during put
    * @exception Exception general capture exception
    */
    public void set( FileStats newFileStatBuffer){
        try{
            mySharedBuffer.put(newFileStatBuffer);
        }catch (InterruptedException exception){
            JOptionPane.showMessageDialog(null,"Interrupted Exception "
                    + "on Buffer Construct.\n"
                    + exception.getMessage(),"Interrupted Exception",
                    JOptionPane.ERROR_MESSAGE);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Unknown Exception "
                    + "on Buffer Construct.\n"
                    + exception.getMessage(),"Unknown Exception",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**Takes a FileStats object from the front of the ArrayBlockingQueue
    * @TheCs Cohesion - Takes a FileStats object from the front of the
    *                   ArrayBlockingQueue.
    * Completeness - Completely takes a FileStats object from the front of the
    *                ArrayBlockingQueue.
    * Convenience - Simply takes a FileStats object from the front of the
    *               ArrayBlockingQueue.
    * Clarity - It is simple to understand that this takes a FileStats object
    *           from the front of the ArrayBlockingQueue.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @exception InterruptedException if thread is interrupted during take
    * @exception Exception general capture exception
    */
    public FileStats get(){
        FileStats tempFileStatsHolder = null;
        try{
            tempFileStatsHolder = mySharedBuffer.take();
            return tempFileStatsHolder;
        }catch (InterruptedException exception){
            JOptionPane.showMessageDialog(null,"Interrupted Exception "
                    + "on Buffer Construct.\n"
                    + exception.getMessage(),"Interrupted Exception",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Unknown Exception "
                    + "on Buffer Construct.\n"
                    + exception.getMessage(),"Unknown Exception",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    /**Returns current size of ArrayBlockingQueue. Used by DfcClient
    * @TheCs Cohesion - Returns current size of ArrayBlockingQueue. Used by
    *                   DfcClient.
    * Completeness - Completely returns current size of ArrayBlockingQueue.
    *                Used by DfcClient.
    * Convenience - Simply returns current size of ArrayBlockingQueue.
    *               Used by DfcClient.
    * Clarity - It is simple to understand that this returns current size of
    *           ArrayBlockingQueue. Used by DfcClient.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @exception Exception general capture exception
    */
    public int getBufferSize(){
        try{
            /**
             * This basically takes the entire size of the Array and subtracts
             * the remaining empty segments of it, thus giving the the number
             * of FileStats object currently in the Buffer. This is used by the
             * DfcClient object to hold the doInBackground method from finishing
             * . This is more for appearance than stability. I did not see less
             * data loss without this, however without the progress bar and
             * bytes received labels frequently did not show accurately.
             */
            return (mySharedBuffer.size() - mySharedBuffer.remainingCapacity());
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Unknown Exception "
                    + "on Buffer Construct.\n"
                    + exception.getMessage(),"Unknown Exception",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
    }

}
