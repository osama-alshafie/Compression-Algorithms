

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;



public class Main {
	  public static int width=0;
	  public static int height=0;

	  public static int[][] readImage(String filePath)
	    {
	        File file=new File("lena.jpg");
	        BufferedImage image=null;
	        try
	        {
	            image=ImageIO.read(file);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	          width=image.getWidth();
	          height=image.getHeight();
	        int[][] pixels=new int[height][width];

	        for(int x=0;x<width;x++)
	        {
	            for(int y=0;y<height;y++)
	            {
	                int rgb=image.getRGB(x, y);
	                int alpha=(rgb >> 24) & 0xff;
	                int r = (rgb >> 16) & 0xff;
	                int g = (rgb >> 8) & 0xff;
	                int b = (rgb >> 0) & 0xff;

	                pixels[y][x]=r;
	            }
	        }

	        return pixels;
	    }

	    public static void writeImage(int[][] pixels,String outputFilePath)
	    {
	        File fileout=new File(outputFilePath);
	        BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

	        for(int x=0;x<width ;x++)
	        {
	            for(int y=0;y<height;y++)
	            {
	                image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
	            }
	        }
	        try
	        {
	            ImageIO.write(image2, "jpg", fileout);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    public static Vector<Range> generate( int N ) {
	    	 Vector<Range> arr=new  Vector<Range>();
	    	int lenth =256/N;
	    	for(int i=0; i<N;i++) {
	    		 Range R=new Range();
	    		int m=new Integer(i*lenth);
	    		R.setMin(m);
	    		int ma= new Integer(m+(lenth-1));
	    		R.setMax(ma);
	    		int z= new Integer((m+ma+1)/2);
	    		R.setS(z);
	    		R.setQ(i);
	    		arr.add(R);
	    	}
	    	return arr;
	    }
	  
	public static int[][] compress( int[][]  img, int level ) {
		int copy[][]=new int[ height][width ];
		
try {
			
			File file = new File("comp.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			Vector<Range> TRQ=new  Vector<Range>();
			TRQ=generate(level);
			
			 for(int x=0;x<width;x++) {
		            for(int y=0;y<height;y++) {
		            	for( int k=0;k<TRQ.size();k++ ) {
		            		if(( img[y][x]>=TRQ.elementAt(k).getMin() )&&( img[y][x]<=TRQ.elementAt(k).getMax() )) {
		            			copy[y][x]=TRQ.elementAt(k).getQ();
		            			String s=Integer.toString(copy[y][x]);
		    	            	bw.write(s);
		    	            	bw.newLine();
		            			break;
		            		}
		            	}
		            }
			 }
			 			
			 
			
			bw.close();
			//System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
		 return copy;
	}
	    
	public static int[][] decompress( int[][]  imgcopy, int l ) {
		Vector<Range> T=new  Vector<Range>();
		T=generate(l);
		int icopy[][]=new int[ height][width ];
		 for(int x=0;x<width;x++) {
	            for(int y=0;y<height;y++) {
	            	for( int k=0;k<T.size();k++ ) {
	            		if( imgcopy[y][x]==T.elementAt(k).getQ() ) {
	            			icopy[y][x]=T.elementAt(k).getS();
	            			break;
	            		}
	            	}
	            }
		 }
		 return icopy;
	}
	
	public static int calc_mean_square_error(int [][]newpixels,int[][]original)
	{
		int mean_square=0;
		int sum=0;
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				sum+= Math.pow(newpixels[i][j]-original[i][j], 2);
			}
		}
		int m=height*width;
		mean_square=sum/(m);
		return mean_square;
	}

}
