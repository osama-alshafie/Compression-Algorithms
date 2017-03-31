package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;


public class Main {


	public static Vector <String> full_primary_Dictionary() {
		Vector < String > PV = new Vector<String>();
		for( int i=0; i<128; i++ ) {
			char ch = (char)i;
			PV.add( Character.toString(ch));
		}
		return PV;
	}

	public static Vector<Tage> compression(String os , Vector<String> vo ) {
		Vector<Tage> VT=new Vector<Tage>();
		for( int i=0 ; i<os.length() ; i++ ) {
			Tage T=new Tage();
			int count = 0;
			int j;
			String str =new String();
			str=Character.toString(os.charAt(i));
			for( j=i+1;j<os.length();j++ ) {
				if(vo.contains( str + os.charAt(j) ) ) {
					str +=os.charAt(j);
					if( j==(os.length()-1) ) {
						T.setPointer(vo.indexOf(str));
						VT.add(T);
					}
				}
				else {
					vo.add( str+os.charAt(j));
					T.setPointer(vo.indexOf(str));
					VT.add(T);
					break;
				}
			}
			count=(j-i)-1;
			i+=count;
		}
		return VT;
	}

	public static String decompression( Vector<Tage> vt ) {
		Vector<String> D=new Vector<String>();
		D=full_primary_Dictionary();
		String s=new String();
		for( int i=0 ; i<vt.size() ; i++ ) {
			if(vt.elementAt(i).getPointer()<D.size()) {
				s+=D.elementAt(vt.elementAt(i).getPointer());
				String str=new String();
				str=Character.toString((D.elementAt(vt.elementAt(i).getPointer()).charAt(0)));
				for( int j=i-1 ; j>=0 ; j-- ) {
					if(D.contains( D.elementAt(vt.elementAt(j).getPointer())+str ))
						str=D.elementAt(vt.elementAt(j).getPointer())+str;
					else {
						str=D.elementAt(vt.elementAt(j).getPointer())+str;
						D.add(str);
						break;
					}
				}
			}
			else {
				String str2= new String();
				str2=D.elementAt(vt.elementAt(i-1).getPointer())+D.elementAt(vt.elementAt(i-1).getPointer()).charAt(0);
				D.add(str2);
				s+=str2;
			}
		}
		//System.out.println(" This is The Dictionary After Decompression-: ");
		//for(int c=0;c<D.size();c++)
			//System.out.println(D.elementAt(c));
		return s;
	}

	public static void main(String[] args) {
		 GUI form1;
		    form1=new GUI();
		    form1.setSize(363, 538);
		    form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    form1.setVisible(true);



		Vector < Tage > tv=new Vector<Tage>();
		Vector < String > V = new Vector<String>();
		String input= new String();
		// Enter The Text To compress-:
		BufferedReader br=null;
		try{
			br= new BufferedReader( new FileReader("D:\\studying\\java\\osama.txt"));
			input=br.readLine();
			//System.out.println(input);
			V= full_primary_Dictionary();
			tv=compression(input,V);
			System.out.println(" This is The Tages-:");
			for(int k=0;k<tv.size(); k++)
				System.out.println(tv.get(k).getPointer());

		} catch (IOException e) {
		e.printStackTrace();
	     }

		//write The tages in output file
		try {
			File file = new File("D:\\studying\\java\\output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int k=0;k<tv.size(); k++)
				bw.write(tv.get(k).getPointer());
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

		//print the text after extract decompression
		System.out.println(" This is the text after extract decompression-: ");
		String o=new String();
		o=decompression(tv);
		System.out.println(o);
	}

}
