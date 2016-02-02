package jp._9684.sandbox;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author ikd9684
 *
 */
public class ClientExample {

    /** */
    private static PrintStream out = System.out;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Socket socket = null;
        OutputStream os = null;
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            byte[] message = args[2].getBytes();

            socket = new Socket(host, port);

            os = socket.getOutputStream();
            os.write(message);
            os.flush();
        }
        catch (IndexOutOfBoundsException e) {
            println("args[0]:host, args[1]:port, args[2]:message");
        }
        catch (NumberFormatException e) {
            println("ポート番号は数字で指定してください。");
        }
        catch (Exception e) {
            println(e);
        }
        finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }
            catch (Exception e) {
                // nothing
            }
        }
    }

    /**
    *
    * @param message
    */
   protected static void println(Object message) {
       out.println(message);
   }
}
