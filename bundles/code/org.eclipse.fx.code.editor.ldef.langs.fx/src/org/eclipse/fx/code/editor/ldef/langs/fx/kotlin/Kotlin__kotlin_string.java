package org.eclipse.fx.code.editor.ldef.langs.fx.kotlin;

public class Kotlin__kotlin_string extends org.eclipse.jface.text.rules.RuleBasedScanner {
	public Kotlin__kotlin_string() {
		org.eclipse.jface.text.rules.Token kotlin_stringToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.jface.text.TextAttribute("kotlin.kotlin_string"));
		setDefaultReturnToken(kotlin_stringToken);
		org.eclipse.jface.text.rules.IRule[] rules = new org.eclipse.jface.text.rules.IRule[0];

		setRules(rules);
	}
}
