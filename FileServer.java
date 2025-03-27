
import java.net.Serversocket;
import java.net.Socket;


class FileServer{
  public static void main(String[] args){
    ServerSocket serverSocket = null;
    try{  
      serverSocket = new ServerSocket(8000);
        System.out.println("server listening on port 8000!");
      while(true){
      Socket  clientSocket = serverSocket.accept();
       new ClientHandler(clientSocket).handle();
      }
    }catch(IOException e){
      System.out.println("error : " + e.getMessage());
    }finally{
      try{
        if(!serverSocket.isClosed()){
          serverSocket.close();
        }
      }catch(IOException e){
          System.out.println("error : " + e.getMessage());
      }
    }
  }
}