import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class TesteOpencv {
	public static void main(String[] args) {
		System.out.println("Welcome to OpenCV hhhh " + Core.VERSION);
		  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		  Mat m1 =Imgcodecs.imread("C:\\Users\\Lucas\\Desktop\\imagem.jpg");
		  Mat m2=new Mat();
		  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}