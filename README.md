# Exstos_Grupp18
Git: https://github.com/CaptainZoraaaaa/Exstos_Grupp18.git

Starta Exsto: 
- Starta ServerMain.
  - Om server inte går att starta beror det med största sannolikhet på att IP-addressen inte är localhost. För att korrigare detta gå till Controller och ändra i metoderna:
    - registerOnServer(String username, String password)
    - logIn (String username, String password)
- Starta Main i com.example.exstos_grupp18

Inloggningsuppgifter:
- Skapa en användare genom att trycka på knappen New user, på inloggningsskärmen

Användning:

**HomePage och navigering till KanbanView.**

- Efter inloggning kommer man till sidan HomePage, för att kunna se en Kanbanboard behöver man först skapa ett projekt.
  - Skapa ett projekt genom att trycka på knappen Create project.
  - Därefter tryck direkt på View Kanban och när scenen byts tryck på main menu.
  - Om man nu trycker på My projects ska projektet dyka upp i denna lista.
  - Välj skapat projekt eller annat projekt ifall ovan steg upprepats flera gånger. 
  - Nu går det att byta mellan Kanbanboards.
  - De tasks som tilldelas dig i ett projekt kommer att synas i listan till höger om menyvalen i HomePage.

**Skapande av tasks**

- Efter att ett byte gjorts till en Kanban, tryck på knappen New task.
- Det ska nu öppnas vad som kallas för popover, en liten ruta där det går att fylla i uppgifter för en task.
  - Rubrik, deadline och status är alla tvingade fält.
- Efter att en task skapats behöver man i nuläget navigera tillbaka till HomePage genom att trycka på knappen Main menu sedan trycka på View Kanban igen.
- En nyligen skapad task ska nu synas på Kanbanboarden.

