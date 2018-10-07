package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("MailBox")
@Route(value = "mail", layout = BaseFragment.class)
public class MailBox extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public MailBox() {
		setHeaderText("MailBox");

	}

}
