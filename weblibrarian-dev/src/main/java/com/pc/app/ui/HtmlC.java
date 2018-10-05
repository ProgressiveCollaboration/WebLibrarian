package com.pc.app.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class HtmlC {

	public static class FlexMe extends FlexLayout {

		private static final long serialVersionUID = 1L;

		public FlexMe() {
			setAlignItems(Alignment.CENTER);
			setJustifyContentMode(JustifyContentMode.BETWEEN);
		}

		public FlexMe(Component... components) {
			this();
			add(components);
		}
	}

	public static class SmallButton extends Button {

		private static final long serialVersionUID = 1L;

		public SmallButton() {
			getElement().setAttribute("theme", "small");
		}

		public SmallButton(String text) {
			this();
			setText(text);
		}

		public void addThemeAttr(String attr) {
			String theme = getElement().getAttribute("theme");
			getElement().setAttribute("theme", theme + " " + attr);
		}

	}

}
