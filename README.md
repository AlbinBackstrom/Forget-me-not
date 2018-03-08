# Forget-me-not

En androidapp som hjälper till att hålla ordning på saker med hjälp av timers/nedräkningar och datumbaserade påminnelser.

[Mockups och printscreens](https://github.com/AlbinBackstrom/Forget-me-not/blob/master/README.md#mockups-och-print-screens)  

### Technical features 
Data från användare spara i en SQLitedatabas och två olika tabeller utan samband, dessa två tabeller innehåller likvärdigt information och borde ha sparats i samma tabell. Men på grund av det valda designmönstret och utformningen på listview visade det sig vara väldigt svårt och komplicerat att skilja denna data åt och presentera den på ett tillfredsställande sätt. Eftersom layouten till timer och påminnelserna skiljer sig ifrån varandra gällande innehåll och ikoner så visas dessa två inte på samma aktivitet. Istället används två fragment som lever i samma aktivitet, i den ena visas alla påminnelser och i den andra visas alla timers.

För att skriva och läsa data till tabellerna används en databasklass som definierar databasen och dess tabeller och sedan skapar den. I en annan klass finns det en datamodell över tabellen som används när man sätter skriver till tabellerna. Alla metoder som finns för att skriva, läsa eller hantera data på annat sätt som relaterar till tabellerna finns i en egen controllerklass. Uppdelningen av metoder och klasser för databashanteringen är gjord för att ge en mer överskådlig och lätthanterad kod. Istället för att ha all kod i en klass som innebär ett väldigt osmidigt sätt att arbete på, med hundratals rader kod att scrolla genom för att leta upp rätt metod, är klasserna uppdelade.


### Navigation and design pattern
Appen består av en huvudaktivitet som i sin tur består av två stycken fragment som innehåller påminnelser respektive timer

En floating action button har valts som huvudknapp för applikationen, det är en knapp med skuggeffekt och ger illusionen att den svävar ovanför resten av UIt. Det är rekommenderat av google att inte ha fler än en sådan knapp per skärmbild som användaren ser. Dessa riktlinjer har följts och Google föreslår att den kan expandera när den trycks på.  Det är för att visa de vanligaste funktionerna i appen på ett snabbt och överskådligt sätt. Även detta har efterföljts och vid ett tryck visar knappen att man kan lägga till en ny timer och en ny påminnelse.  

Vid tryck på ny påminnelse kommer användaren till en ny aktivitet där denne kan lägga till ett namn eller rubrik, ett eventuellt meddelande, datum och en tidsangivelse då påminnelsen ska aktiveras. Även den nya aktiviteten har en floating action button som har ett “check/done-mark” som ikon. Knappen är inte placerad på den vanliga platsen som i det högra hörnet utan i en expanderad toolbar. Det är för att göra aktivteten mer levande och inte så tom som den hade blivit annars då det egentligen inte är mycket information som behöver fyllas i. Vid val av datum och tid visas två dialogfragment som innehåller standard android date- och timepickers. I början gjordes valet att ha en popup/dialog där användaren skulle fylla i påminnelsen och sen trycka så dialogfragment visades. Men detta ändrades relativt snart då det visade sig att det gav en en osmidigt gränssnitt och dålig användarupplevelse som visade dialogrutor ovanpå varandra. Timern hade dock inte detta problem, informationen som ska fyllas i är enbart en tid och en rubrik och det krävs inga dialogrutor eller fragment. Valet av att använda en dialogruta för detta gjordes då det kändes onödigt och inte så smidigt att starta och använda en helt ny aktivitet för två stycken inputrutor. 

I Googles designdokument står det: “Dialogs should never be obscured by other elements or appear partially on screen. Dialogs always retain focus until dismissed or a required action has been taken, such as choosing a setting.”


### Grafisk design
Den grafiska designen har som utgångspunkt baserats på färgerna som återfinns på blommat förgätmigej eller forget me not som den heter på engelska. Namnet på appen kommer även ifrån blomman och tanken är applikationen ska fungera som en övergripande app som hjälper användaren att komma ihåg saker. Den ljusblåa färgen är ett genomgående tema och den färg som de flesta kopplar samma med förgätmigej. Färgen i sig verkar även lite lugnande vilket kan vara till fördel om användaren är stressad och känner oro över saker som måste göras och som ligger inplanerade. Även rosa har en lungnande effekt på människor och fungerar som “accent”-färg.  Den bryter av den ljusblåa färgen på ett bra sätt samtidigt om den lyser relativt starkt och drar blicken åt sig på viktiga funktioner vilket rekommenderas av Google att man har. Resten av appen är i vit för att användaren enkelt och snabbt ska kunna se information om påminnelser eller timers och inte distraheras av en massa onödigt färger eller dylikt. 

### Vidareutveckling 
Det finns ett antal idéer för vidareutveckling för appen.

Platsbaserade påminnelser som ger en notifikation eller alarm baserad på plats. Till exempel om användaren lägger till en påminnelse om att köpa en vara på en affär. När användaren kommer i närheten av affären eller liknande ger det en notis eller alarm på telefonen. Tanken är att man antingen kan ange en specifik affär tex ICA Oden eller en generell affär som tex mataffär, hemelektronik eller järnaffär. 

Om användaren befinner sig på en plats kan han spara den aktuella platsen för att lägga till en påminnelse för ett senare tillfälle. Till exempel om en användare precis har handlat och kommer ut ur affären och kommer på att han glömt köpa en sak så lägger han till en påminnelse baserad på den aktuella platsen. På så sätt kommer det en notis eller liknande  nästa gång platsen besöks. 

En annan idé som skulle vara intressant att implementera vore att göra en funktion där användaren kan lägga till en påminnelse enbart via en rad text. Till exempel om användaren skriver i en inputruta “Tvätt, 18:30” så skapas en påminnelse vid nästa gång som 18:30 är. Eller “Tvätttid, på fredag 17:00”, eller “köp mjölk, Ica”  där den sista är är platsbaserad och ica är en favorit som är tillagd tidigare. 





## Mockups och print screens

Nedan följer print-screens på appens alla skärmbilder i den ordning som användaren troligtvis upplever. Den första visar hur man lägger till en timer, den andra hur en påminnelse läggs till och i slutet visas två stycken mockups som gjordes innan projektet påbörjades.

#### Ny timer/nedräkning
![Ny timer](https://i.imgur.com/qED6OtB.png)  


#### Ny påminelse
![Ny påminelse](https://i.imgur.com/at81vo0.png)


#### Mockups
![Övergripande bild](https://i.imgur.com/OWYPwTA.png)
![Ny påmineles mockup](https://i.imgur.com/e6i7qoc.png)
