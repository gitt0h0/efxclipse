/*
 * generated by Xtext
 */
package org.eclipse.fx.ide.gmodel.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class GModelDSLAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/fx/ide/gmodel/parser/antlr/internal/InternalGModelDSL.tokens");
	}
}