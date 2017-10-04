
import java.io.*;
import java.net.Socket;

public final class ChatClient {

    public static void main(String[] args) throws Exception {

        try (Socket socket = new Socket("18.221.102.182",38001)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            BufferedReader brIS = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            System.out.println("Please enter your username:");
            String inp;
            String sMessage;
            out.println(brIS.readLine());
            sMessage=br.readLine();
            if(sMessage.equals("Name in use.")) {
                return;
            }

            Runnable listen = () ->{
                String sMess;
                while(true){
                    try{
                        sMess=br.readLine();
                        System.out.println(sMess);
                    }
                    catch (IOException e){

                    }
                }
            };

            listen.run();
            while((inp=brIS.readLine())!=null){
                out.println(inp);
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
