package com.pc.app.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;

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

	public static class GridHeader extends Span {

		private static final long serialVersionUID = 1L;

		public GridHeader() {
			addClassName("gridheader");
		}

		public GridHeader(String text) {
			this();
			setText(text);
		}

	}

	public static class TextFieldClearButton extends Button {

		private static final long serialVersionUID = 1L;

		public TextFieldClearButton(TextField searchbox) {
			getElement().setAttribute("theme", "tertiary-inline");
			setIcon(new Icon("lumo", "cross"));
			addClickListener(c -> searchbox.clear());
		}
	}

	public static class IconText extends Div {

		private static final long serialVersionUID = 1L;

		public IconText(String icon, String text) {
			Span span = new Span(text);
			span.setClassName("hideonsmall");
			add(new SVGIcon(icon), span);
			addClassName("icontext");
		}
	}

	public static class SVGIcon extends Image {

		private static final long serialVersionUID = 1L;

		private final String prefx = "frontend/images/svg/";

		public SVGIcon() {

		}

		public SVGIcon(String icon) {
			setIcon(icon);
		}

		public SVGIcon(String icon, String size) {
			setIcon(icon);
			setWidth(size);
		}

		public void setIcon(String icon) {
			setSrc(prefx + icon);
		}

	}

	@Tag("div")
	public static class NavigationBar extends Div {

		private static final long serialVersionUID = 1L;

		Div a = new Div();
		Div b = new Div();
		Div c = new Div();

		public NavigationBar() {
			setClassName("sidenav");
			a.setClassName("vnav-headerbanner");
			b.setClassName("vnav-midsection");
			c.setClassName("vnav-footer");
			add(a, b, c);
		}

		public void addHeader(Component... component) {
			a.add(component);
		}

		public void addSection(Component... components) {
			b.add(components);
		}

		public void addFooter(Component... component) {
			c.add(component);
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

		public SmallButton theme(String attr) {
			String theme = getElement().getAttribute("theme");
			getElement().setAttribute("theme", theme + " " + attr);
			return this;
		}

		public SmallButton onclick(Click clk) {
			addClickListener(c -> clk.onclick());
			return this;
		}

		public interface Click {
			void onclick();
		}

	}

}
