package com.pc.app.ui.cruds;

import com.pc.db.MDB;
import com.pc.entity.*;
import com.pc.enums.DialogAction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@PageTitle("WebLibrarian")
//@Route(value = "home")
public class GenericCRUD extends FlexLayout
{
    Div container = new Div();
    private static final long serialVersionUID = 1L;
    
    //private static Logger logger = LoggerFactory.getLogger(GenericCRUD.class);
    
    private Span count = new Span();
    
    EntityAction action;
    
    Button createButton = new Button();
    
    Grid<? extends BaseEntity> genericGrid = new Grid<>();
    private BaseEntity entity = new BaseEntity();
    //int columnCount = grid.getColumns().size();
    
    public GenericCRUD(EntityAction action)
    {
        log.info("Generic CRUD");
        //logger.info("Logger Generic CRUD");
        this.action = action;
        switch (action)
        {
            case PUBLICATIONS:
                log.info("Generic CRUD>PUBLICATION");
                createButton.addClickListener(click -> addEntityCRUD(new Publication(), action));
                break;
            case AUDIOVIDEO:
                log.info("Generic CRUD>AUDIOVIDEO");
                createButton.addClickListener(click -> addEntityCRUD(new AudioVideo(), action));
                break;
            case LIBRARY_USERS:
                log.info("Generic CRUD>LIBRARY_USERS");
                createButton.addClickListener(click -> addEntityCRUD(new LibraryUser(), action));
                break;
            case AUTHOR:
                log.info("Generic CRUD>AUTHORS");
                createButton.addClickListener(click -> addEntityCRUD(new Author(), action));
                break;
            case PUBLISHER:
                log.info("Generic CRUD>PUBLISHERS");
                createButton.addClickListener(click -> addEntityCRUD(new Publisher(), action));
                break;
        }
        createButton.setText("Add New " + action.getSingular());
        getStyle()//
                .set("padding", "1em")//
                .set("flex-grow", "1")//
                .set("flexDirection", "column");
        
        count.getStyle().set("color", "#777777");
        
        createButton.getElement().setAttribute("theme", "small primary");
        
        FlexLayout flex = new FlexLayout(new H3(action.getSingular()), createButton);
        flex.setAlignItems(Alignment.START);
        flex.setJustifyContentMode(JustifyContentMode.BETWEEN);
        add(flex);
        
        switch (action)
        {
            case PUBLICATIONS:
                log.info("Generic CRUD>PUBLICATION>GRID");
                entity = new Publication();
                Grid<Publication> grid = new Grid<>();
                List<Publication> allItems = MDB.getPublications(0, 50, "createdDate", true);
                log.info("Generic CRUD>PUBLICATION>GRID>");
                grid.setDataProvider(new ListDataProvider<>(allItems));
                log.info("Generic CRUD>PUBLICATION>GRID>>");
                grid.setSelectionMode(Grid.SelectionMode.NONE);
                grid.addComponentColumn(publication ->
                {
                    Button pcode = new Button(publication.getTitle());
                    pcode.getElement().setAttribute("theme", "tertiary");
                    pcode.setClassName("font-size-s");
                    pcode.addClickListener(clk -> openPublicationView(publication));
                    return pcode;
                }).setHeader(action.getSingular()).setFooter(count);
                grid.addColumn(Publication::getBinding).setHeader("Binding");
                grid.addColumn(Publication::getDescription).setHeader("Description");
                grid.addColumn(Publication::getEdition).setHeader("Edition");
                grid.addColumn(Publication::getFirstPublishedDate).setHeader("First Published Date");
                grid.addColumn(Publication::getGenre).setHeader("Genre");
                grid.addColumn(Publication::getNumberOfPages).setHeader("No Of Pages");
                grid.addColumn(Publication::getPricinginformation).setHeader("Pricing Information");
                grid.addColumn(Publication::getPublicationType).setHeader("Pricing Type");
                grid.addColumn(Publication::getAuthorId).setHeader("Author(s)");
                grid.addColumn(Publication::getLanguage).setHeader("Language");
                grid.addColumn(Publication::getPublisherId).setHeader("Publisher");
                
                grid.addColumn(publication -> publication.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Created");
                grid.addComponentColumn(publication ->
                {
                    Div fl = new Div();
                    fl.getStyle().set("textAlign", "right");
                    
                    Button edit = new Button(new IronIcon("lumo", "edit"));
                    edit.getElement().setAttribute("theme", "small icon");
                    edit.getStyle().set("marginRight", "0.4em");
                    edit.addClickListener(click -> editPublisherView(publication, action));
                    
                    Button delete = new Button(new IronIcon("lumo", "cross"));
                    delete.getElement().setAttribute("theme", "small icon error");
                    delete.addClickListener(click -> deletePublisherView(publication, action));
                    
                    fl.add(edit, delete);
                    return fl;
                }).setFlexGrow(0).setWidth("160px");
                genericGrid = grid;
                
                break;
            case AUDIOVIDEO:
                log.info("Generic CRUD>AUDIOVIDEO>GRID");
                entity = new AudioVideo();
                Grid<AudioVideo> gridAudioVideo = new Grid<>();
                List<AudioVideo> allAudioVideoItems = new ArrayList<>();
                gridAudioVideo.setDataProvider(new ListDataProvider<>(allAudioVideoItems));
                gridAudioVideo.setSelectionMode(Grid.SelectionMode.NONE);
                gridAudioVideo.addComponentColumn(audioVideo ->
                {
                    Button pcode = new Button(audioVideo.getTitle());
                    pcode.getElement().setAttribute("theme", "tertiary");
                    pcode.setClassName("font-size-s");
                    pcode.addClickListener(clk -> openAudioVideoView(audioVideo));
                    return pcode;
                }).setHeader(action.getSingular()).setFooter(count);
                gridAudioVideo.addColumn(AudioVideo::getTitle).setHeader("Title");
                gridAudioVideo.addColumn(AudioVideo::getReleaseDate).setHeader("Release Date");
                gridAudioVideo.addColumn(AudioVideo::getDescription).setHeader("Description");
                gridAudioVideo.addColumn(AudioVideo::getMetaData).setHeader("Meta Data");
                gridAudioVideo.addColumn(AudioVideo::getAudioVideoType).setHeader("AudioVideo Type");
                gridAudioVideo.addColumn(AudioVideo::getLanguage).setHeader("Language");
                gridAudioVideo.addColumn(AudioVideo::getPricinginformation).setHeader("Pricing Information");
                
                gridAudioVideo.addColumn(audioVideo -> audioVideo.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Created");
                gridAudioVideo.addComponentColumn(audioVideo ->
                {
                    Div fl = new Div();
                    fl.getStyle().set("textAlign", "right");
                    
                    Button edit = new Button(new IronIcon("lumo", "edit"));
                    edit.getElement().setAttribute("theme", "small icon");
                    edit.getStyle().set("marginRight", "0.4em");
                    edit.addClickListener(click -> editAudioVideoView(audioVideo, action));
                    
                    Button delete = new Button(new IronIcon("lumo", "cross"));
                    delete.getElement().setAttribute("theme", "small icon error");
                    delete.addClickListener(click -> deleteAudioVideoView(audioVideo, action));
                    
                    fl.add(edit, delete);
                    return fl;
                }).setFlexGrow(0).setWidth("160px");
                genericGrid = gridAudioVideo;
                break;
            case LIBRARY_USERS:
                log.info("Generic CRUD>LIBRARY_USERS>GRID");
                entity = new LibraryUser();
                Grid<LibraryUser> gridUsers = new Grid<>();
                List<LibraryUser> allUserItems = MDB.getLibraryUsers(0, 50, "createdDate", true);
                gridUsers.setDataProvider(new ListDataProvider<>(allUserItems));
                gridUsers.setSelectionMode(Grid.SelectionMode.NONE);
                gridUsers.addComponentColumn(libraryUser ->
                {
                    Button pcode = new Button(libraryUser.getFirstName() + " " + libraryUser.getLastName());
                    pcode.getElement().setAttribute("theme", "tertiary");
                    pcode.setClassName("font-size-s");
                    pcode.addClickListener(clk -> openLibraryUserView(libraryUser));
                    return pcode;
                }).setHeader(action.getSingular()).setFooter(count);
                gridUsers.addColumn(LibraryUser::getFirstName).setHeader("First Name");
                gridUsers.addColumn(LibraryUser::getLastName).setHeader("Last Name");
                gridUsers.addColumn(LibraryUser::getMiddleName).setHeader("Middle Name");
                gridUsers.addColumn(LibraryUser::getEmailAddress).setHeader("Email");
                gridUsers.addColumn(LibraryUser::getPhoneNumber).setHeader("Phone Number");
                gridUsers.addColumn(LibraryUser::getDateOfBirth).setHeader("Date Of Birth");
                
                gridUsers.addColumn(libraryUser -> libraryUser.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Created");
                gridUsers.addComponentColumn(libraryUser ->
                {
                    Div fl = new Div();
                    fl.getStyle().set("textAlign", "right");
                    
                    Button edit = new Button(new IronIcon("lumo", "edit"));
                    edit.getElement().setAttribute("theme", "small icon");
                    edit.getStyle().set("marginRight", "0.4em");
                    edit.addClickListener(click -> editLibraryUserView(libraryUser, action));
                    
                    Button delete = new Button(new IronIcon("lumo", "cross"));
                    delete.getElement().setAttribute("theme", "small icon error");
                    delete.addClickListener(click -> deleteLibraryUserView(libraryUser, action));
                    
                    fl.add(edit, delete);
                    return fl;
                }).setFlexGrow(0).setWidth("160px");
                genericGrid = gridUsers;
                break;
            case AUTHOR:
                log.info("Generic CRUD>AUTHOR>GRID");
                entity = new Author();
                Grid<Author> gridAuthors = new Grid<>();
                List<Author> allAuthorsItems = MDB.getAuthors(0, 50, "createdDate", true);
                gridAuthors.setDataProvider(new ListDataProvider<>(allAuthorsItems));
                gridAuthors.setSelectionMode(Grid.SelectionMode.NONE);
                gridAuthors.addComponentColumn(author ->
                {
                    Button pcode = new Button(author.getFirstName() + " " + author.getLastName());
                    pcode.getElement().setAttribute("theme", "tertiary");
                    pcode.setClassName("font-size-s");
                    pcode.addClickListener(clk -> openAuthorView(author));
                    return pcode;
                }).setHeader(action.getSingular()).setFooter(count);
                gridAuthors.addColumn(Author::getEmailAddress).setHeader("Email");
                gridAuthors.addColumn(Author::getGender).setHeader("Gender");
                gridAuthors.addColumn(Author::getWikiLink).setHeader("WikiLink");
                gridAuthors.addColumn(Author::getImageURL).setHeader("ImgeURL");
                gridAuthors.addColumn(Author::getWebsite).setHeader("Website");
                gridAuthors.addColumn(Author::getAka).setHeader("AKA");
                gridAuthors.addColumn(Author::getDateOfBirth).setHeader("Date Of Birth");
    
                gridAuthors.addColumn(libraryUser -> libraryUser.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Created");
                gridAuthors.addComponentColumn(author ->
                {
                    Div fl = new Div();
                    fl.getStyle().set("textAlign", "right");
            
                    Button edit = new Button(new IronIcon("lumo", "edit"));
                    edit.getElement().setAttribute("theme", "small icon");
                    edit.getStyle().set("marginRight", "0.4em");
                    edit.addClickListener(click -> editAuthorView(author, action));
            
                    Button delete = new Button(new IronIcon("lumo", "cross"));
                    delete.getElement().setAttribute("theme", "small icon error");
                    delete.addClickListener(click -> deleteAuthorView(author, action));
            
                    fl.add(edit, delete);
                    return fl;
                }).setFlexGrow(0).setWidth("160px");
                genericGrid = gridAuthors;
                break;
            case PUBLISHER:
                log.info("Generic CRUD>PUBLISHER>GRID");
                entity = new Publisher();
                Grid<Publisher> gridPublisher = new Grid<>();
                List<Publisher> allPublisherItems = MDB.getPublishers(0, 50, "createdDate", true);
                gridPublisher.setDataProvider(new ListDataProvider<>(allPublisherItems));
                gridPublisher.setSelectionMode(Grid.SelectionMode.NONE);
                gridPublisher.addComponentColumn(publisher ->
                {
                    Button pcode = new Button(publisher.getPublisherName());
                    pcode.getElement().setAttribute("theme", "tertiary");
                    pcode.setClassName("font-size-s");
                    pcode.addClickListener(clk -> openPublisherView(publisher));
                    return pcode;
                }).setHeader(action.getSingular()).setFooter(count);
                gridPublisher.addColumn(Publisher::getPublisherWebsite).setHeader("Email");
                gridPublisher.addColumn(Publisher::getImageUrl).setHeader("Gender");
                gridPublisher.addColumn(Publisher::getCopyright).setHeader("WikiLink");
                gridPublisher.addColumn(Publisher::getWikiLink).setHeader("ImgeURL");
                gridPublisher.addColumn(publisher -> publisher.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Created");
                gridPublisher.addComponentColumn(publisher ->
                {
                    Div fl = new Div();
                    fl.getStyle().set("textAlign", "right");
            
                    Button edit = new Button(new IronIcon("lumo", "edit"));
                    edit.getElement().setAttribute("theme", "small icon");
                    edit.getStyle().set("marginRight", "0.4em");
                    edit.addClickListener(click -> editPublisherView(publisher, action));
            
                    Button delete = new Button(new IronIcon("lumo", "cross"));
                    delete.getElement().setAttribute("theme", "small icon error");
                    delete.addClickListener(click -> deletePublisherView(publisher, action));
            
                    fl.add(edit, delete);
                    return fl;
                }).setFlexGrow(0).setWidth("160px");
                genericGrid = gridPublisher;
                break;
            default:
                break;
        }
        add(genericGrid);
        refreshDataTable(entity);
    }
    
    private void openPublisherView(Publisher publisher)
    {
    
    }
    
    private void openAuthorView(Author author)
    {
    
    }
    
    private void deleteLibraryUserView(LibraryUser libraryUser, EntityAction entityAction)
    {
        log.info("deleteLibraryUserView>"+ entityAction);
        new AddEntityDiv(libraryUser, DialogAction.DELETE, entityAction, i -> {
            i.delete();
            refreshDataTable(libraryUser);
        }).open();
    }
    
    private void editLibraryUserView(LibraryUser libraryUser, EntityAction entityAction)
    {
        log.info("editLibraryUserView>"+ entityAction);
        new AddEntityDiv(libraryUser, DialogAction.EDIT, entityAction, i -> {
            i.persist(i);
            refreshDataTable(libraryUser);
        }).open();
        //refreshDataTable(libraryUser);
    }
    
    private void openLibraryUserView(LibraryUser libraryUser)
    {
    
    }
    
    private void deleteAudioVideoView(AudioVideo audioVideo, EntityAction entityAction)
    {
        log.info("deleteAudioVideoView>"+ entityAction);
        new AddEntityDiv(audioVideo, DialogAction.DELETE, entityAction, i -> {
            i.delete();
            refreshDataTable(audioVideo);
        }).open();
    }
    
    private void editAudioVideoView(AudioVideo audioVideo, EntityAction entityAction)
    {
        log.info("editAudioVideoView>"+ entityAction);
        new AddEntityDiv(audioVideo, DialogAction.EDIT, entityAction, i -> {
            i.persist(i);
            refreshDataTable(audioVideo);
        }).open();
        //refreshDataTable(audioVideo);
    }
    
    private void editAuthorView(Author author, EntityAction entityAction)
    {
        log.info("editAuthorView>"+ entityAction);
        new AddEntityDiv(author, DialogAction.EDIT, entityAction, i -> {
            i.persist(i);
            refreshDataTable(author);
        }).open();
        //refreshDataTable(audioVideo);
    }
    
    private void editPublisherView(Publisher publisher, EntityAction entityAction)
    {
        log.info("editPUblisherView>"+ entityAction);
        new AddEntityDiv(publisher, DialogAction.EDIT, entityAction, i -> {
            i.persist(i);
            refreshDataTable(publisher);
        }).open();
        //refreshDataTable(audioVideo);
    }
    
    private void openAudioVideoView(AudioVideo audioVideo)
    {
    
    }
    
    private void deletePublisherView(Publication publication, EntityAction entityAction)
    {
        log.info("deletePublisherView>"+ entityAction);
        new AddEntityDiv(publication, DialogAction.DELETE, entityAction, i -> {
            i.delete();
            refreshDataTable(publication);
        }).open();
    }
    
    private void deleteAuthorView(Author author, EntityAction entityAction)
    {
        log.info("deleteAuthorView>"+ entityAction);
        new AddEntityDiv(author, DialogAction.DELETE, entityAction, i -> {
            i.delete();
            refreshDataTable(author);
        }).open();
    }
    
    private void deletePublisherView(Publisher publisher, EntityAction entityAction)
    {
        log.info("deletePublisherView>"+ entityAction);
        new AddEntityDiv(publisher, DialogAction.DELETE, entityAction, i -> {
            i.delete();
            refreshDataTable(publisher);
        }).open();
    }
    
    private void editPublisherView(Publication publication, EntityAction entityAction)
    {
        log.info("editPublisherView>"+ entityAction);
        new AddEntityDiv(publication, DialogAction.EDIT, entityAction, i -> {
            i.persist(i);
            refreshDataTable(publication);
        }).open();
    }
    
    private void openPublicationView(Object i)
    {
    
    }
    
    private <E extends BaseEntity> void addEntityCRUD(Object obj, EntityAction entityAction)
    {
        log.info("GenericCRUD>addEntityCRUD"+ entityAction);
        if(obj instanceof Publication)
        {
            new PublicationDialog((Publication)obj, DialogAction.NEW, i -> {
                i.persist(i);
                refreshDataTable((Publication)obj);
            }).open();
        }
        if(obj instanceof AudioVideo)
        {
            new AudioVideoDialog((AudioVideo)obj, DialogAction.NEW, i -> {
                i.persist(i);
                refreshDataTable((AudioVideo)obj);
            }).open();
        }
        if(obj instanceof LibraryUser)
        {
            new LibraryUserDialog((LibraryUser)obj, DialogAction.NEW, i -> {
                i.persist(i);
                refreshDataTable((LibraryUser)obj);
            }).open();
        }
        if(obj instanceof Author)
        {
            new AuthorDialog((Author)obj, DialogAction.NEW, i -> {
                i.persist(i);
                refreshDataTable((Author)obj);
            }).open();
        }
        if(obj instanceof Publisher)
        {
            new PublisherDialog((Publisher)obj, DialogAction.NEW, i -> {
                i.persist(i);
                refreshDataTable((Publisher)obj);
            }).open();
        }
        
        
        
//        new PublicationDialog((Publication)obj, DialogAction.NEW, i -> {
//            i.persist(i);
//            if(obj instanceof Publication) refreshDataTable((Publication)obj);
//            if(obj instanceof AudioVideo) refreshDataTable((AudioVideo)obj);
//            if(obj instanceof LibraryUser) refreshDataTable((LibraryUser)obj);
//            if(obj instanceof Author) refreshDataTable((Author)obj);
//            if(obj instanceof Publisher) refreshDataTable((Publisher)obj);
//        }).open();
    }
    
    public enum EntityAction
    {
        
        PUBLICATIONS("Publication", "Publications"), AUDIOVIDEO("AudioVideo", "AudioVideos"), LIBRARY_USERS("Library User", "Library Users"), RENTALS("Rental", "Rentals"), SUBSCRIPTIONS("Subscription", "Subscriptions"), AUTHOR("Author", "Authors"), PUBLISHER("Publisher", "Publishers");
        
        private final String singular;
        private final String plural;
        
        public String getSingular()
        {
            return singular;
        }
        
        public String getPlural()
        {
            return plural;
        }
        
        
        EntityAction(String singular, String plural)
        {
            this.singular = singular;
            this.plural = plural;
        }
    }
    
    private List<? extends BaseEntity> allItems = new ArrayList<>();
    
    private <E extends BaseEntity> void refreshDataTable(E entity)
    {
        log.info("refreshDataTable"+entity);
        allItems.clear();
        if (entity instanceof Publication)
        {
            log.info("refreshDataTable>"+entity);
            List<Publication> allPublications = new ArrayList<>();
            allPublications.addAll(MDB.getPublications());
            updateCount(allPublications.size());
            genericGrid.getDataProvider().refreshAll();
            allItems = allPublications;
        }
        else if(entity instanceof AudioVideo)
        {
            log.info("refreshDataTable>"+entity);
            List<AudioVideo> allAudioVideo = new ArrayList<>();
            allAudioVideo.addAll(MDB.getAudioVideo());
            updateCount(allAudioVideo.size());
            genericGrid.getDataProvider().refreshAll();
            allItems = allAudioVideo;
        }
        else if(entity instanceof LibraryUser)
        {
            log.info("refreshDataTable>"+entity);
            List<LibraryUser> allLibraryUsers = new ArrayList<>();
            allLibraryUsers.addAll(MDB.getLibraryUsers());
            updateCount(allLibraryUsers.size());
            genericGrid.getDataProvider().refreshAll();
            allItems = allLibraryUsers;
        }
        else if(entity instanceof Author)
        {
            log.info("refreshDataTable>"+entity);
            List<Author> allAuthors = new ArrayList<>();
            allAuthors.addAll(MDB.getAuthors());
            updateCount(allAuthors.size());
            genericGrid.getDataProvider().refreshAll();
            allItems = allAuthors;
        }
        else if(entity instanceof Publisher)
        {
            log.info("refreshDataTable>"+entity);
            List<Publisher> allPublishers = new ArrayList<>();
            allPublishers.addAll(MDB.getPublishers());
            updateCount(allPublishers.size());
            genericGrid.getDataProvider().refreshAll();
            allItems = allPublishers;
        }
    }
    
    private void updateCount(int size)
    {
    
    }
    
    /*private void openCustomerView(Employee employee)
    {
        new EditEmployeeDialog(employee, DialogAction.VIEW, i -> refreshDataTable()).open();
    }
    
    private void editEmployeeView(Employee employee)
    {
        new EditEmployeeDialog(employee, DialogAction.EDIT, i ->
        {
            i.persist(i);
            refreshDataTable();
        }).open();
    }
    
    private void deleteEmployeeView(Employee employee)
    {
        new EditEmployeeDialog(employee, DialogAction.DELETE, i ->
        {
            i.delete(i);
            refreshDataTable();
        }).open();
    }
    
    private void newPublicationView()
    {
        new EditPublicationDialog(new Publication(), DialogAction.NEW, i ->
        {
            i.save(i);
            refreshDataTable();
        }).open();
    }
    private void updateEmployeeCount(int count)
    {
        employeecount.setText(String.format("Total: %d", count));
    }*/
    
    private interface ActionToPerform
    {
        void perform(Object obj);
    }
    
    public interface OnAction<T extends BaseEntity>
    {
        void action(T aClass);
    }
}

