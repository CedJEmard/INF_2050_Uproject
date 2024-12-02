# inf2050-projet-equipe4

**TP1 inf2050 equipe4** <br>
Salut nous somme l'equipe 4 !<br>

### Comment compiler/exécuter l'application:
1. *Cloner/pull* le projet (Si le clonage est déjà réalisé)
2. *Creer* une application Maven : `Edit/Add configuration/Maven, Command line : clean install`
3. *Run* le projet
4. Se deplacer dans le répertoire **target/** et lancer l'exécutable: 
   1. Soumettre une déclaration :`java -jar FormationContinue.jar declaration.json resultat.json` (attention, les fichiers d'entrée et sortie doivent être appélé par leurs path (exemple: *../declaration.json*))
   2. Réinitialiser les statistiques : `java -jar FormationContinue.jar -SR`
   3. Afficher les statistiques : `java -jar FormationContinue.jar -S`


**Note**: il faut aussi préalablement relier les sources et les tests: 
a. *Import module* si le module ne contient pas toutes les sources.
b. *Relier les sources* du projet (`src/main/java`) : `File > Project Structure > Modules` : Selectionner `src/main/java`, Mark as sources root (icone bleu).
c. *Relier les tests* du projet (`src/test/java`) : `File > Project Structure > Modules` : Selectionner `src/test/java`, Mark as test root (icone vert).

### Lancer les tests : 
- Lorsque vous lancez l'application Maven comme précisé dans le 2., cela lancera les tests.
- Le plugin jacoco calcul la couverture des tests. Elle est visite sur le site présent dans le fichier `target/site/jacoco/index.html`

### Contraintes technologiques:
- Le logiciel est développé avec le langage de programmation Java (JDK11).
- L'environnement de développement intégré utilisé est IntelliJ.
- Les fichiers d'entrées et de sorties doivent être des documents JSON.

### Autres informations importantes pour la correction:
Nous n'avons pas reçu le feedback du TP2, nous sommes navrés si vous trouvez donc des erreurs du précédent TP qui n'ont pas pu être corrigées.
Merci de votre compréhension.
