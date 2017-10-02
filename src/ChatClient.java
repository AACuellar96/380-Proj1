
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class ChatClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            BufferedReader brIS = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            System.out.print("Please enter your username:");
            String inp;
            String name = br.readLine().trim();
            String sMessage;
            while((inp=brIS.readLine())!=null){
                out.println(inp);
                sMessage=br.readLine();
                if(sMessage.equals("Name in use.")) {
                    break;
                }
                else{
                    System.out.println(sMessage);
                }
                System.out.print("Your message:");
            }
            is.close();
            isr.close();
            br.close();
            brIS.close();
            socket.close();
        }
    }
}
