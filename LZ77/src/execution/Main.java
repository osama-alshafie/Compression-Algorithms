package execution;

import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static Vector<Tage> compress( String mstring ) {
		Vector<Tage> vlist = new Vector<Tage>();
		for( int i=0 ; i<mstring.length() ; i++ ) {
			Tage T = new Tage();
			int ccount=0;
			int index=0;
			if( i==0 ) {
				T.setPointer(0);
				T.setLength(0);
				T.setNextchar( mstring.charAt(i) );
			}
			else {
				for( int j=i-1; j>=0; j-- ) {
					int count=0;
					//cv=j;
					if(mstring.charAt(j)==mstring.charAt(i)) {
						count++;
						T.setPointer(i-j);
						for( int c=j+1 ; c<i ;c++) {
							if( mstring.charAt(c) == mstring.charAt(i+1)) {
								count++;
								i++;
								int cv=i+1;
								if( (i+1)>=mstring.length()  )  {
									T.setNextchar( '\n' );
									T.setLength(count);
									break;
								}
							}
							else {
								T.setNextchar(mstring.charAt(i+1));
								T.setLength(count);
								break;
							}
						}
					}
					if(( count==0 ) && ( j==0 )) {
						T.setPointer(0);
						T.setLength(0);
						T.setNextchar( mstring.charAt(i) );
					}					//if((index==count)&& (cv>j))
						//T.setPointer(cv);
					//index=count;
					ccount= count;
				}
				for( int cc=0; cc<ccount; cc++ ) {
					i++;
				}
			}
			vlist.add(T);
			System.out.println( T.getPointer() + "|" +T.getLength() + "|" + T.getNextchar()  );
		}
		return vlist;
	}
	
	public static String decompress( Vector<Tage> M ) {
		String Ostr = new String();
		for(int i=0; i<M.size(); i++) {
			if( M.get(i).getPointer()==0 ) {
				Ostr+=M.get(i).getNextchar();
			}
			else {
				for( int j=M.get(i).getPointer(); j<M.get(i).getPointer()+M.get(i).getLength(); j++ ) {
					int c=Ostr.length()-M.get(i).getPointer();
					Ostr += Ostr.charAt(c);
				}
				Ostr+=M.get(i).getNextchar();
			}
		}
		return Ostr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Tage> V = new Vector<Tage>();
		String input = new String();
		Scanner in = new Scanner( System.in );
		System.out.println(" Please! Enter The Text To compress-: ");
		input = in.nextLine() ;
		System.out.println("This Is The Tages -:");
		V = compress(input);
		System.out.println("This Is The Original taxt -:");
		String n= new String();
		n=decompress(V);
		System.out.println( n );
		
		
	}

}
