package com.pc.app.ui.cruds;

import com.pc.app.ui.dialog.MDialog;
import com.pc.enums.DialogAction;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddEntityDiv extends MDialog
{
    String gid;
    
    VerticalLayout vLayout = new VerticalLayout();
    public AddEntityDiv(Object obj, DialogAction action, GenericCRUD.EntityAction entityAction, GenericCRUD.OnAction onaction)
    {
        super(action);
        
        setContents(vLayout);
    }
}
