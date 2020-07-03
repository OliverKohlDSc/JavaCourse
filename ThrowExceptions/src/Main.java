import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

	static class Parent {
		public Parent() {
			System.out.println("An instance of the parent class has been created!");
		}
	}
	
	final static class Child extends Parent {
		public Child() {
			System.out.println("An instance of the child class has been created!");
		}
		
		public void print() {
			System.out.println("HELLO!");
		}
	}
	
	static List<String> myNumberList;
	
	public static void main(String[] args) {
		// ClassCastException, NullPointerException, NumberFormatException, IndexOutOfBoundsException,
		// ArithmeticException, ArrayIndexOutOfBoundsException, IOException
		classCastExceptionDemo();
		nullPointerExceptionDemo();
		numberFormatExceptionDemo();
		//indexOutOfBoundsExceptionDemo();
		//arithmeticExceptionDemo();
		ioExceptionDemo();
	}
	
	private static void ioExceptionDemo() {
		// 1. Lesen einer Datei, welche in der Zwischenzeit gelöscht wurde / exisitierte niemals.
		// 2. Netzwerkshare, der in der Zwischenzeit disconnected wurde.
		// 3. Stream -> lesen, schließen den Stream -> lesen xxxx
		// 4. Wir haben die Berechtigung auf die Datei nicht.
		
		File file = new File("readMe.txt");
		FileInputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		// FileNotFoundException, EOFException, FileSystemException, SocketException, InterruptedIOException, ...
		
		//java.nio.file.NoSuchFileException
		//java.io.IOException
		
		// Java IO -> JDK 1.0
		// Java NIO -> JDK 1.4 (Java SE 7) -> JDK 1.7 NIO Non-blockion I/O (Input/Output - Eingabe Ausgabe)
	}
	
	@SuppressWarnings("unused")
	private static void arithmeticExceptionDemo() {
		int number1 = 30;
		int number2 = 0;
		
		//System.out.println(number1 / number2);
		
		// NaN
		//double r = Math.sqrt(-30);
		//System.out.println(r);
	}
	
	@SuppressWarnings("unchecked")
	private static void indexOutOfBoundsExceptionDemo() {
		// IndexOutOfBoundsException seit JDK 1.0
		//  erbt -> ArrayIndexOutOfBoundsException
		//  erbt -> StringIndexOutOfBoundsException
		
		// ArrayIndexOutOfBoundsException
		int myNumbers [];
		int[] myNumbers1 = new int[3];
		Integer[] myNumbers2 = new Integer[3];
		
		System.out.println(myNumbers2[0]);
		
		// ArrayIndexOutOfBoundsException
		long[] myLongArray = new long[1];
		myLongArray[0] = 345129352345L;
		System.out.println(myLongArray[myLongArray.length-1]);
		//int a = myNumbers2[0];
		
		// StringIndexOutOfBoundsException
		//String myFancyString = "TEST";
		//myFancyString.charAt(100);
		
		// IndexOutOfBoundsException
		//throw new IndexOutOfBoundsException("No pages in paper tray left");
		
		// IndexOutOfBoundsException
		@SuppressWarnings("rawtypes")
		List listWithoutGenerics = new ArrayList();
		listWithoutGenerics.add("String");
		listWithoutGenerics.get(70);
	}
	
	private static void numberFormatExceptionDemo() {
		String s = "CCCCCC";
		//int i = Integer.parseInt(s);
		//int i = Integer.parseInt(s, 16);
		//int i = Integer.parseInt("-FF", 16);
		//System.out.println(i);
		
		//System.out.println(Integer.decode("0x4d2")); // 1234 dezimal
		// System.out.println(Integer.decode("asdfasdfasdf0x4d2")); // exception
		//System.out.println(Integer.toHexString(1234)); // 0x4d2 hex
	}
	
	private static void classCastExceptionDemo() {
		/*
		Object o = new String();
		Integer i = (Integer)o;
		*/
		
		/*
		String s = "ABC";
		Integer i2 = s;
		*/
	
		/*
		Object obj = new Integer(100);
		System.out.println((String)obj);
		*/
		
		/*
		Parent p = new Parent();
		Child ch = new Child();
		ch.print();
		ch = (Child)p;
		*/
	}
	
	private static void nullPointerExceptionDemo() {
		// NullPointerException
		// -> Pointer NULL
		String s = "ABC";
		String s2 = s;
		
		String s3 = null;
		System.out.println(s3);
		
		//method1(s3);
		//method2(s3);
		//method3(s3);
		
		//myNumberList = new ArrayList<>();
		//myNumberList.add("ABC");
	}
	
	@SuppressWarnings("unused")
	private static void method1(String x) {
		try {
			System.out.println("First character: " + x.charAt(0));
		} catch (NullPointerException ex) {
			System.out.println("NullPointerException thrown!");
		}
	}
	
	@SuppressWarnings("unused")
	private static void method2(String x) {
		if (x == null) {
			System.out.println("NULL!");
		}
		else {
			System.out.println("Inhalt: " + x);
		}
	}
	
	@SuppressWarnings("unused")
	private static void method3(String x) {
		String myContent = Objects.requireNonNull(x);
	}
}
