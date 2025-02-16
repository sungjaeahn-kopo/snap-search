package com.snap_search.lvalue.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

public class ImageOptimizationUtil {
	public static InputStream resizeImage(InputStream originalImage, int width, int height) throws Exception {
		// Thumbnailator를 사용하여 PNG 이미지를 리사이즈
		BufferedImage resizedImage = Thumbnails.of(originalImage)
			.size(width, height)
			.asBufferedImage();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "png", outputStream);

		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
