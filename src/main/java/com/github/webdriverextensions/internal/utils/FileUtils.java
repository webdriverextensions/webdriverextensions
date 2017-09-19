package com.github.webdriverextensions.internal.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

	private static List<String> fileList = new ArrayList<String>();

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FileUtils.class);

	private FileUtils() {
	}

	public static void makeExecutable(String path) {
		if (path == null) {
			return;
		}
		File file = new File(path);
		if (file.exists() && !file.canExecute()) {
			file.setExecutable(true);
		}
	}

	/**
	 * 
	 * @param path
	 *            - Name of the directory to be created.
	 * @param overwrite
	 *            - Overwrite the directory if exists.
	 * 
	 */
	public static void makeDirectory(String path) {
		// Verify that, Path is null. If so, return
		if (path == null) {
			return;
		}
		// Get the instance of the path
		File file = new File(path);
		// Check the file exists and overwrite flag is true then delete it.
		if (file.exists()) {
			for (File report : file.listFiles()) {
				report.delete();
			}
		} else {
			// Make the directory of given path
			file.mkdir();
		}
		
	}

	/**
	 * Gets the file extension.
	 *
	 * @param file
	 *            the file
	 * @return the file extension
	 */
	public static String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * File exists.
	 *
	 * @param file
	 *            the file
	 * @return true, if successful
	 */
	public static boolean fileExists(String file) {
		return new File(file).exists();
	}

	/**
	 * Copy file.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @return true, if successful
	 */
	public static boolean copyFile(String source, String destination) {
		boolean copyStatus = false;
		try {
			Files.copy(new File(source).toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
			copyStatus = true;
		} catch (Exception e) {
		}
		return copyStatus;
	}

	/**
	 * Gets the absolute path of the resource.
	 *
	 * @param resourceName
	 *            the resource name
	 * @return the resource
	 */
	public static String getAbsolutePath(String resourceName) {
		File fileResource = null;
		try {
			ClassLoader classLoader = new FileUtils().getClass().getClassLoader();
			fileResource = new File(classLoader.getResource(resourceName).getFile());
		} catch (Exception e) {
		}
		return fileResource.getAbsolutePath();
	}

	/**
	 * Get the resource as file
	 * 
	 * @param resourceName
	 * @return File
	 */
	public static File getResourceAsFile(String resourceName) {
		File fileResource = null;
		try {
			ClassLoader classLoader = new FileUtils().getClass().getClassLoader();
			fileResource = new File(classLoader.getResource(resourceName).getFile());
		} catch (Exception e) {
		}
		return fileResource;
	}

	/**
	 * Gets the class name list.
	 *
	 * @param path
	 *            the path
	 * @param suffix
	 *            the suffix
	 * @param isDepth
	 *            the is depth
	 * @return the class name list
	 */
	public static List<String> getClassNameList(String path, String suffix, boolean isDepth) {
		List<?> listDir = FileUtils.getFileList(path, suffix, isDepth);
		List<String> listFileName = new ArrayList<String>();
		for (int i = 0; i < listDir.size(); i++) {
			int index = ((String) listDir.get(i)).lastIndexOf(File.separator);
			int len = ((String) listDir.get(i)).length();
			String fileNameWithsuffix = ((String) listDir.get(i)).substring(index + 1, len - suffix.length() - 1);
			listFileName.add(fileNameWithsuffix);
		}
		return listFileName;
	}

	/**
	 * Gets the package name.
	 *
	 * @param path
	 *            the path
	 * @param suffix
	 *            the suffix
	 * @param isDepth
	 *            the is depth
	 * @return the package name
	 */
	public static List<String> getPackageName(String path, String suffix, boolean isDepth) {
		List<?> listDir = FileUtils.getFileList(path, suffix, isDepth);
		List<String> listPackPathName = new ArrayList<String>();
		for (int i = 0; i < listDir.size(); i++) {
			// int index=((String)listDir.get(i)).lastIndexOf(":");
			int index = FileUtils.getProjectDirectory().length();
			int len = ((String) listDir.get(i)).length();
			String fileNameWithsuffix = ((String) listDir.get(i)).substring(index + 5, len - suffix.length() - 1)
					.replace(File.separator, ".");
			listPackPathName.add(fileNameWithsuffix);
		}
		return listPackPathName;
	}

	/**
	 * Gets the file list.
	 *
	 * @param path
	 *            the path
	 * @param suffix
	 *            the suffix
	 * @param isDepth
	 *            the is depth
	 * @return the file list
	 */
	public static List<String> getFileList(String path, String suffix, boolean isDepth) {
		File file = new File(path);
		return FileUtils.listFile(file, suffix, isDepth);
	}

	/**
	 * List file.
	 *
	 * @param f
	 *            the f
	 * @param suffix
	 *            the suffix
	 * @param isDepth
	 *            the is depth
	 * @return the list
	 */
	public static List<String> listFile(File f, String suffix, boolean isDepth) {
		if (f.isDirectory() && isDepth == true) {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(t[i], suffix, isDepth);
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (suffix != null) {
				int begIndex = filePath.lastIndexOf(".");
				String tempsuffix = "";
				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
				}
				if (tempsuffix.equals(suffix)) {
					fileList.add(filePath);
				}
			}
		}
		return fileList;
	}

	/**
	 * Gets the project file.
	 *
	 * @return the project file
	 */
	public static String getProjectDirectory() {
		return System.getProperty("user.dir");
	}

	public static void backupDirectory(String source, String dest) {
		try {
			File srcDir = new File(source);
			File destDir = new File(dest);
			if (srcDir.exists()) {
				org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir);				
			}
		} catch (IOException e) {
			log.error("Unable to copy the directory." + e.getMessage());
		}
	}

	public static String getTimeStamp() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		return dateFormatter.format(new Date().getTime());
	}
}
