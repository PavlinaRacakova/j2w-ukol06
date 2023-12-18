# Homework 6 - Business Cards 3.0
We'll modify the application for displaying business cards so that the business card data is stored in a database. This way, the data won't be lost when the application restarts. If you made changes to the appearance of the pages in previous tasks with business cards, feel free to use the modified styles and pages here.

The application will display a list of business cards on the home page. Clicking on a business card will display its details – a page with a single business card, and below it, there will be a map. The home page will also have a button to add a business card. Clicking it will display a form for adding a business card. You don't need to implement card editing and deletion, but you can do it as a bonus task.

The repository contains scripts for creating a database and sample page templates. In the Java code, there's only the Application class – you'll need to create all the other classes.

The database includes a table named business_card. The table contains the following columns:
* id – numerical identifier of the business card, a primary key – use the Integer type in Java
* full_name – remember that the entity will have a property named fullName
* company
* street
* city
* zip_code – stored in the database as a text of 5 characters
* email
* phone
* website
  
All text strings have a maximum length of 100 characters, except for the zip code, which always has 5 characters, and the phone number, which has a maximum of 20 characters. The email, phone, and website fields are optional in the database, while the others are mandatory. The entity will also have a read-only property fullAddress, which constructs the address from street, zip_code, and city. This property will be used in the business card detail to display the map.

To view the table in the database, run the application once to create the database. You can find the connection URL used when configuring the Database panel in IntelliJ IDEA in the file src/main/resources/application.yaml.

The code in the controller that ensures empty strings are converted to null is here:
```java
@InitBinder
public void nullStringBinding(WebDataBinder binder) {
  binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
}
```

1. Fork the source repository into your GitHub account or continue with your previous lesson's task.
2. Clone the repository from your account to your local machine.
3. Run the cloned application to create the database. Initially, it will show an error on the page http://localhost:8080/ because there's no controller in the application yet.
4. Configure the Database panel in IntelliJ IDEA to check the database contents. You can find the connection URL used for configuring the Database panel in IntelliJ IDEA in the file src/main/resources/application.yaml. Create a new Data Source on the panel, and the database is H2.
5. Create a controller (don't forget the appropriate class annotation) that will handle GET requests at the URL /. The method will display the list view, initially without any data. Verify that the browser displays a page with one business card and an empty business card functioning as a button to add one.
6. Create an entity BusinessCard according to the table description above, with the correct class annotation. Add fields based on the table description and generate properties from these fields. A custom constructor is not needed (Java automatically creates a no-argument constructor, which is sufficient for us). Don't forget to annotate the id field – it's a database identifier and should be automatically generated by the database.
7. Create a repository to access the database table of business cards. The repository doesn't have to be named specifically, but remember to use the correct annotation. The repository won't be a class, but an interface extending the CrudRepository. When extending CrudRepository, specify the entity type (BusinessCard) and the primary key type (Integer).
8. Modify the controller to include a field for the repository. Create a constructor for the controller that receives the repository as a parameter and stores it in a field, allowing the repository to be used later in the controller.
9. Modify the controller method that displays the list of business cards to fetch the list of business cards from the repository using findAll(). Insert the list of business cards into the model under a specific key, such as list.
10. Adjust the list.ftlh template to loop through the list of business cards using [#list] and display them on the page. Now you can check in the browser that the home page displays all business cards from the database, not just one. You can open the business_card table in IntelliJ IDEA, add a new record or modify an existing one, and verify that the data changes after refreshing the page in the browser.
11. Ensure that the links on the home page work correctly – the first business card should link to http://localhost:8080/1, the second to http://localhost:8080/2, and so on. The numbers after the slash represent the ID of the database records and don't necessarily start from one.
12. Implement a controller method to handle GET requests at the /card/{id} address, where {id} is a number passed as a parameter. Based on this ID, retrieve a single record from the database using the repository's findById(). This method returns an Optional<BusinessCard>. Check if the Optional contains a value. If it does, insert it into the model and display it using the card.ftlh template. If the Optional doesn't contain data (i.e., the business card with the given ID doesn't exist), terminate the method by returning a response signaling a 404 Not found status to the browser:
```java
return ResponseEntity.notFound().build();
```
13. Modify the card.ftlh template to display the data from the model. Use the fullAddress property for displaying the map. The HTML code for displaying the address will look like this (assuming the business card data is stored in the model under the key businessCard):
```html
<iframe style="border:none" src="https://frame.mapy.cz/?q=${businessCard.fullAddress?url}" width="100%" height="100%" frameborder="0"></iframe>
```
14. Test in the browser that business card details are displayed correctly. Also, verify that an error is displayed in the browser (it's a page directly shown by the browser) if you enter a non-existing ID in the address.
15. Add a controller method that handles GET requests at the /new. The method should simply display the form.ftlh template. Modify the form to submit data via POST to the /new. Check in the browser that the link to add a business card on the home page works.
16. Add a POST method to the controller that handles requests at the /new. It should accept a BusinessCard entity as a parameter, which will be used to transfer data from the form. Use the repository's save() method to save the business card. After saving, redirect the user to the home page. Check in the browser that adding a business card works.
17. Bonus 1: The form for adding a business card already has some validation, but these are only recommendations for the browser; the user can bypass them, for example, by modifying the page in the browser to remove the validation. In our case, this might only harm their own business cards, but the server should always validate user input in a real application. You can add validation annotations to the entity as a bonus, implement validation in the controller, and based on the validation results, decide whether to display the form again due to errors or proceed with saving the record in the database. For zip code validation, you can use the annotation `@Pattern(regexp = "\\d{5}")` – this regular expression checks that the text contains exactly five digits. Tip: If you don't want to remove all client-side validations from the form, add an empty novalidate attribute to the form (HTML element <form>). This disables the browser's validation.
18. Bonus 2: You can add a button on the detail page to delete the business card or to edit it. The edit button could redirect the user to a form page with the current data pre-filled. After saving the changes, redirect the user back to the business card detail page to see the updated information. After deleting the business card, redirect the user to the home page. In the controller, add methods to display the edit form (GET method), save the updated business card (called by the POST method), and delete the business card by ID (also a POST method). The method for saving the business card should validate the input data and, if there are any errors, display the form again with validation errors.
19. Ensure that everything works as expected.
20. Commit and push the changes (the final code) to your repository on GitHub.
21. Submit the link to your repository as the solution to the task on the Moje Czechitas portal.

# Úkol 6 – Vizitky 3.0

Aplikaci pro zobrazování vizitek upravíme tak, aby údaje o vizitkách měla uložené v databázi. Údaje se tak (konečně!) nebudou ztrácet při restartu aplikace.
Jako výchozí repository použij toto repository, je zde nakonfigurovaná databáze. Pokud sis v předchozích úkolech s vizitkami upravovala vzhled stránky,
můžeš úpravené styly a stránky použít i zde.

Aplikace bude na úvodní stránce zobrazovat seznam vizitek (šablona `seznam.ftlh`). Po kliknutí na vizitku se zobrazí její detail – stránka s jednou vizitkou,
pod vizitkou bude mapa (šablona `vizitka.ftlh`). Na titulní stránce je také tlačítko pro přidání vizitky. To zobrazí formulář pro přidání vizitky – šablona
`formular.ftlh`. Úpravu a mazání vizitky implementovat nemusíš, ale můžeš to udělat jako bonusový úkol.

Repository obsahuje skripty pro vytvoření databáze a vzorové šablony stránek. V Java kódu obsahuje jenom třídu `Application` – všechny ostatní třídy musíš
vytvořit ty. 

Databáze obsahuje jednu tabulku pojmenovanou `vizitka`. Tabulka obsahuje následující sloupečky:

* `id` – číselný identifikátor vizitky, primární klíč – v Javě pro něj použij typ `Integer`
* `cele_jmeno` – nezapomeň, že v entitě bude property pojmenovaná `celeJmeno`
* `firma`
* `ulice`
* `obec`
* `psc` – PSČ je v databázi uložené jako text o délce 5 znaků
* `email`
* `telefon`
* `web`

Všechny textové řetězce mají maximální délku 100 znaků, s výjimkou PSČ, které má vždy 5 znaků, a s výjimkou telefonního čísla, které má maximálně 20 znaků.
Údaje `email`, `telefon` a `web` jsou v databázi nepovinné, ostatní údaje jsou povinné. V entitě bude také read-only property `celaAdresa`, která poskládá adresu
z ulice, PSČ a obce. tato property se použije v detailu vizitky pro zobrazení mapy.

Pokud se chce do tabulky v databázi podívat, spusť jednou aplikaci, aby se databáze vytvořila. Připojovací URL, které se zadává při konfiguraci panelu Database
v IntelliJ Idea, najdeš v souboru `src/main/resources/application.yaml`.

Kód, který v controlleru zajistí, aby se prázdné stringy převedly na `null`, je zde:
```java
@InitBinder
public void nullStringBinding(WebDataBinder binder) {
  binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
}
```
1. Udělej fork zdrojového repository do svého účtu na GitHubu, případně pokračuj ve svém úkolu z předchozí lekce.
1. Naklonuj si repository **ze svého účtu** na GitHubu na lokální počítač.
1. Spusť si naklonovanou aplikaci, aby se vytvotřila databáze. V prohlížeči se na stránce [http://localhost:8080/](http://localhost:8080/) zatím bude zobrazovat
   jen chyba, v aplikaci není žádný controller.
1. Zprovozni si panel Database v IntelliJ Idea, ať si můžeš ověřit, co je v databázi. Připojovací URL, které se zadává při konfiguraci panelu Database
   v IntelliJ Idea, najdeš v souboru `src/main/resources/application.yaml`. Na panelu se vytváří nový *Data Source*, databáze je *H2*.
1. Vytvoři si controller (nezapomeň na odpovídající anotaci třídy), která bude odpovídat na požadavky `GET` na URL `/`. Metoda zobrazí view `seznam`, zatím
   bez dat. Ověř si, že se v prohlížeči zobrazí stránka s jednou vizitkou a jednou prázdnou vizitkou, která funguje jako tlačítko pro přidání.
1. Vytvoř entitu `Vizitka`, nezapomeň na správnou anotaci třídy. Přidej fieldy na základě popisu tabulky výše a z fieldů vygeneruj properties. Vlastní
   konstruktor není potřeba (Java automaticky vytvoří bezparametrický konstruktor, který nám stačí). Nezapomeň field `id` označit anotacemi – jde o databázový
   identifikátor a databáze ho má generovat automaticky.
1. Vytvoř respository pro přístup k databázové tabulce s vizitkami. Na jménu repository nezáleží, nezapomeň však na správnou anotaci. Repository nebude třída
   (`class`), ale rozhraní (`interface`) a rozšiřuje (`extends`) rozhraní `CrudRepository`. Při rozšiřování `CrudRepository` je potřeba uvést typ entity (`Vizitka`)
   a typ primárního klíče (databázového identifikátoru) `Integer`.
1. Uprav controller tak, že bude mít field pro repository. Vytvoř pro controller konstruktor, který dostane repository jako vstupí parametr a uloží si ho do fieldu,
   aby bylo možné repository později v controlleru používat.
1. Uprav metodu controlleru, která zobrazuje seznam vizitek, aby z repository získala seznam vizitek voláním `findAll()`. Seznam vizitek vlož do modelu pod
   nějakým klíčem, třeba `seznam`.
1. Uprav šablonu `seznam.ftlh` tak, aby pomocí `[#list]` procházela seznam vizitek a vypisovala je na stránku. Teď si zase můžeš v prohlížeči zkontrolovat, že
   se úvodní stránka zobrazuje správně a už na ní není jedna vizitka, ale všechny vizitky zadané v databázi. Můžeš si otevřít tabulku vizitka v IntelliJ Idea a
   přidat do ní nový záznam nebo záznam upravit a ověřit, že se v prohlížeči po obnově stránky data změní.
1. Zkontroluj, že správně fungují odkaz na úvodní stránce – první vizitka by měla odkazovat na adresu `http://localhost:8080/1`, druhá na `http://localhost:8080/2`.
   Čísla za lomítkem jsou ID databázového záznamum tj. nemusí začínat jedničkou.
1. Zprovozni metodu controlleru, která bude reagovat na požadavky metodou `GET`, které budou mít v URL hned za lomítkem číslo. Číslo bude předáno jako parametr
   dovnitř metody. Na základě tohoto ID načti pomocí repository z databáze jeden záznam s odpovídajícím ID. Dostaneš na výstupu typ `Optional<Vizitka>`. Ověříš,
   zda je v `Optional` přítomná hodnota. Pokud ano, vložíš ji do modelu a zobrazíš pomocí šablony `vizitka.ftlh`. Pokud v `Optional` nebudou data přítomná
   (vizitka s daným ID neexistuje), ukončíš metodu voláním `return` s návratovým kódem, který prohlížeči signalizuje stav `404 Not found` – stránka nenalezena.
   Použij k tomu tento kód:
   ```java
   return ResponseEntity.notFound().build();
   ```
1. Uprav šablou `vizitka.ftlh`, aby zobrazovala data z modelu. Pro zobrazení mapy použij property `celaAdresa`. HTML kód pro zobrazení adresy bude vypadat takto
   (předpokládám, že údaje o vizitce jsou v modelu uložené pod klíčem `vizitka`):
   ```html
   <iframe style="border:none" src="https://frame.mapy.cz/?q=${vizitka.celaAdresa?url}" width="100%" height="100%" frameborder="0"></iframe>
   ```
1. Vyzkoušej v prohlížeči, že se správně zobrazují detaily vizitky. A také že se zobrazí v prohlížeči chyba (je to stránka zobrazená přímo prohlížečem), pokud
   v adrese zadáš nějaké neexistující ID.   
1. Do controlleru přidej metodu, která bude reagovat na `GET` požadavky na adrese `/nova`. Metoda jen zobrazí šablonu `formular.ftlh`. Uprav formulář tak,
   aby odesílal data metodou `POST` na adresu `/nova`. Vyzkoušej v prohlížeči, že funguje odkaz na přidání vizitky na úvodní stránce.
1. Do controlleru přidej POST metodu, která bude reagovat na `POST` požadavky na adrese `/nova`. Jako parametr bude přijímat entitu `Vizitka`, použijeme ji i
   pro přenos dat z formuláře. Použij metodu `save()` repository pro uložení vizitky. Po uložení vizitky přesměruj uživatele na úvodní stránku. Vyzkoušej
   v prohlížeči, že funguje přidání vizitky.
1. **Bonus 1** Formulář pro přidání vizitky má už na sobě validace. To jsou však jen doporučení pro prohlížeč, uživatel je může obejít – může si např.
   v prohlížeči stránku upravit tak, že validace odstraní. V našem případě by nanejvýš poškodil své vlastní vizitky, navíc povinnost údajů hlídá i databáze
   (ta ale třeba pustí prázdné jméno – kontroluje jenom zda není `null`). V reálné aplikaci je tedy vždy potřeba kontrolovat vstup uživatele i na serveru. Můžeš
   tedy jako bonus doplnit do entity i validační anotace, přidat validaci do controlleru a podle výsledku validace zjišťovat, zda znovu zobrazit formulář, nebo
   zda je validace bez chyb a je možné záznam uložit do databáze. Pro validaci PSČ můžeš použít anotaci `@Pattern(regexp = "\\d{5}")` – uvedený regulární výraz
   kontroluje, že text obsahuje přesně pět číslic. Tip – pokud nechceš odebírat všechny klientské validace z formuláře, stačí na formulář (HTML element `<form>`)
   přidat prázdný atribut `novalidate`. Tím se validace v prohlížeči vypnou.
1. **Bonus 2** Můžeš na stránku s detailem přidat tlačítko pro mazání vizitky, případně pro její úpravu. Tlačítko pro úpravu by uživatele přesměrovalo na stránku
   s formulářem, kde budou předvyplněné současné údaje. Po uložení přesměruj uživatele zpět na stránku s detailem vizitky, kde už uživatel uvidí změněné údaje.
   Po smazání vizitky uživatele přesměruj na úvodní stránku. V controlleru přidej metody, které zobrazí formulář pro editaci (metodou `GET`), uloží upravenou
   vizitku (metoda bude volána metodou `POST`) a metodu, která smaže vizitku určenou ID (také metoda `POST`). Metoda pro uložení vizitky by opět měla validovat
   vstupní data a zobrazit znovu formulář s validačními chybami, pokud je nějaký údaj špatně nebo chybí.
1. Zkontroluj, zda vše funguje.
1. *Commitni* a *pushnni* změny (výsledný kód) do svého repository na GitHubu.
1. Vlož odkaz na své repository jako řešení úkolu na portálu [Moje Czechitas](https://moje.czechitas.cz).

## Odkazy

* odkaz na stránku [Lekce 8](https://java.czechitas.cz/2023-podzim/java-2-online/lekce-8.html)
* Java SE 17 [Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/) – dokumentace všech tříd, které jsou součástí základní Javy ve verzi 17.
* Dokumentace [Spring Boot](https://spring.io/projects/spring-boot#learn) – odsud je anotace `@SpringBootApplication` a třída `SpringApplication`.
* Dokumentace [Spring Framework](https://spring.io/projects/spring-framework#learn) – odsud jsou anotace `@Controller`, `@GetRequest` a třída `ModelAndView`.
* Dokumentace [Freemarker](https://freemarker.apache.org/docs/) – šablonovací systém pro HTML použitý v projektu.
* Dokumentace [HTML formulářů](https://developer.mozilla.org/en-US/docs/Learn/Forms)
* [Bootstrap](https://getbootstrap.com) – jeden z CSS frameworků
* [Bootstrap Icons](https://icons.getbootstrap.com) – sada ikon pro použití na webu
* [Unsplash](https://unsplash.com) – obrázky a fotografie k použití zdarma
