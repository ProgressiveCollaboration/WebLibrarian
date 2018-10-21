package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.cruds.GenericCRUD;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AudioVideo Report")
@Route(value = "audiovideo", layout = BaseFragment.class)
public class AudioVideoManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public AudioVideoManager() {
		 setHeaderText("Audio / Video");
		GenericCRUD genericCRUD = new GenericCRUD(GenericCRUD.EntityAction.AUDIOVIDEO);
		add(genericCRUD);
	}

}
