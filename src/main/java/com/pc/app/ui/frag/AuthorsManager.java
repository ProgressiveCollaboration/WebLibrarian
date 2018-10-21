package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.cruds.GenericCRUD;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PageTitle("Authors Dashboard")
@Route(value = "authors", layout = BaseFragment.class)
public class AuthorsManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	public AuthorsManager() {
		log.info("AuthorsManager>>");
		setHeaderText("Authors");
		GenericCRUD genericCRUD = new GenericCRUD(GenericCRUD.EntityAction.AUTHOR);
		add(genericCRUD);
		/*SmallButton discontbtn = new SmallButton("Discontinued");
		discontbtn.addClassName("btn-mr");
		SmallButton lostbtn = new SmallButton("Lost");
		lostbtn.addClassName("btn-mr");
		SmallButton issuedbtn = new SmallButton("Issued");
		issuedbtn.addClassName("btn-mr");*/

		//addToolbarComponent(discontbtn, lostbtn, issuedbtn);

	}

}
