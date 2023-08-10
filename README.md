# My Personal Project

## What will the application do?
The project will consist of a game where the player attempts to 
dodge projectiles fired at them. Possible features include 
- lives
- different types of projectiles
- a high-score board of all the games
(X could be the score of a game. Y could be a list of high-scores) 
## Who will use it?
Bored students and children during their free time!
## Why is this project of interest to you?
Seeing that I have done to-do list types of projects in the past, 
I want to create something unique that challenges myself for this project. 
As someone who always hung around arcades as a child, what better to work
on than a simple game?
## User Stories
- As a user, I want to be able to move my character around the screen.
- As a user, I want to be able to see my current score.
- As a user, I want to be able to have my game score added to the high-score list.
- As a user, I want to be able to view my high-scores.
- As a user, I want to be able to see the projectile on the screen.
- As a user, I want to be able to save the state of the application if I wish to do so.
- As a user, I want to be able to load the state of the application if I wish to do so.
- As a user, I want to be able to clear the high-scores if I wish to do so.
- As a user, I want to be able to display the high-scores in different orders
if I wish to do so.

## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by
playing the game until you are hit by a projectile.
- You can generate the second required action related to adding Xs to a Y by
clicking the clear high scores button to remove the current Xs in Y.
- You can generate a third action related to adding Xs to a Y by clicking the Ascending 
Order button to sort Xs in Y by score in Ascending order.
- You can generate a fourth action related to adding Xs to a Y by clicking the Descending
    Order button to sort Xs in Y by score in Descending order.
- You can locate my visual component by looking at the background, projectiles,
and the rubber ducky which represents the player.
- You can save the state of my application by clicking the Save button.
- You can reload the state of my application by clicking the Load button.

## Phase 4: Task 2
- Thu Aug 10 10:12:34 PDT 2023
- Added Score: 5 2023/08/10 10:12:34
- Thu Aug 10 10:12:36 PDT 2023
- Added Score: 0 2023/08/10 10:12:36
- Thu Aug 10 10:12:41 PDT 2023
- Added Score: 2 2023/08/10 10:12:41
- Thu Aug 10 10:12:44 PDT 2023
- Added Score: 0 2023/08/10 10:12:44
- Thu Aug 10 10:12:51 PDT 2023
- Added Score: 5 2023/08/10 10:12:51
- Thu Aug 10 10:12:53 PDT 2023
- Sorted Scores in Ascending Order
- Thu Aug 10 10:12:55 PDT 2023
- Sorted Scores in Descending Order
- Thu Aug 10 10:12:57 PDT 2023
- Cleared Scores
- Thu Aug 10 10:12:59 PDT 2023
- Added Score: 0 2023/08/10 10:12:59
- Thu Aug 10 10:13:03 PDT 2023
- Added Score: 0 2023/08/10 10:13:03

## Phase 4: Task 3
Looking at the UML diagram, I realized that implementing Writable
with all the different classes that are a part of MyGame was probably
unnecessary. To simplify the code and avoid redundancy, I would refactor
it by only using toJson in MyGame and making a Json object with every
field in MyGame. Something else I would like to do is add a new projectiles
class that contains lists of projectiles instead of having a list of projectiles
in MyGame. This would allow me to add different types of projectiles more
easily with new classes extending the Projectile class. 
