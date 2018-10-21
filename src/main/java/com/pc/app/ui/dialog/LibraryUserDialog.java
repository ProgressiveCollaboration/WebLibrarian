package com.pc.app.ui.dialog;

import com.pc.app.ui.cruds.GenericCRUD;
import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.pc.entity.BaseEntity;
import com.pc.entity.LibraryUser;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class LibraryUserDialog extends MDialog {
	private static final long serialVersionUID = 1L;
	String gid;
	Binder<LibraryUser> pBinder = new Binder<>(LibraryUser.class);
	VerticalLayout vLayout = new VerticalLayout();

	public LibraryUserDialog(final LibraryUser pBean, BeanAction action, GenericCRUD.OnAction<LibraryUser> onaction) {
		super(action);

		// gid = IdGenerator.generateId("libraryUser");
		switch (action) {
		case NEW:
			setHeader("New LibraryUser");
			break;
		case EDIT:
			setHeader("Edit LibraryUser");
			break;
		case DELETE:
			setHeader("Delete LibraryUser");
			break;
		case VIEW:
			setHeader("View LibraryUser");
			break;
		}

		FormLayout form = new FormLayout();
		pBinder.setBean(new LibraryUser());

		TextField firstName = new TextField("First Name");
		firstName.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
		firstName.getStyle().set("minHeight", "12em");
		pBinder.forField(firstName).asRequired("Please enter First Name").bind("firstName");

		TextField lastName = new TextField("Last Name");
		pBinder.forField(lastName).bind("lastName");

		TextArea middleName = new TextArea("Middle Name");
		pBinder.forField(middleName).bind("middleName");

		TextField email = new TextField("Email");
		pBinder.forField(email).bind("email");

		TextField emailAddressVerified = new TextField("Verify Email");
		pBinder.forField(email).bind("emailAddressVerified");

		TextField phoneNo = new TextField("Phone No");
		pBinder.forField(phoneNo).bind("phoneNo");

		DatePicker dateOfBirth = new DatePicker("Date Of Birth");
		pBinder.forField(dateOfBirth).bind("dateOfBirth");

		form.add(firstName, lastName, middleName, email, emailAddressVerified, phoneNo, dateOfBirth);
		form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
		form.setWidth("500px");
		setContents(form);

		pBinder.readBean(pBean);

		// all fields will be locked if viewing or in delete mode
		pBinder.setReadOnly(action == BeanAction.DELETE || action == BeanAction.VIEW);

		if (action != BeanAction.VIEW) {

		}
	}

	public interface OnAction<T extends BaseEntity> {
		void action(T aClass);
	}
}
