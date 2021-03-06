package org.eclipse.fx.code.compensator.project.ci.travis;

import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;

import javax.annotation.PostConstruct;

public class TravisProjectViewer {
	@PostConstruct
	void init(BorderPane parent) {
		WebView v = new WebView();
		v.getEngine().setOnError(new EventHandler<WebErrorEvent>() {
			
			@Override
			public void handle(WebErrorEvent event) {
				System.err.println(event);
			}
		});
		v.getEngine().load("https://travis-ci.org/tomsontom/compensator-samples");
//		v.getEngine().load("http://www.google.com");
//		v.getEngine().load("https://travis-ci.org");
		parent.setCenter(v);
	}
}