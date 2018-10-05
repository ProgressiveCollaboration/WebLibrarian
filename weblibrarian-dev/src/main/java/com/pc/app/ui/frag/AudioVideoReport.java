package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AudioVideo Report")
@Route(value = "avdash", layout = BaseFragment.class)
public class AudioVideoReport extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public AudioVideoReport() {
		 setTitleText("Dashboard");
	}

}
