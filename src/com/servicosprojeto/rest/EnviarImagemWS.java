package com.servicosprojeto.rest;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream.GetField;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.servicosprojeto.open.Operacoes;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class EnviarImagemWS {

	
	@POST
	@Path("/uploadImagemOriginal")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		/*
		 * String uploadedFileLocation =
		 * "C:\\Users\\Lucas\\Desktop\\cupim\\processada\\" + fileDetail.getFileName();
		 * 
		 * // save it writeToFile(uploadedInputStream, uploadedFileLocation); Operacoes
		 * operacoes = new Operacoes();
		 */

		/*
		 * try { operacoes.segmentando(uploadedFileLocation);
		 * operacoes.teste(uploadedFileLocation); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * String output = "File uploaded to : " + uploadedFileLocation;
		 */
		String output = "Imagem inputstream recebido!!!";

		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/OriginalToHSV")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response OriginalToHSV(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		
		try {
			OutputStream out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\imagem_s1.jpg"));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\imagem_s1.jpg"));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			
			Mat rgb = Imgcodecs.imread("C:\\Users\\pc\\Desktop\\imagem_s1.jpg"); 
			Mat hsv = new Mat();
			
			Imgproc.cvtColor(rgb, hsv, Imgproc.COLOR_BGR2HSV);  
			
			Imgcodecs.imwrite("C:\\Users\\pc\\Desktop\\originalparahsv_s1.jpg", hsv);
	        
			return Response.status(200).entity("Deu bom - chame S2!").build();
		} catch (Exception e) {
			System.out.print("Deu ruim na conversão: " + e);
		}
		return null;
	}

	@POST
	@Path("/HSVToSegmentacao")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response HSVToSegmentacao(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		try {
			OutputStream out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\hsvparabinarizar.jpg"));
			
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\hsvparabinarizar.jpg"));
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			out.flush();
			out.close();
			
			Mat rgb = Imgcodecs.imread("C:\\Users\\pc\\Desktop\\hsvparabinarizar.jpg"); 
			Mat binarizada = new Mat();
			
			 //Procurando branco na imagem, pode ser um serviço
	        //Core.inRange(rgb, new Scalar(35,225,220), new Scalar(55,255,255), binarizada);
	        //Core.inRange(rgb, new Scalar(20,50,50), new Scalar(255,200,200), binarizada);
	        Core.inRange(rgb, new Scalar(0,50,50), new Scalar(255,200,200), binarizada);
	        Imgcodecs.imwrite("C:\\Users\\pc\\Desktop\\binarizada.jpg", binarizada);
				        
			return Response.status(200).entity("Deu bom - chame S3!").build();
		} catch (Exception e) {
			System.out.print("Deu ruim na conversão: " + e);
		}
		return null;

	}

	@POST
	@Path("/SegmentacaoErosao")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response SegmentacaoErosao(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		try {
			OutputStream out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\paraerosao.jpg"));
			
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\paraerosao.jpg"));
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			out.flush();
			out.close();
			
			Mat rgb = Imgcodecs.imread("C:\\Users\\pc\\Desktop\\paraerosao.jpg"); 
			
			//
			double kernelSize = 5.0;
	        
	        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_ELLIPSE, 
	        		new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
	                new Point(kernelSize, kernelSize));
	        
	        Mat matImgDstErode = new Mat(); 
	        
	        Imgproc.dilate(rgb, matImgDstErode, element);
	        
	        Imgcodecs.imwrite("C:\\Users\\pc\\Desktop\\imagemcomerosao.jpg", matImgDstErode);
				        
			return Response.status(200).entity("Deu bom - chame S3!").build();
		} catch (Exception e) {
			System.out.print("Deu ruim na conversão: " + e);
		}
		return null;

	}

	@POST
	@Path("/ErosaoToBoudingBox")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response ErosaoToBoudingBox(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		Mat srcGray = new Mat();
	    int threshold = 100;
	    Random rng = new Random(12345);
	    
		try {
			OutputStream out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\boudingbox.jpg"));
			
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\boudingbox.jpg"));
			Mat src = Imgcodecs.imread("C:\\Users\\pc\\Desktop\\imagemcomerosao.jpg");
			
			Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY);
	        Imgproc.blur(srcGray, srcGray, new Size(3, 3));
	        
	        Mat cannyOutput = new Mat();
	        Imgproc.Canny(srcGray, cannyOutput, threshold, threshold * 2);
	        List<MatOfPoint> contours = new ArrayList<>();
	        Mat hierarchy = new Mat();
	        Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
	        
	        MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
	        Rect[] boundRect = new Rect[contours.size()];
	        Point[] centers = new Point[contours.size()];
	        float[][] radius = new float[contours.size()][1];
	        for (int i = 0; i < contours.size(); i++) {
	            contoursPoly[i] = new MatOfPoint2f();
	            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
	            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
	            centers[i] = new Point();
	            Imgproc.minEnclosingCircle(contoursPoly[i], centers[i], radius[i]);
	        }
	        Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
	        List<MatOfPoint> contoursPolyList = new ArrayList<>(contoursPoly.length);
	        for (MatOfPoint2f poly : contoursPoly) {
	            contoursPolyList.add(new MatOfPoint(poly.toArray()));
	        }
	        for (int i = 0; i < contours.size(); i++) {
	            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
	            Imgproc.drawContours(drawing, contoursPolyList, i, color);
	            Imgproc.rectangle(drawing, boundRect[i].tl(), boundRect[i].br(), color, 2);
	            Imgproc.circle(drawing, centers[i], (int) radius[i][0], color, 2);
	        }
	        
	        Imgcodecs.imwrite("C:\\Users\\pc\\Desktop\\aoiudoais.jpg", drawing);
	        
			//while ((read = uploadedInputStream.read(bytes)) != -1) {
			//	out.write(bytes, 0, read);
			//}
			
			out.flush();
			out.close();
			
			//Mat rgb = Imgcodecs.imread("C:\\Users\\pc\\Desktop\\boudingbox.jpg"); 
			
			return Response.status(200).entity("Deu bom - chame S3!").build();
		} catch (Exception e) {
			System.out.print("Deu ruim na conversão: " + e);
		}
		return null;
	}

	@POST
	@Path("/NumerosBoxToCliente")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public InputStream NumerosBoxToCliente(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String output = "Imagem inputstream recebido!!!";

		return null;

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}