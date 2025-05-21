# MICHENAUD MATHIS ESGI 2025

## Prérequis
- Java 17 
- Maven (installé et configuré dans votre PATH)


Structure
Classes
- Book.java : classe représentant un livre (titre, auteur, année)
- BookException : exception pour les livres
- Library.java : classe représentant une bibliothèque avec ajout, suppression et recherche de livres
- LivraryPrinter.java : classe utilitaire pour l'affichage de la librairie

Tests
- LibraryTest.java : classe de test unitaire
- LibraryFunctionalTest.java : classe de test fonctionnel
- pom.xml : fichier de configuration Maven


## Compiler le projet
Pour compiler le projet, ouvrez un terminal à la racine du projet (là où se trouve le fichier `pom.xml`) et lancez la commande :

```bash
mvn clean compile
```

## Compiler les tests
Pour compiler le projet, ouvrez un terminal à la racine du projet (là où se trouve le fichier `pom.xml`) et lancez la commande :

```bash
mvn test
```



