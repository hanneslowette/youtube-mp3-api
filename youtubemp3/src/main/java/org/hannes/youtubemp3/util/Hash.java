package org.hannes.youtubemp3.util;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Hash {
	
	/**
	 * The class responsible for all of the script engines
	 */
	private static final ScriptEngineManager engineManager = new ScriptEngineManager();
	
	/**
	 * The script engine
	 */
	private static final ScriptEngine engine = engineManager.getEngineByName("javascript");

	/**
	 * Calls the Javascript function that will hash the given url
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static long hash(String url) {
		try {
			double d = callFunction("sig", url);
			return (long) d;
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws ScriptException 
	 * @throws FileNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static <T> T callFunction(String name, Object... params) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		engine.eval(new InputStreamReader(ClassLoader.getSystemResourceAsStream("./youtube-mp3.js")));
		Invocable invocable = (Invocable) engine;
		return (T) invocable.invokeFunction("sig", params);
	}

}