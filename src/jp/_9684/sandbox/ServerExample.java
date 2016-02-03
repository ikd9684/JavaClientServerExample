package jp._9684.sandbox;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ikd9684
 *
 */
public class ServerExample {

    /** */
    private static PrintStream out = System.out;
    /** default port number */
    private static final int DEFAULT_PORT_NUMBER = 62571;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            int port;
            if (args.length == 0) {
                port = DEFAULT_PORT_NUMBER;
            }
            else {
                port = Integer.parseInt(args[0]);
            }

            serverSocket = new ServerSocket(port);

            println("サーバを開始しました。");
            println("ホスト名  : " + InetAddress.getLocalHost().getHostName());
            println("IPアドレス: " + InetAddress.getLocalHost().getHostAddress());
            println("ポート番号: " + port);
            println("[Ctrl]+[c]で停止します。");
            println("");

            while (true) {
                socket = serverSocket.accept();

                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                String line = br.readLine();
                println("受信[" + sdf.format(new Date()) + "] " + line);

                br.close();
                isr.close();
                is.close();
            }
        }
        catch (IndexOutOfBoundsException e) {
            println("args[0]:port (49152 - 65535)");
        }
        catch (NumberFormatException e) {
            println("ポート番号は数字で指定してください。");
        }
        catch (Exception e) {
            println(e);
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
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
