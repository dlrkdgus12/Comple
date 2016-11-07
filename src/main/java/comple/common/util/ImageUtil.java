package comple.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static void main(String[] srgs){
		String srcFilePath = "/users/ttobii/desktop/ttobii.png";
		String srcFilePath2 = "/users/ttobii/desktop/put.jpg";
		String desFilePath = "/users/ttobii/desktop/ttobiiout.jpg";
		String desFilePath2 = "/users/ttobii/desktop/out2.jpg";
		
		if (new File(srcFilePath).length() > 0) {
			OutputStream os = null;
			try {
				
				
				BufferedImage srcOp = getResizeByRatio(srcFilePath, 1500, 1000, true);
				ImageIO.write(srcOp, "jpg", new File(desFilePath));

				srcOp = getResizeByRatio(srcFilePath2, 3000, 6000, false);
				ImageIO.write(srcOp, "jpg", new File(desFilePath2));

				// os = new FileOutputStream(desFilePath);
				// JPEGEncodeParam param = new JPEGEncodeParam();
				// param.setQuality(1);
				// ImageEncoder enc = ImageCodec.createImageEncoder("jpeg", os,
				// param);
				// enc.encode(srcOp);
				// os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ?Å¨Î°??ïòÎ©¥ÏÑú Î¶¨ÏÇ¨?ù¥Ï¶? ?ïòÍ∏?
	
	/**
	 * ?†ú?ïú?êú ?Ç¨?ù¥Ï¶? ?Ç¥Î∂??óê?Ñú ?ù¥ÎØ∏Ï? ÎπÑÏú®?óê ÎßûÍ≤å ÏµúÎ?Î°? Î¶¨ÏÇ¨?ù¥Ï¶?
	 * @param srcFilePath
	 * @param stdWidth
	 * @param stdHeight
	 * @param soft
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getResizeByRatio(String srcFilePath, int stdWidth, int stdHeight, boolean soft) throws IOException {
		ImageIcon ii = new ImageIcon(new File(srcFilePath).getCanonicalPath());
		Image i = ii.getImage();
		
		return getResizeByRatio(i, stdWidth, stdHeight, soft);
	}
	
	public static BufferedImage getResizeByRatio(MultipartFile upfile, int stdWidth, int stdHeight, boolean soft) throws IOException {
		ImageIcon ii = new ImageIcon(upfile.getBytes());
		Image i = ii.getImage();
		
		return getResizeByRatio(i, stdWidth, stdHeight, soft);
	}
	
	public static BufferedImage getResizeByRatio(Image i, int stdWidth, int stdHeight, boolean soft) throws IOException {
		// ?ù¥ÎØ∏Ï? Î¶¨ÏÇ¨?ù¥Ï¶? ?Å¨Í∏? Í≥ÑÏÇ∞
		double ratioWidth = stdWidth>stdHeight?((double)stdWidth/(double)stdHeight):1;
		double ratioHeight = stdWidth>stdHeight?1:((double)stdHeight/(double)stdWidth);
		
		int targetWidth = i.getWidth(null);
		int targetHeight = i.getHeight(null);
		
		if(ratioHeight*targetWidth>=ratioWidth*targetHeight){
			targetHeight = (int) (((double)targetHeight/(double)targetWidth)*stdWidth);
			targetWidth = stdWidth;
		} else {
			targetWidth = (int) (((double)targetWidth/(double)targetHeight)*stdHeight);
			targetHeight = stdHeight;
		}
		return getResize(i, targetWidth, targetHeight, soft);
	}
	
	/**
	 * ?†ï?ï¥Ïß? ?Ç¨?ù¥Ï¶àÎ°ú Í∞ïÏ†ú Î¶¨ÏÇ¨?ù¥Ï¶?
	 * @param srcFilePath
	 * @param targetWidth
	 * @param targetHeight
	 * @param soft
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage getResize(String srcFilePath, int targetWidth, int targetHeight, boolean soft) throws IOException{
		ImageIcon ii = new ImageIcon(new File(srcFilePath).getCanonicalPath());
		Image i = ii.getImage();
		return getResize(i, targetWidth, targetHeight, soft);
	}
	
	/**
	 * ?ã§?†úÎ°? ?ù¥ÎØ∏Ï?Î•? Î¶¨ÏÇ¨?ù¥Ï¶? ?ïò?äî Î©îÏÑú?ìú ( Î™©Ìëú ?Å¨Í∏∞Î°ú Í∞ïÏ†ú Î¶¨ÏÇ¨?ù¥Ï¶? ?ïú?ã§ )
	 * @param i
	 * @param targetWidth
	 * @param targetHeight
	 * @param soft
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage getResize(Image i, int targetWidth, int targetHeight, boolean soft) throws IOException {
		Image resizedImage = null;
		resizedImage = i.getScaledInstance(targetWidth, targetHeight,Image.SCALE_SMOOTH);

		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
//		g.setColor(Color.white);
//		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		if (soft) {
			float softenFactor = 0.05f;
			float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			Kernel kernel = new Kernel(3, 3, softenArray);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);
		}

		return bufferedImage;
	}
}
