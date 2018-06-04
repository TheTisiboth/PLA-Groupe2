# [PLA] : *JOURNAL DE BORD* - Groupe 2

## **28/05/2018 & 29/05/2018**
- Création de notre projet de jeu, voir le doc suivant : https://docs.google.com/document/d/1xm8RrV3marbpYX4PS8rGZVIzWwMr_bfBu3qsKPhUCyg/edit
- Etude du langage automate donné par M.Perrin

## **30/05/2018**
Globalement, aujourd'hui nous avons continué de consolider l'idée de notre projet de jeu en spécifiant notamment la manière dont nous allons générer nos maps, comment le joueur va intéragir avec son entourage, etc. Nous avons aussi commencer à travailler sur l'étude du Framework et son utilisation dès la réception de ce dernier. Pour finir, nous nous sommes aussi chargés de créer le repo GitHub de notre projet adapté à Eclipse.

#### **_Done :_**
###### *Travail en groupe*
- Spécification du fonctionnement des maps et de leur création
- Ebauche de conception de certaines classes pour la suite
- Mise en place du repo GitHub et des outils de développement nécessaires (Eclipse, ZSH, ...)

###### *Chacun seul de son côté*
- Etude du Framework de O.Gruber
- Analyse des automates de M.Perrin


#### **_ToDo :_**
- Discussion autour du prototype pour choisir ce que l'on va faire
- Eventuellement, début du code du prototype si les ressources que nous avons sont suffisantes

## **31/05/2018 & 01/06/2018**
###### *Joachim Fontfreyde*
- Organisation de groupe
- AIDE (voir Nathan) : Déplacement d'un personnage en case par case de manière fluide -> Le personnage ne se téléporte pas, il glisse jusqu'à la case suivante

###### *Léni Gauffier*
- Création de la classe pour les salles des différents niveaux 
- Création de la classe pour les cases des salles
- Principe de fonctionnement de ces deux classes mises en parallèle

###### *Maxence Brès*
- Travail sur le parser de fichier JSON -> Ces fichiers contiendront les spécifications des différents niveaux de jeu (liste des ennemis possibles, des armes possibles, des salles possibles, le familier à libérer, le boss du niveau, ...)

###### *Nathan Dalaine*
- Déplacement d'un personnage en case par case de manière fluide -> Le personnage ne se téléporte pas, il glisse jusqu'à la case suivante

###### *Eva Bardou*
- Recherche de sprites libres de droit pour les graphismes du jeu -> Infructueux
- Création du menu principal de jeu -> Fenêtre avec "Jouer", "Options", "Quitter" -> en cours

###### *Yann Gautier*
- Recherche approfondie sur les automates


#### **_ToDo :_**
- Finir les différentes parties sur lesquelles chacun travaille 


# Informations complémentaires sur le jeu

### Taille des maps :
- Largeur map : 32
- Hauteur : 22
- Plusieurs maps par niveau -> Sur la dernière map : un boss + un familier à sauver

### Codes couleur pour la création des maps :
- Cyan : Entrée
- Jaune : Sortie
- Noir : Mur
- Vert : Item
- Rouge : Ennemi
- Rose : Boss

### Convention code:
  - Tabulation
  - Variables de classe: m_ au début et majuscule entre les mots
  - Anglais
