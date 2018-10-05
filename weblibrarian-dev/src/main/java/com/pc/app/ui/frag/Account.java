package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("User Registration")
@Route(value = "account", layout = BaseFragment.class)
public class Account extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public Account() {
		setTitleText("Users");
	}

}
