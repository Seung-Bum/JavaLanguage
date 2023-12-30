package File;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Date;
public class FileUploadUtil {
	public static void copyUsingFiles(File source, File target) {
		try {
			Files.copy(source.toPath(), target.toPath());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	//f=abc.zip => f.indexOf('.') 3
	//f.substring(3) .zip
	//f.substring(0,3) abc
	public static String getNewFile(String f){
		String filename="";
		String fpost="";
		//String fre="";
		if(f.indexOf('.')>=0){
			fpost=f.substring(f.indexOf('.'));
			//fre=f.substring(0,f.indexOf('.'));
			filename=new Date().getTime()+fpost;
		}else{
			filename=new Date().getTime()+".back";
		}
		return filename;
	} 
	public static String getNewFile2(String f){
		String filename="";
		String fpost="";
		String fre="";
		if(f.indexOf('.')>=0){
			fpost=f.substring(f.indexOf('.'));
			fre=f.substring(0,f.indexOf('.'));
			filename=fre+new Date().getTime()+fpost;
		}else{
			filename=f+new Date().getTime()+".back";
		}
		return filename;
	} 
	public static String getNewjpg(String f){
		String filename="";
		String fpost="";
		String fre="";
		if(f.indexOf('.')>=0){
			fpost=f.substring(f.indexOf('.'));
			fre=f.substring(0,f.indexOf('.'));
			filename=fre+fpost;
		}else{
			filename=f+".jpg";
		}
		return filename;
	} 
	private static void copyUsingFileStreams(File source, File target){
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(target);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		}catch(IOException e){	
			System.out.println( e);
		}catch(Exception e){
			System.out.println( e);
		}
		finally {
			try {
				input.close();
				output.close();
			} catch (IOException e) {
			}
		}
	}
	public static void copyUsingFileChannels(File source, File target) {
		FileChannel input = null;
		FileChannel output = null;
		try {
			input = new FileInputStream(source).getChannel();
			output = new FileOutputStream(target).getChannel();
			output.transferFrom(input, 0, input.size());
		}catch(IOException e){	
			System.out.println( e);
		}catch(Exception e){
			System.out.println( e);
		} 
		finally {
			try {
				input.close();
				output.close();
			} catch (IOException e) {
			}
		}
	}
}
