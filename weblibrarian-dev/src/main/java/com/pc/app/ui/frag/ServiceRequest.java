package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Service Request")
@Route(value = "requests", layout = BaseFragment.class)
public class ServiceRequest extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public ServiceRequest() {
		setHeaderText("Service Request");
	}

}
