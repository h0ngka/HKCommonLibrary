package com.hongka.hkcommonlibrary.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * 화면의 density를 관리하는 class
 * @author 
 *
 */
public class DensityScaleUtil {
	protected static float sDensityScale = 0;

	/**
	 * dip를 pixel로 변환
	 * @param context
	 * @param dip 
	 * @return
	 */
	public static int dipToPixel(Context context, int dip) {
		if (DensityScaleUtil.sDensityScale == 0) {
			DensityScaleUtil.sDensityScale = context.getResources().getDisplayMetrics().density;
		}
		
		return (int) (dip * DensityScaleUtil.sDensityScale + 0.5f);
	}

	/**
	 * pixel을 dip로 변환
	 * @param context
	 * @param pixel
	 * @return
	 */
	public static int pixelToDip(Context context, int pixel) {
		if (DensityScaleUtil.sDensityScale == 0) {
			DensityScaleUtil.sDensityScale = context.getResources().getDisplayMetrics().density;
		}
		
		return (int) (pixel / DensityScaleUtil.sDensityScale - 0.5f);
	}	
	
	/**
	 * 현재 디스플레이의 너비를 반환
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		return display.getWidth();
		
	}
}
