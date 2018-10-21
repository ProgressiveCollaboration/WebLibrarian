package com.pc.app.ui.dialog;

import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.entity.Publisher;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class EditPublisherDialog extends BasicDialog {

	private static final long serialVersionUID = -2520361391158861741L;

	public EditPublisherDialog(Publisher bean, BeanAction beanAction, Action<Publisher> action) {
		super(beanAction);
		setTitle("Publisher");
		setSize(DialogSize.MID);

		Binder<Publisher> binder = new Binder<>(Publisher.class);
		binder.setBean(new Publisher());

		TextField tf0 = new TextField("Publisher Name");
		binder.forField(tf0).asRequired().bind("publisherName");

		TextField tf1 = new TextField("Publisher Website");
		binder.forField(tf1).asRequired().bind("publisherWebsite");

		TextField tf2 = new TextField("Publisher Wiki Link");
		binder.forField(tf2).bind("wikiLink");

		TextField tf3 = new TextField("Copyright Notice");
		binder.forField(tf3).bind("copyright");

		TextField tf4 = new TextField("Image Url");
		binder.forField(tf4).bind("imageUrl");

		binder.readBean(bean);
		binder.setReadOnly(beanAction == BeanAction.DELETE || beanAction == BeanAction.VIEW);

		setContent(new FormLayout(tf0, tf1, tf2, tf3, tf4));

		if (beanAction != BeanAction.VIEW) {
			if (beanAction == BeanAction.NEW || beanAction == BeanAction.EDIT)
				addFormComponent(new SmallButton("Clear").onclick(() -> binder.readBean(new Publisher())));

			addTerminalComponent(new SmallButton(beanAction == BeanAction.DELETE ? "Delete" : "Save")
					.theme(beanAction == BeanAction.DELETE ? "primary error" : "primary").onclick(() -> {
						if (binder.validate().isOk())
							if (binder.writeBeanIfValid(bean)) {
								bean.save();
								if (action.action(bean))
									close();
							}
					}));

			if (beanAction == BeanAction.EDIT)
				addFormComponent(new SmallButton("Delete").theme("error").onclick(() -> {
					new ActionConfirmDialog("Delete Publisher", "Are you sure you want to remove this publisher?",
							BeanAction.DELETE, () -> {
								bean.delete();
								action.action(bean);
								close();
							}).open();
				}));
		}
	}

}
