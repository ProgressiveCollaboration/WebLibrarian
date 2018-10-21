package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.cruds.GenericCRUD;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Publications Dashboard")
@Route(value = "publications", layout = BaseFragment.class)
public class PublicationsManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public PublicationsManager() {
		setHeaderText("Publications");
		
		GenericCRUD genericCRUD = new GenericCRUD(GenericCRUD.EntityAction.PUBLICATIONS);
		add(genericCRUD);
		
		SmallButton discontbtn = new SmallButton("Discontinued");
		discontbtn.addClassName("btn-mr");
		SmallButton lostbtn = new SmallButton("Lost");
		lostbtn.addClassName("btn-mr");
		SmallButton issuedbtn = new SmallButton("Issued");
		issuedbtn.addClassName("btn-mr");

		addToolbarComponent(discontbtn, lostbtn, issuedbtn);

	}

}
