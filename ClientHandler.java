import java.io.*;
import java.net.*;

 class ClientHandler{
  private Socket ClientSocket;
  private String statusCode;
  private String filePath;
  private BufferedReader reader;
  private PrintWriter writer;
  private BufferedReader fileReader;
  
  public ClientHandler(Socket socket){
    this.ClientSocket = socket;
  }
  
  public void handle(){
    try{
      reader = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
      writer = new PrintWriter(new OutputStreamWriter((ClientSocket.getOutputStream()), true);
      filePath = reader.readLine();
      File file = new File(filePath);
      
      if((!file.exists()) || (file.isDirectory())){
        statusCode = "404";
        writer.println(statusCode);
        return;
      }
      statusCode = "200";
      writer.println(statusCode);
    private String response = reader.readLine();
     if(response.equals("ok")){
        try{
          fileReader = new BufferedReader(new FileReader(filePath));
        private  String context;
          while((context = fileReader.readLine()) != null){
            writer.println(context);
          }
        }catch(IOException e){
        System.out.println("error while reading file\n" + e.getMessage());
        }
     }
    }catch(IOException e){
      System.out.println("error : " + e.getMessage());
    }finally{
      try{
        if(reader != null){
          reader.close();
        }
        if(writer != null){
          writer.close();
        }
        if(fileReader != null){
          fileReader.close();
        }
        ClientSocket.close();
      }catch(IOException e){
        System.out.println("error : " + e.getMessage());
      }
    }
  }
}