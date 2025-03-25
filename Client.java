import java.io.*;
import java.net.*;

class Client{
  public static void main(String[] args){
    if(args.length != 1){
      System.err.println("error : run program as 'java Client <filepath>'");
      return;
    }
    //declare io components
    Socket socket = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    int exitCode = 0;
    try{
      socket = new Socket("127.0.0.1", 8000);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(args[0]);
        String statusCode = reader.readLine();
        switch(statusCode){
          case "404": System.out.println("error 404 : file not found");
            break;
          case "200":System.out.println("status : " + statusCode + "ok");
             writer.println("ok");
            String context;
            while((context = reader.readLine()) != null){
              System.out.println(context);
            }
            break;
          default : System.out.println("error : internal server issue"); 
        }
        
    }catch(IOException e){
      System.err.println("unexpected error : " + e.getMessage());
      exitCode = -1;
    }finally{
      try{
        if(reader != null){
          reader.close();
        }
        if(writer != null){
          writer.close();
        }
        if(socket != null && !socket.isClosed()){
          socket.close();
        }
      }catch(IOException e){
        System.out.println("error : " + e.getMessage());
      }
      System.out.println("exitcode : " + exitCode);
    }
  }
}