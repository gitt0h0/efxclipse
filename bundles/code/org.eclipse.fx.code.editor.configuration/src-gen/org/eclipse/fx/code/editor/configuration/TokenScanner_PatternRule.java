package org.eclipse.fx.code.editor.configuration;

public interface TokenScanner_PatternRule extends EditorBase, TokenScanner {
	public int getStartLength();
	public String getStartPattern();
	public String getContainmentPattern();
	public Check getCheck();
	public Condition getCondition();

	public interface Builder {
		public Builder startLength(int startLength);
		public Builder startPattern(String startPattern);
		public Builder containmentPattern(String containmentPattern);
		public Builder check(Check check);
		public Builder condition(Condition condition);
		public TokenScanner_PatternRule build();
	}
}
