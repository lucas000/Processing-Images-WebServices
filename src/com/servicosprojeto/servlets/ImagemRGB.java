package com.servicosprojeto.servlets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.text.html.parser.Entity;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;
import com.sun.jersey.multipart.MultiPart;

/**
 * Servlet implementation class FileUploadServlet
 * 
 * @param <WebTarget>
 */
@MultipartConfig
public class ImagemRGB<WebTarget> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(ImagemRGB.class.getCanonicalName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImagemRGB() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file
		final String path = "D:\\imagensProjetos";
		final Part filePart = request.getPart("file");
		final String fileName = getFileName(filePart);

		// OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();

		try {

			// out = new FileOutputStream(new File(path + File.separator + fileName));
			// Salvando dados da requisição no InputStream
			filecontent = filePart.getInputStream();

			// Chamando o serviço para enviar a imagem passada pro servlet

			// the file we want to upload

			// File inFile = new File("C:\\Users\\Lucas\\Desktop\\imagem.jpg");
			// FileInputStream fis = null;

			try {
				// fis = new FileInputStream(inFile);

				// Use response object to verify upload success

				DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

				// server back-end URL
				HttpPost httppost = new HttpPost("http://localhost:8080/ServicosProjeto/rest/file/OriginalToHSV");

				MultipartEntity entity = new MultipartEntity();
				// set the file input stream and file name as arguments
				entity.addPart("file", new InputStreamBody(filePart.getInputStream(), filePart.getName()));
				httppost.setEntity(entity);

				// execute the request
				HttpResponse response1 = httpclient.execute(httppost);

				int statusCode = response1.getStatusLine().getStatusCode();
				HttpEntity responseEntity = response1.getEntity();
				// String responseString = EntityUtils.toString(responseEntity, "UTF-8");

				if (statusCode == 200) {

					//Chama Serviço 2
					{
						DefaultHttpClient httpclientServico2 = new DefaultHttpClient(new BasicHttpParams());

						// server back-end URL
						HttpPost httppostServico2 = new HttpPost("http://localhost:8080/ServicosProjeto/rest/file/HSVToSegmentacao");

						MultipartEntity entityServico2 = new MultipartEntity();
						// set the file input stream and file name as arguments
						
						File initialFile = new File("C:\\Users\\pc\\Desktop\\originalparahsv_s1.jpg");
					    InputStream targetStream = new FileInputStream(initialFile);
					    
						entityServico2.addPart("file", new InputStreamBody(targetStream, initialFile.getName()));
						
						httppostServico2.setEntity(entityServico2);

						// execute the request
						HttpResponse response1Servico2 = httpclientServico2.execute(httppostServico2);

						int statusCodeServico2 = response1Servico2.getStatusLine().getStatusCode();
						HttpEntity responseEntityServico2 = response1Servico2.getEntity();
						
						if (statusCodeServico2 == 200) {
							//Chama Serviço 3
							{
								DefaultHttpClient httpclientServico3 = new DefaultHttpClient(new BasicHttpParams());

								// server back-end URL
								HttpPost httppostServico3 = new HttpPost("http://localhost:8080/ServicosProjeto/rest/file/SegmentacaoErosao");

								MultipartEntity entityServico3 = new MultipartEntity();
								// set the file input stream and file name as arguments
								
								File initialFile3 = new File("C:\\Users\\pc\\Desktop\\binarizada.jpg");
							    InputStream targetStream3 = new FileInputStream(initialFile3);
							    
								entityServico3.addPart("file", new InputStreamBody(targetStream3, initialFile3.getName()));
								
								httppostServico3.setEntity(entityServico3);

								// execute the request
								HttpResponse response1Servico3 = httpclientServico3.execute(httppostServico3);

								int statusCodeServico3 = response1Servico3.getStatusLine().getStatusCode();
								HttpEntity responseEntityServico3 = response1Servico3.getEntity();
								
								if (statusCodeServico3 == 200) {
									{
										DefaultHttpClient httpclientServico4 = new DefaultHttpClient(new BasicHttpParams());

										// server back-end URL
										HttpPost httppostServico4 = new HttpPost("http://localhost:8080/ServicosProjeto/rest/file/ErosaoToBoudingBox");

										MultipartEntity entityServico4 = new MultipartEntity();
										// set the file input stream and file name as arguments
										
										File initialFile4 = new File("C:\\Users\\pc\\Desktop\\imagemcomerosao.jpg");
									    InputStream targetStream4 = new FileInputStream(initialFile4);
									    
										entityServico4.addPart("file", new InputStreamBody(targetStream4, initialFile4.getName()));
										
										httppostServico4.setEntity(entityServico4);

										// execute the request
										HttpResponse response1Servico4 = httpclientServico4.execute(httppostServico4);

										int statusCodeServico4 = response1Servico4.getStatusLine().getStatusCode();
										HttpEntity responseEntityServico4 = response1Servico4.getEntity();
										
										if (statusCodeServico4 == 200) {
											System.out.println("\nBouding box super bom!");

											RequestDispatcher r = request.getRequestDispatcher("paginas/EnviaEscalaCinza.jsp");

											r.forward(request, response);
										}
									}
								}
							}
						}
					}
					
				} else if (statusCode == 415) {
					System.out.println("\n\n415!");
				} else {
					System.out.println(statusCode);
					System.out.println("\n\nConversão para HSV deu ruim!");
				}
				// System.out.println("[" + statusCode + "] " + responseString);

			} catch (ClientProtocolException e) {
				System.err.println("Unable to make connection");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Unable to read file");
				e.printStackTrace();
			}

			//
			//
			writer.println("New file " + fileName + " created at " + path);
			LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", new Object[] { fileName, path });
			//

		} catch (FileNotFoundException fne) {
			writer.println("You either did not specify a file to upload or are "
					+ "trying to upload a file to a protected or nonexistent " + "location.");
			writer.println("<br/> ERROR: " + fne.getMessage());

			LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[] { fne.getMessage() });
		} finally {
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
