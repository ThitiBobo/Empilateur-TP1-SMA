# Empilateur-TP1-SMA

Baptiste Magous -Thibaut Delplanque 

## Présentation du code



## Analyse des résultats

À l'aide de notre implémentation, nous pouvons exécuter nos agents, dit agents tropiques, pour notre problème d'empilement ordonné de bloc. 

Dans cette première implémentation, nos agents se contentent de se déplacer seulement s'ils n'ont pas atteint leur objectif ou si un bloque en dessous de eux, les poussent. Enfin, nos agents vérifient seulement si le bloque en dessous d'eux correspond à leur objectif.

Reprenons l'exemple du cours, où nous avons 4 agents (bloques) identifier avec les lettres A, B, C ou D pour seulement 3 piles. la configuration de départ se présente de la manière suivante, nos quatre agents sont empilés sur une seule pile, dans l'ordre B, D, A et C (de bas en haut) et doivent atteindre la configuration, A, B, C et D.

<img src="img/exemple-cours.svg" alt="exemple-cours" style="zoom:150%;" />

Dans la plus grande majorité des cas, nous arrivons à atteindre la situation voulue, par exemple, pour une exécution nous arrivons à l'objectif final avec **41** mouvements.

Nous pouvons analyse la performance de nos agents pour cette première implémentation, on exécute 100 fois le scénario et on regarde les résultats qu'on obtient.

<img src="img/tableau-1.svg" alt="tableau-1" style="zoom:110%;  display: block;
margin-left: auto;
margin-right: auto;" />

On remarque que sur nos **100** exécutions, on atteint la solution **97%** du temps, la moyenne des mouvements pour l'atteindre est de **43,93** et la médiane est de **38** mouvements. Nous pouvons aussi voir que lors d'une exécution, on a obtenu la solution en **8** mouvement, alors que le nombre de mouvements théoriques est de **7**.

## Amélioration et stratégies

Nous pouvons, maintenant, améliorer le comportement de nos agents et proposer des stratégies de coordination pour rendre plus performant la planification.

### Stratégie 1

Cette première stratégie consiste à améliorer la fonction d'évaluation de l'objectif de nos agents, au lieu de vérifier  si l'agent est sur le bloque voulus, l'agent va vérifier en plus, si tous les agents en dessous de lui ont atteint leur objectif pour considérer son objectif satisfait.

<img src="img/tableau-2.svg" alt="tableau-2" style="zoom:110%;" />

Avec cette première stratégie, sur **100** lancers, on atteint la solution **99%** du temps, le moyenne des mouvements est de **40,48** et la médiane est de **33** mouvements. 

Cette première amélioration semble nous donner de meilleurs résultats que l'implémentation initiale.

### Stratégie 2

Notre seconde stratégie est de faire réaliser à tous nos agents, que des déplacements utiles. C'est-à-dire, nos agents effectueront leurs déplacements seulement si celui-ci valide leur objectif. 

On ajoute à cette stratégie quelques règles supplémentaires pour éviter les situations autobloquantes:

- Les agents doivent bouger s'ils sont poussés par un agent en dessous d'eux.
- Les agents qui n'ont pas pour objectif le bas de la pile et qui se retrouve à même le sol, doivent bouger (cette règle évite d'entrer dans une situation bloquante où tous les agents se retrouvent à la base d'une pile et n'ont plus envie de bouger sauf un agent qui est le seul à vouloir être à la base de la pile et qui ne peut pas).
- Les agents satisfaits, poussent les agents insatisfaits au-dessus d'eux (cette règle évite, lorsque la pile commence à se former correctement, de voir des agents se mettre à son sommet, insatisfait et la bloqué).

<img src="img/tableau-3.svg" alt="tableau-3" style="zoom:110%;" />

Avec cette seconde stratégie, sur **100** lancers, on atteint la solution **100%** du temps, le moyenne des mouvements est de **26,76** et la médiane est de **21** mouvements. 
