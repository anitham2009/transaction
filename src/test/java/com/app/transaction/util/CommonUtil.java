package com.app.transaction.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Util class used for testing
 * @author Anitha Manoharan
 *
 */
public class CommonUtil {

	/**
	 * Read json file and returns object of the class of given type.
	 * @param <T>
	 * @param fileName
	 * @param contentClass
	 * @return
	 * @throws StreamReadException
	 * @throws DatabindException
	 * @throws IOException
	 */
	public static <T> Object retrieveObject(String fileName, Class<T> contentClass)
			throws StreamReadException, DatabindException, IOException {
		File file = new File(CommonConstants.BASE_FILE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(file, contentClass);
		return object;
	}
	
}
