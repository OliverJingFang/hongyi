package com.cn.util;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;

public class ImageUtil {

    /**
     *  �Ŵ���СͼƬ
     * @param bitmap Ҫ�Ŵ��ͼƬ
     * @param dstWidth Ŀ���
     * @param dstHeight Ŀ���
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int dstWidth, int dstHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) dstWidth / width);
        float scaleHeight = ((float) dstHeight / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    /**
     * ��Drawableת��ΪBitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
                .getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * ���Բ��ͼƬ�ķ���
     * @param bitmap 
     * @param roundPx  4�ŷ���
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * ��ô���Ӱ��ͼƬ����
     * @param bitmap
     * @return
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
                width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }
    
    //.bmp,.jpg,.jpeg,png
    
    
    /**
     * @description: �����µ�ͼƬ��������ͼƬ�޸���ͼƬ
     * @date 2014-12-2 
     * @param 
     * @return Bitmap
     * @Exception
     */
    public static Bitmap drawNewBitmap(Bitmap oriBitmap, Paint paint, String content, float location_X, float location_Y) {
        if (paint == null || oriBitmap == null) {
            return null;
        }
        int width = oriBitmap.getWidth();
        int hight = oriBitmap.getHeight();
        
        //ÿ������ռλ�� 
        float fontSpace = paint.getFontSpacing();
        // ����ռλ�ܿ� 
        float fontLength = content.length() * fontSpace;
        // ���ָ��� 
        float fontSize = paint.getTextSize();
        
        if (fontLength > width || location_X < 0) {
            return null;
        }
        if (fontSize > hight || location_Y - fontSize < 0) {
            return null;
        }
        // ����һ���յ�BItMap
        Bitmap tempBitmap = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888); 
        // ��ʼ���������Ƶ�ͼ��icon��
        Canvas canvas = new Canvas(tempBitmap);
        // ����һ��ָ�����¾��ε�����
        Rect src = new Rect(0, 0, width, hight);
        // ����һ��ָ�����¾��ε�����
        Rect dst = new Rect(0, 0, width, hight);
        canvas.drawBitmap(oriBitmap, src, dst, paint);
        canvas.drawText(content, location_X, location_Y, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return tempBitmap;
    }
    
    
    /**
     * ����ͼƬ
     * @param bm
     *            ��Ҫת����bitmap
     * @param newWidth�µĿ�
     * @param newHeight�µĸ�
     * @return ָ����ߵ�bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        Bitmap newbm = null;
        try {
            if( bm == null ) {
                return null;
            }
            // ���ͼƬ�Ŀ��
            int width = bm.getWidth();
            int height = bm.getHeight();
            // �������ű���
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // ȡ����Ҫ���ŵ�matrix����
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // �õ��µ�ͼƬ
            newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return newbm;
    }
    //storage/emulated/0/517na/517.jpg
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

}