package com.snap_search.lvalue.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageOptimizationUtil {
	public static InputStream resizeImage(InputStream originalImage, int width, int height) throws Exception {
		BufferedImage bufferedImage = ImageIO.read(originalImage);

		// PNG의 투명도(알파 채널)를 유지한 상태로 새로운 BufferedImage 생성
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(bufferedImage, 0, 0, width, height, null);
		g2d.dispose();

		// Thumbnailator를 사용하여 PNG 이미지를 리사이즈
		// BufferedImage resizedImage = Thumbnails.of(originalImage)
		// 	.size(width, height)
		// 	.asBufferedImage();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "png", outputStream);

		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
