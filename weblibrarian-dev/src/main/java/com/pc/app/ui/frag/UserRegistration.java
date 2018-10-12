package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SmallButton;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("User Registration")
@Route(value = "users", layout = BaseFragment.class)
public class UserRegistration extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public UserRegistration() {
		setHeaderText("User Registration");
		addContent(new TextField("content"));

		SmallButton adduserbtn = new SmallButton("Add Library User"); 
		adduserbtn.addClassName("btn-mr");
		adduserbtn.theme("primary");

		SmallButton addmgmtuserbtn = new SmallButton("Add Management User");
		addmgmtuserbtn.addClassName("btn-mr");
		addmgmtuserbtn.theme("primary");

		addToolbarComponent(adduserbtn, addmgmtuserbtn);

		addFooterComponent(new SmallButton("Print Receipt"));
	}

}
