package com.pc.app.ui.dialog;

import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.entity.Publisher;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class EditPublisherDialog extends BasicDialog {

	private static final long serialVersionUID = -2520361391158861741L;

	public EditPublisherDialog(Publisher bean, BeanAction beanAction, Action<Publisher> action) {
		super(bean, beanAction);
		setTitle("Publisher");
		setSize(DialogSize.LARGE);

		Binder<Publisher> binder = new Binder<>(Publisher.class);
		binder.setBean(new Publisher());

		FormLayout ftml = new FormLayout();

		TextField tf0 = new TextField("Publisher Name");
		binder.forField(tf0).asRequired().bind("publisherName");
		ftml.add(tf0);

		TextField tf1 = new TextField("Publisher Website");
		binder.forField(tf1).asRequired().bind("publisherWebsite");
		ftml.add(tf1);

		TextField tf2 = new TextField("Publisher Wiki Link");
		binder.forField(tf2).bind("wikiLink");
		ftml.add(tf2);

		TextField tf3 = new TextField("Copyright Notice");
		binder.forField(tf3).bind("copyright");
		ftml.add(tf3);

		TextField tf4 = new TextField("Image Url");
		binder.forField(tf4).bind("imageUrl");
		ftml.add(tf4);

		binder.readBean(bean);
		setContent(ftml);

		if (beanAction != BeanAction.VIEW) {
			addFormComponent(new SmallButton("Clear").onclick(() -> binder.readBean(new Publisher())));

			addTerminalComponent(new SmallButton(beanAction == BeanAction.DELETE ? "Delete" : "Save")
					.theme(beanAction == BeanAction.DELETE ? "primary error" : "primary").onclick(() -> {
						if (binder.validate().isOk())
							if (binder.writeBeanIfValid(bean))
								if (action.action(bean))
									close();
					}));
		}
	}

}
