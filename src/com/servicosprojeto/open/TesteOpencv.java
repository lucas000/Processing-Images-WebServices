package com.servicosprojeto.open;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TesteOpencv {
	public static void main(String[] args) {
		System.out.println("Welcome to OpenCV hhhh " + Core.VERSION);
		  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		  Mat m1 =Imgcodecs.imread("C:\\Users\\Lucas\\Desktop\\imagem.jpg");
		  Mat m2=new Mat();
		 
		  Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
			System.out.println("mat = " + mat.dump());
			
			/*
			String input = "C:\\Users\\Lucas\\Desktop\\imagem.jpg"; 
			 File input1 = new File(input);
			try {
				BufferedImage image = ImageIO.read(input1);
				
				byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		        mat.put(0, 0, data);

		        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
		        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

		        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
		        mat1.get(0, 0, data1);
		        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
		        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

		        File ouptut = new File("C:\\Users\\Lucas\\Desktop\\o\\grayscale.jpg");
		        ImageIO.write(image1, "jpg", ouptut);
		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			*/
	}
}