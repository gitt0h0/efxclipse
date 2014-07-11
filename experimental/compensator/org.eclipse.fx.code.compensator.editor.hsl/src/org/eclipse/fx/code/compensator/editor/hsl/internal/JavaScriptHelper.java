package org.eclipse.fx.code.compensator.editor.hsl.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public class JavaScriptHelper {
	private static String getPluginPath(URI uri) {
		String[] parts = uri.segments();
		StringBuffer b = new StringBuffer(parts[2]);
		
		for( int i = 3; i < parts.length; i++ ) {
			b.append("/" + parts[i]);
		}
		return b.toString();
	}
	
	public static <O> O loadScript(ClassLoader cl, EObject m, String jsURL) {
		URI uri = m.eResource().getURI();
		uri = uri.trimSegments(1);
		uri = uri.appendSegment(jsURL);
		
		ScriptEngineManager mgr = new ScriptEngineManager(cl);
		ScriptEngine engine = mgr.getEngineByName("nashorn");
		System.err.println(" ======================> " + uri);
		try( InputStreamReader reader = new InputStreamReader(uri.isPlatform() ? cl.getResourceAsStream(getPluginPath(uri)) : new FileInputStream(new File(uri.toFileString()))) ) {
			return (O) engine.eval(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
