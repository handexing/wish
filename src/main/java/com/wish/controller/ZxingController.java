package com.wish.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月2日 上午10:42:15
 */

@RestController
@RequestMapping("qrcode")
public class ZxingController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("createQrcode")
	public void createQrcode(HttpServletResponse response, String content) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			BufferedImage image = QRCodeCreate(content, 250, 250);
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: QRCodeCreate 
	 * @Description: 生成二维码
	 * @param @param content
	 * @param @param W
	 * @param @param H
	 * @param @return    设定文件 
	 * @return BufferedImage    返回类型 
	 * @throws
	 */
	public BufferedImage QRCodeCreate(String content, Integer W, Integer H){
        //生成二维码
        MultiFormatWriter mfw = new MultiFormatWriter();
        BitMatrix bitMatrix = null;
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        try {
            bitMatrix = mfw.encode(content, BarcodeFormat.QR_CODE, W, H,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int x=0; x < width; x++){
            for(int y=0; y < height; y++){
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

	@RequestMapping("qrcodePage")
	public ModelAndView showQrcodePage() {
		return new ModelAndView("/sundry/qrcode");
	}

}
