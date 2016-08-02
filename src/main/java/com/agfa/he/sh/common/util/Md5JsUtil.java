package com.agfa.he.sh.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class Md5JsUtil {

	private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	
	static {
		try {
			DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource("md5.js");
			//System.out.println(resource.getInputStream());
			
			engine.eval(new BufferedReader(new InputStreamReader(resource.getInputStream())));
			//engine.eval("load('src/main/resources/md5.js')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final String b64_md5(String source) {
		Invocable invocable = (Invocable) engine;
		try {
			Object result = invocable.invokeFunction("b64_md5", source);
			return (String)result;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
}
