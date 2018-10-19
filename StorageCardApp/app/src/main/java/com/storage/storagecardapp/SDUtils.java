package com.storage.storagecardapp;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class SDUtils {


	public static boolean checkSDExit() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true; //sdcard存在
		} else {
			return false;
		}
	}



	public static boolean checkEmptySize(String path) {
		try {
			StatFs sf = new StatFs(path);
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			long byteCount = blockSize * availCount;
			return byteCount < (30 * 1024 * 1024) ? false : true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkZeroFile(String path) {
		try {
			StatFs sf = new StatFs(path);
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long byteCount = blockSize * blockCount;
			return byteCount <= 0 ? false : true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getEmptySize(String path) {
		try {
			StatFs sf = new StatFs(path);
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			long availByte = blockSize * availCount;
			long allByte = blockSize * blockCount;
			double avail = 0;
			double all = 0;
			String availString, allString;
			return "全部" + getSize(allByte) + "/可用"  + getSize(availByte);
		} catch (Exception e) {
			return "全部0MB/可用0MB";
		}
	}

	public static long getAvailableSize(String path) {
		try {
			StatFs sf = new StatFs(path);
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			long availByte = blockSize * availCount;
			return availByte;
		} catch (Exception e) {
			return 0;
		}
	}

    /**
     * byte大小转文字.
     * @param byteCount
     * @return
     */
	public static String getSize(long byteCount) {
		double all = 0;
		if (byteCount < (1000 * 1000 * 1000)) {
			if (byteCount < 1000 * 1000) {
				all = (double) (Math.round(((double) byteCount / (1000)) * 10) / 10.0);
				return all + "K";
			} else {
				all = (double) (Math
						.round(((double) byteCount / (1000 * 1000)) * 10) / 10.0);
				return all + "M";
			}
		} else {
			all = (double) (Math
					.round(((double) byteCount / (1000 * 1000 * 1000)) * 10) / 10.0);
			return all + "G";
		}
	}


	
}
