package org.eclipse.fx.code.editor.ldef.langs.fx.go;

public class Go__go_string extends org.eclipse.jface.text.rules.RuleBasedScanner {
	public Go__go_string() {
		org.eclipse.jface.text.rules.Token go_stringToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.jface.text.TextAttribute("go.go_string"));
		setDefaultReturnToken(go_stringToken);
		org.eclipse.jface.text.rules.IRule[] rules = new org.eclipse.jface.text.rules.IRule[0];

		setRules(rules);
	}
}
