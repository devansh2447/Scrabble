import java.io.*;
import java.net.*;

/**
 * Write a description of class Utils here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Utils
{
    public static String getPathForReference(){
        return getRootDirectory() + "/words.txt";
    }

    public static void write(String path, String content) throws IOException, FileNotFoundException {
        // content = processFile(path) + content;   
        FileWriter fw = new FileWriter(path);    
        fw.write(content);
        fw.close();
    }

    public static String getRootDirectory(){
        String os = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        if(os.contains("Windows")){
            return "C:/Users/" + user + "/scrabble";
        }
        else if(os.contains("Mac")){
            return "/Users/" + user + "/scrabble";
        }
        else{
            return "/home/users/" + user + "/scrabble";
        }
    }

    public static void createDirectory(String path){
        new File(path).mkdirs();
    }

    //credits for this method: https://stackoverflow.com/a/10710115
    //helped me find an efficient way to read the full dictionary file
    public static String processFile(String name) throws IOException{
        String filename = name;
        String content = null;
        File file = new File(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return "\n"  + content + "\r";
    }

    public static void saveFile(String path, String url){
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream file = new FileOutputStream(path)) {
            byte data[] = new byte[2^20];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 2^20)) != -1) {
                file.write(data, 0, byteContent);
            }
        }
        catch (IOException e) {
        }
    }

    public static void createFile(String path) throws IOException {
        File toCreate = new File(path);
        toCreate.createNewFile();
    }

    public static void init() throws IOException {
        createDirectory(getRootDirectory());
        if(!exists(getPathForReference())){
            boolean isConnected = isInternetAvailable();
            if(isConnected ){
                saveFile(getPathForReference(), "https://raw.githubusercontent.com/devansh2447/Scrabble/master/words.txt");
            }
            else{
                System.out.println("System not connected to internet or site not reachable, so file cannot be updated."); 
                exit();
            }
        }
    }

    public static void exit(){
        System.out.flush();
        System.exit(0);
    }

    //code for checking connection from here: https://stackoverflow.com/a/30817677
    
    public static boolean isInternetAvailable() throws IOException{
        return isHostAvailable("github.com");
    }

    public static boolean isHostAvailable(String hostName) throws IOException{
        try(Socket socket = new Socket()){
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);
            return true;
        }
        catch(UnknownHostException unknownHost){
            return false;
        }
    }

    public static boolean exists(String path){
        File file = new File(path);
        return file.exists();
    }
}
