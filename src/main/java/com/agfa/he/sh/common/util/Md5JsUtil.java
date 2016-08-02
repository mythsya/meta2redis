package com.agfa.he.sh.common.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Md5JsUtil {

	private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	
	static {
		try {
			engine.eval("load('src/main/resources/md5.js')");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(b64_md5("123"));
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
