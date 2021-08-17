package com.example.test;


import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.test.layout.MenuLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route(value="", layout=MenuLayout.class)
@PageTitle("Home Page")
@PWA(
        name = "main view",shortName = "M3",
        offlineResources = { 
                "./styles/offline.css"}

)
//@StyleSheet("context://styles/global.css")

    public class MainView extends VerticalLayout {
    
    private HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private FormLayout formLayout = new FormLayout();
    private User currentUser;
    private Grid<User> grid = new Grid<>();
    Binder<User> binder = new Binder<>(User.class);
    
    private TextField firstNameTextField = new TextField("First Name");
    private TextField lastNameTextField = new TextField("Last Name");
    private DatePicker dobDatePicker = new DatePicker("Birth Day");
    private TextField ageNumberField = new TextField("Age");
    private NumberField assetsNumberField = new NumberField("Assets");
    private PhoneNumberField phoneNumberField = new PhoneNumberField();
    
    private EmailField emailField = new EmailField("Email");
    Details details = new Details("Expandable Details", new Text("Enter and space keys"));
    
    public MainView() {
        ListDataProvider<User> listDataProvider = DataProvider.ofCollection(fetchUsers());
        HorizontalLayout leftFlexLayout = new HorizontalLayout();
        TextField searchField = new TextField();
        searchField.setPlaceholder("this is flex layout");
        searchField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        //searchField.setHelperText("Filter by first name");
        Button searchButton = new Button("search",new Icon(VaadinIcon.SEARCH));
        //searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchButton.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
        FlexLayout wrapperFlexLayout = new FlexLayout();
        Button removeButton = new Button("remove",new Icon(VaadinIcon.FILE_REMOVE));
        removeButton.addClickListener( e ->{
            User clickUser = grid.asSingleSelect().getValue();
            listDataProvider.getItems().remove(clickUser);
            listDataProvider.refreshAll();
        });
        Button addButton = new Button("create new",new Icon(VaadinIcon.PLUS_CIRCLE_O));
        addButton.addClickListener( e ->{
            User newUser = new User();
            newUser.setFirstName("FirstName");
            newUser.setLastName("LastName");
            newUser.setAssets(1231.23);
            newUser.setAge(30);
            newUser.setEmail("newEmail@medavis.de");
            newUser.setPhoneCode("+46");
            newUser.setPhonenumber("12321312");
            newUser.setDateOfBirth(LocalDate.of(1983, 1, 2));
            listDataProvider.getItems().add(newUser);
            listDataProvider.refreshAll();
            grid.select(newUser);
            currentUser = newUser;
            binder.setBean(currentUser);
        });

        wrapperFlexLayout.add(removeButton,addButton);
        wrapperFlexLayout.setJustifyContentMode(JustifyContentMode.END);
        leftFlexLayout.add(searchField,searchButton,wrapperFlexLayout);
        leftFlexLayout.expand(wrapperFlexLayout);
        leftFlexLayout.setAlignSelf(Alignment.END,addButton);

        leftFlexLayout.getStyle().set("border", "1px dashed black");
        leftFlexLayout.setSizeFull();
        leftFlexLayout.setPadding(true);
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.addColumn(User::getFirstName).setKey("fname").setHeader("First Name");
        grid.addColumn(User::getLastName).setHeader("Last Name");
        grid.addColumn(new LocalDateRenderer<>(User::getDateOfBirth,"dd/MM/yyyy")).setHeader("Birthday").setFooter(new Html("<div style='color:green'>LocalDateRenderer</div>"));
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(TemplateRenderer.<User>of("<span style='color:red'>[[item.code]]</span> <span style='color:blue'>[[item.number]]</span>")
                .withProperty("code", User::getPhoneCode).withProperty("number", User::getPhonenumber)).setHeader("Phone").setFooter(new Html("<div style='color:green'>TemplateRenderer</div>"));
        grid.addColumn(TemplateRenderer.<User>of(""
                + "<dom-repeat items=\"[[item.childrens]]\">\r\n"
                + "      <template>\r\n"
                + "        <div><span>{{item.firstName}},{{item.age}}  </span></div> "
                + "      </template></dom-repeat>")
                .withProperty("childrens",User::getChildrensList)).setHeader("Childrens").setFooter(new Html("<div style='color:green'>TemplateRenderer dom-repeat</div>"));

        grid.getColumnByKey("fname").setComparator(Comparator.comparing(User::getFirstName));
        grid.addColumn(new NumberRenderer<>(User::getAssets,NumberFormat.getNumberInstance())).setKey("assets").setHeader("Assets").setFooter(new Html("<div style='color:green'>NumberRenderer</div>"));
        grid.addColumn(new NativeButtonRenderer("button render",clickItem ->{
            User clickUser = grid.asSingleSelect().getValue();
            listDataProvider.getItems().remove(clickUser);
            listDataProvider.refreshAll();
        })).setFooter(new Html("<div style='color:green'>NativeButtonRenderer</div>"));

        
        grid.setDataProvider(listDataProvider);
        
        //set filter
        User filterObject = new User();
        listDataProvider.setFilter( u->filterObject.test(u));
        
        // First filter
        TextField firstNameFilterField = new TextField();
        firstNameFilterField.setPlaceholder("First Name");
        firstNameFilterField.addValueChangeListener(event -> {
            filterObject.setFirstName(event.getValue());
            listDataProvider.refreshAll();
        });
        grid.getColumnByKey("fname").setHeader(firstNameFilterField);
        
        grid.addItemClickListener( i ->{
            User clickUser = grid.asSingleSelect().getValue();
            currentUser = clickUser;
            binder.setBean(currentUser);
         });
        binder.withValidator(u-> !u.getFirstName().equalsIgnoreCase(u.getLastName()), "First name and Last name can't be equal.");
        binder.forField(firstNameTextField).asRequired("first name is required").bind(user -> user.getFirstName(), (user, firstName) -> user.setFirstName(firstName));
        binder.forField(lastNameTextField).asRequired("last name is required").bind(user -> user.getLastName(), (user, lastName) -> user.setLastName(lastName));
        binder.forField(emailField).withValidator(new EmailValidator("wrong email")).bind("email");
        binder.forField(ageNumberField).withConverter(new StringToIntegerConverter("Must enter a integer")).bind("age");
        binder.forField(assetsNumberField).bind("assets");

        Select countryCode = phoneNumberField.getCountryCode();
        TextField subscriberCode = phoneNumberField.getSubscriberCode();
        binder.forField(subscriberCode).bind("phonenumber");
        
        binder.forField(dobDatePicker).bind("dateOfBirth");
        binder.forField(countryCode).bind("phoneCode");
        leftLayout.add(leftFlexLayout,grid);
        leftLayout.setAlignItems(Alignment.STRETCH);
        leftLayout.setHeightFull();
        leftLayout.expand(grid);
        leftLayout.setWidthFull();
        leftLayout.setHeightFull();
        
        
        
        //form
        formLayout.setResponsiveSteps(
                new ResponsiveStep("25em", 1),
                new ResponsiveStep("32em", 2),
                new ResponsiveStep("40em", 3));

        formLayout.add(firstNameTextField,lastNameTextField,assetsNumberField,dobDatePicker,ageNumberField,phoneNumberField,emailField);
        formLayout.setSizeFull();
        
        HorizontalLayout actions = new HorizontalLayout();
        FlexLayout buttonFlexLayout = new FlexLayout();
        
        Button saveButton = new Button("update");
        saveButton.addClickListener( e ->{
            try {
                binder.writeBean(currentUser);
            } catch (ValidationException e1) {
                Notification.show(e1.getBeanValidationErrors().get(0).getErrorMessage(), 3000, Position.MIDDLE);
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            binder.readBean(currentUser);
            listDataProvider.refreshAll();
        });
        Button resetButton = new Button("Reset");
        resetButton.addClickListener( e ->{
            binder.readBean(currentUser);
        });
        buttonFlexLayout.add(saveButton);
        actions.add(buttonFlexLayout);        
        actions.setWidthFull();
        buttonFlexLayout.setJustifyContentMode(JustifyContentMode.END);
        actions.expand(buttonFlexLayout);
        //rightLayout.add(formLayout,actions);
        //rightLayout.setSizeFull();
        
        FlexLayout footerFlexLayout = new FlexLayout();
        footerFlexLayout.setSizeFull();
        H1 titleH6 = new H1("Dom Paragraph");
        titleH6.getStyle().set("background-color", "yellow");
        titleH6.getStyle().set("text-align", "center");
        footerFlexLayout.setFlexDirection(FlexDirection.COLUMN);
        footerFlexLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        footerFlexLayout.setAlignItems(Alignment.CENTER);
        footerFlexLayout.setAlignSelf(Alignment.STRETCH, titleH6);
        Paragraph currentUserDiv = new Paragraph();
        ReadOnlyHasValue<String> allValue =  new ReadOnlyHasValue<>(text->currentUserDiv.setText(text));
        currentUserDiv.getStyle().set("background-color", "green");
        Footer autherH1= new Footer();
        autherH1.add("right by Footer");
        autherH1.getStyle().set("background-color", "pink");
        
        binder.forField(allValue).bind(User::toString,null);
        footerFlexLayout.add(titleH6,currentUserDiv,autherH1);
        footerFlexLayout.setFlexGrow(5, currentUserDiv);
        
        leftLayout.add(formLayout,actions,footerFlexLayout);
        
        mainHorizontalLayout.add(leftLayout);
        mainHorizontalLayout.setSizeFull();
        add(mainHorizontalLayout);
     
    }
    
    private List<User> fetchUsers(){
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("rong");
        user1.setLastName("yin");
        user1.setAge(50);
        user1.setEmail("rong.yin@medavis.de");
        user1.setDateOfBirth(LocalDate.of(1961, 5, 13));
        user1.setPhoneCode("+86");
        user1.setPhonenumber("13348554118");
        user1.setAssets(123213.123);
        Address address1 = new Address();
        address1.setStreet("wen ding road");
        List<User> cList1 = new ArrayList<>();
        User c1 = new User();
        c1.setFirstName("rong junior1");
        c1.setLastName("yin");
        c1.setAge(10);
        User c2 = new User();
        c2.setFirstName("rong junior2");
        c2.setLastName("yin");
        c2.setAge(10);
        cList1.add(c1);
        cList1.add(c2);
        user1.setChildrensList(cList1);
        user1.setAddress(address1);
        
        User user2 = new User();
        user2.setAssets(4423213.123);
        user2.setFirstName("belen");
        user2.setLastName("liu");
        user2.setAge(45);
        user2.setDateOfBirth(LocalDate.of(1978, 3, 13));
        user2.setEmail("belen.liu@medavis.de");
        user2.setPhoneCode("+86");
        user2.setPhonenumber("13312354118");
        Address address2 = new Address();
        address1.setStreet("xi chuan road");
        user2.setAddress(address2);
        List<User> cList2 = new ArrayList<>();
        User c3 = new User();
        c3.setFirstName("belen junior1");
        c3.setLastName("liu");
        c3.setAge(10);
        User c4 = new User();
        c4.setFirstName("belen junior2");
        c4.setLastName("liu");
        c4.setAge(10);
        cList2.add(c3);
        cList2.add(c4);
        user2.setChildrensList(cList2);
        
        User user3 = new User();
        user3.setAssets(158654.56);
        user3.setFirstName("eason");
        user3.setLastName("li");
        user3.setAge(30);
        user3.setDateOfBirth(LocalDate.of(1992, 6, 23));
        user3.setEmail("eason.li@medavis.de");
        user3.setPhoneCode("+46");
        user3.setPhonenumber("13312454118");
        Address address3 = new Address();
        address3.setStreet("pudong road");
        user3.setAddress(address3);
        List<User> cList3 = new ArrayList<>();
        User c5 = new User();
        c5.setFirstName("eason junior");
        c5.setLastName("li");
        c5.setAge(10);
        cList3.add(c5);
        user3.setChildrensList(cList3);

        User user4 = new User();
        user4.setAssets(48751555.656);
        user4.setFirstName("johnny");
        user4.setLastName("yang");
        user4.setAge(32);
        user4.setDateOfBirth(LocalDate.of(1988, 5, 21));
        user4.setEmail("eason.li@medavis.de");
        user4.setPhoneCode("+34");
        user4.setPhonenumber("13912454119");        
        Address address4 = new Address();
        address4.setStreet("nanjing road");
        user4.setAddress(address4);
        List<User> cList4 = new ArrayList<>();
        User c6 = new User();
        c6.setFirstName("johnny junior");
        c6.setLastName("li");
        c6.setAge(10);
        cList4.add(c6);
        user4.setChildrensList(cList4);
        
        list.add(user1);
        list.add(user3);
        list.add(user2);
        list.add(user4);
        return list;
        
    }
}
