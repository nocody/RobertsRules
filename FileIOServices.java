import java.util.*;
import java.io.*;
public class FileIOServices {
	public static ObjectInputStream read;
	public static ObjectOutputStream write;
	
//	public static void openReadFile(String fileName) {
//		
//		try {
//			read = new ObjectInputStream(new FileInputStream(fileName));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}//close openReadFile
	
	public static void openReadFile() {
		
		try {
			read = new ObjectInputStream(new FileInputStream("minutes.bin"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//close openReadFile
	public static void openWriteFile(String fileName) {
		
		try {
			write = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}//close openWriteFile
	
	public static Object loadObject() {
		Object t = null;
		try {
			 t = (Object) read.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}//close readObject
	
//	public static Agenda loadAgenda(String fileName) {
//		Agenda t = null;
//		openReadFile(fileName);
//		try {
//			 t = (Agenda) read.readObject();
//		} catch (ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return t;
//	}//close readObject
	
	public static Agenda loadAgenda() {
		Agenda t = null;
		openReadFile();
		try {
			 t = (Agenda) read.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeReadFile();
		return t;
	}//close readObject
	
	public static void writeObject(Object obj) {
		try {
			write.writeObject(obj);
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Tree saved.");
	}//close writeObject
	
	public static void saveAgenda(Agenda a) {
		try {
			write.writeObject(a);
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		//System.out.printl
	
	public static void closeReadFile() {
		try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//close closeReadFile
	
	public static void closeWriteFile() {
		try {
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//close closeWriteFile
}
