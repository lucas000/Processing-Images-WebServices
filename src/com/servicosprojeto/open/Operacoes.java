package com.servicosprojeto.open;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Operacoes {

	public void segmentando(String caminho) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//ile input = new File(caminho);
		
		Mat source = Imgcodecs.imread(caminho); 
		  
        // Creating the empty destination matrix 
        Mat destination = new Mat(); 
  
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
        //Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2HSV); 
        
        Mat formatoHSV = new Mat(); 
        final int hueLowerR = 160;
        final int hueUpperR = 180;
        
        Mat desn = new Mat(); 
        
        //RGB para HSV pode ser um serviço
        Imgproc.cvtColor(source, formatoHSV, Imgproc.COLOR_BGR2HSV);    
        
        //Procurando branco na imagem, pode ser um serviço
        Core.inRange(formatoHSV, new Scalar(0,50,50), new Scalar(255,200,200), desn);
        
        //Esse ta dando certo
        //Core.inRange(formatoHSV, new Scalar(25,50,50), new Scalar(35,200,200), desn);
       
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cupim\\processada\\hsv.jpg", formatoHSV);
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cupim\\processada\\binarizada.jpg", desn);
        
        //Erosao
        double kernelSize = 2.9;
        
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_ELLIPSE, 
        		new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));
        
        Mat matImgDstErode = new Mat(); 
        Mat matImgDstDilatacao = new Mat();
        
        //Erodindo e dilatando a imagem, pode ser um serviço
        Imgproc.erode(desn, matImgDstErode, element);
        Imgproc.dilate(desn, matImgDstDilatacao, element);
        
        //Salvando operações de erosao e ditalacao, nao precisar ser serviço
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cupim\\processada\\erosao.jpg", matImgDstErode);
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cupim\\processada\\dilacao.jpg", matImgDstDilatacao);
        
	}
	public void teste(String teste) throws IOException{
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		
		int[][] testArray = new int[][]{{1,2,3,4},{4,5,6,7},{7,8,9,10}};
	    Mat matArray = new Mat(3,4,CvType.CV_8UC1);
	    for(int row=0;row<3;row++){
	        for(int col=0;col<4;col++)
	            matArray.put(row, col, testArray[row][col]);
	    }
	    File input = new File(teste);
	    BufferedImage image = ImageIO.read(input);
	    
	 // To Read the image 
        Mat source = Imgcodecs.imread(teste); 
  
        // Creating the empty destination matrix 
        Mat destination = new Mat(); 
  
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
        Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY); 
  
        System.out.print("Deu erro antes");
        
        Imgcodecs.imwrite("C:\\File\\input.jpg", destination);
        // Writing the image 
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cumpim\\processada\\segmentada1.jpg", destination); 
        System.out.print("Deu errou depois");
        try {
        int kernelSize = 9;
        
        Mat source1 = Imgcodecs.imread("C:\\Users\\Lucas\\Desktop\\cumpim\\processada\\segmentada1.jpg");
        Mat destination1 = new Mat(source.rows(),source.cols(),source.type());
        
        Mat kernel = new Mat(kernelSize,kernelSize, CvType.CV_32F) {
           {
              put(0,0,-1);
              put(0,1,0);
              put(0,2,1);

              put(1,0-2);
              put(1,1,0);
              put(1,2,2);

              put(2,0,-1);
              put(2,1,0);
              put(2,2,1);
           }
        };	      
        
        Imgproc.filter2D(source1, destination1, -1, kernel);
        Imgcodecs.imwrite("C:\\Users\\Lucas\\Desktop\\cumpim\\processada\\binarizada.jpg", destination1);
     } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
     }
	    /*
		File input1 = new File(teste);
  
		try {
			BufferedImage image = ImageIO.read(input1);
			
			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	        Mat mat2 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
	        mat.put(0, 0, data);

	        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
	        Imgproc.cvtColor(mat2, mat1, Imgproc.COLOR_RGB2GRAY);

	        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	        mat1.get(0, 0, data1);
	        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
	        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

	        File ouptut = new File("C:\\Users\\Lucas\\Desktop\\o\\grayscale.jpg");
	        ImageIO.write(image1, "jpg", ouptut);
	        
	        System.out.print("Deu bom Deus");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
		
	}
	
}
