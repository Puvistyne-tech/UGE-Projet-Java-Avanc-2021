# ESCRIVA-RAJASEGAR



## ICalender


## friday back: Spring, JPA, H2 Database

### Fonctionnalités :


Crud ok
Ics ok
Google pas ok

Il y as un bug lors du compile en un coup. Il faut les faire de façon séparer...


### Base de données :


Nous stockons dans la base de données des événements qui comprennent :
Id de type long
Title de type string
Description de type string
Location de type string
Start de type LocalDateTime
End de type LocalDateTime

La BDD est reinitialisé à chaque redemarrage du serveur.

Pour créer une table avec h2 database et spring jpa il faut une annotation @entity avant la Déclaration de la classe, dans cette classe il faudra aussi les getters et setters pour chaque variable, lors de la sauvegarde d'un événement la création se fait avec le constructeur par défaut puis utilise tout les setters. 


### EventRepository:


Les requêtes sql ou les méthodes pour trouver un élément se fait a partir de l'interface eventRepository implementant JpaRepository, on va pouvoir définir les requêtes que l'on souhaite avoir. Dans cette interface il peut y avoir plusieurs méthode. 


### EventController:


C'est la classe qui va definie les méthodes de l'api ainsi que leurs routes pour y accéder. 
Les methodes CRUD sont presente dans cette classe. Les méthodes sont toutes routés a partir de /events.

- public ResponseEntity<List<Event>> getAllEvents(), GET /event:
Il est possible d'obtenir tout les événements avec cette méthode sous la forme d'une List d'event.
retourne un HttpStatus.NO_CONTENT si aucun event dans la bdd,
retourne un HttpStatus.OK si des events sont présent.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.

- public ResponseEntity<Event> getEventById(@PathVariable("id") long id), GET /event/{id}:
Il est possible d'obtenir un event grâce à son id, la présence de l'event n'étant pas obligatoire on utilise un optionnal et retourne un httpstatut.no_content si absent ou httpstatut.ok avec en body l'événement si présent. 
retourne un HttpStatus.NO_CONTENT si l'event n'est pas dans la bdd,
retourne un HttpStatus.OK si des events est présent.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.

- public ResponseEntity<Event> deleteEventById(@PathVariable Long eventId), DELETE /{eventId}:
Il est possible de supprimer un event grâce à son id.
retourne un HttpStatus.OK que l'event soit supprimer ou si l'event n'était pas présent.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.

- public ResponseEntity<Event> updateEvent(@RequestBody Event event), PUT /event:
Il est possible d'update un event à l'aide de son id.
retourne un HttpStatus.OK si l'event est modifié.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.

- public ResponseEntity<List<Event>> getEventByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date), GET /findByDate:
Il est possible d'obtenir tout les events commencant a une certaine date.
retourne un HttpStatus.NO_CONTENT si aucun event dans la bdd,
retourne un HttpStatus.OK si des events sont présent.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.

- public ResponseEntity<Event> setEvent(@RequestBody Event event), POST /event:
Il est possible de sauvegarder un event dans la base de donnée.
retourne un HttpStatus.OK si l'event est enregitré dans la bdd.
retourne un HttpStatus.INTERNAL_SERVER_ERROR en cas d'erreur lors d'une des fonctions.


### ICalController:


Nous avons décidé pour l'upload d'un icalendar par file ou url que si un des event est mal formater alors il ne doit pas perturbé l'ajout des autres event.

Pour gérer le parsing d'un fichier icalendar on utilise biweekly.
La méthode gérant l'upload d'un icalendar par fichier prend en paramètres un multipartfile, alors que celle utilisant l'url prend un string. Les opérations pour parser un ical et ajouté un event sont commune entre les deux fonctions


## Friday-Front: Angular


Toutes les fonctionnalités indiquer comme implémenté fonctionne sur le front, Nous avons essayé d'utiliser toute les fonctionnalités disponible d'angular pour le data binding. L'affichage du calendrier se fait grâce FullCalendar et on as decider de n'afficher qu'une semaine par une semaine car c'est plus jolie comme ça.


## Conclusion


La difficulté principale de ce projet fut de commencer à utiliser chaques technologies, et aussi de les connecters ensemble.
La communication pour travailler sur le projet fut quelque peu difficile.
Nous avons essayé d'utiliser google api mais nous avons seulement reussi à la faire avec fullcalendar au lieu de passer par le back donc nous l'avons supprimer.

