# My Personal Project 

## NBA/Fantasy NBA Application

**What will the application do?**

The NBA/Fantasy NBA application that I will build will, overarchingly, provide any user who interacts with this 
application a platform for them to explore NBA and Fantasy NBA topics. In regards to NBA topics, some features  include 
users  being able to keep track of their favourite players, create viewing schedules for themselves in order to watch 
NBA games, and create playoff brackets that they can update. 

In terms of Fantasy NBA, some features include creating and saving potential NBA fantasy teams, adding player fantasy 
statistics and having the ability to view them at a later time. 

**Who will use it?**

This application can be used by any fan of the NBA or Fantasy NBA. Its ease of use 
will also make it appealing to people who are just beginning to get interested 
in the NBA or Fantasy NBA, providing an easy to use application to learn more 
about the NBA and keep track of their favourite players and teams. 

**Why this project interests me:**

As a life long NBA fan, I want to create an application that takes my love for the game of basketball 
and combines it with my love for software programming. I want to create this application so that I have a tool that 
allows not only myself, but others to keep track of their favourite teams and players and also have a tool that will 
allow users to improve their NBA fantasy experience. 



## User Stories
* As a user, I want to be able to create a new fantasy team and add multiple NBA players to it. 
* As a user, I want to be able to add statistics for a player on a fantasy team.  
* As a user, I want to be able to select a fantasy team and view a list of players on the team.
* As a user, I want to be able to create a player with a name, jersey number, and NBA team. 
* As a user, I want to be able to look at the statistics for a player on my Fantasy Team. 
* As a user, I want to be able to save my Fantasy team, including all the players on it, with their stats
* As a user, I want to be able to load a previously saved Fantasy team, and have access to all data I had previously entered
* As a user, I want to be able to view the total stats for a player up to a specific date
* As a user, I want to be able to view the statistical leader for Points, Rebounds and(or) Assists for my team 

## Instructions for Grader
* You can generate the first required action related to adding X's to a Y by clicking the button "Create Fantasy Team", creating your NBA fantasy team, and then clicking the button "Add a player to your fantasy team"
* You can generate the second required action related to adding X's to a Y by clicking the button "Add a player to your fantasy team" twice, creating two different players. Then you press "Add Statistics for your Player" button twice, and add statistics for your two players, respectively, on each button press. Ensure that they have different values for at least one of the statistics (Points, Rebounds or Assists). Then you press the button "View Statistical Leaders on Your Team", and enter the statistical category you want to see the leader for. 
* You can locate my visual component by creating a fantasy NBA team and viewing the icons for each button once you arrive at the Fantasy Team Frame. You can further view visual components by pressing the button "View players on your team" or "Add Statistics for your Player". You can also view another visual component when you first run the GUI.
* You can save the state of my application by pressing the button "Create Fantasy Team", and once you are inside the Fantasy Team Frame, pressing the button "Save your Fantasy Team"
* You can reload the state of my application by pressing the button "Load your team" on the home screen, SO LONG AS you have a valid Fantasy Team that has already been saved. If there are no teams already saved, the load button will not appear, you will have to create a Fantasy Team, save it, and then when you return to the home screen by pressing "Return Home" the load button will be displayed (similarly, after saving, you can quit the application, re-run the GUI and since you saved a team, the load button will appear). 

## Phase 4: Task 2
* Wed Apr 12 11:57:10 PDT 2023
* Fantasy NBA team Parsa's Fantasy NBA Team was created
* Wed Apr 12 11:57:16 PDT 2023 
* A player Lebron James was created 
* Wed Apr 12 11:57:16 PDT 2023 
* Player was added to team 
* Wed Apr 12 11:57:27 PDT 2023 
* A player Anthony Davis was created 
* Wed Apr 12 11:57:27 PDT 2023 
* Player was added to team 
* Wed Apr 12 11:57:29 PDT 2023 
* Players on the Fantasy Team were returned 
* Wed Apr 12 11:57:41 PDT 2023 
* Lebron James was searched for and returned 
* Wed Apr 12 11:57:45 PDT 2023 
* Points were added for Lebron James 
* Wed Apr 12 11:57:45 PDT 2023 
* Rebounds were added for Lebron James 
* Wed Apr 12 11:57:45 PDT 2023 
* Assists were added for Lebron James 
* Wed Apr 12 11:57:50 PDT 2023 
* Lebron James was searched for and returned 
* Wed Apr 12 11:57:54 PDT 2023 
* Points leader Lebron James was returned 
* Wed Apr 12 11:58:03 PDT 2023 
* Anthony Davis was searched for and returned 
* Wed Apr 12 11:58:07 PDT 2023 
* Points were added for Anthony Davis 
* Wed Apr 12 11:58:07 PDT 2023 
* Rebounds were added for Anthony Davis 
* Wed Apr 12 11:58:07 PDT 2023 
* Assists were added for Anthony Davis 
* Wed Apr 12 11:58:10 PDT 2023 
* Players on the Fantasy Team were returned

## Phase 4: Task 3

Upon reflecting on the process to build out my Fantasy NBA application, there are a few refactoring decision I would
make. To begin, I would reconstruct the FantasyTeam class to be an abstract class. Doing so would allow me to extend 
my Fantasy Application outside the scope of just the NBA. By making the FantasyTeam class abstract, it would allow me to
also incorporate other teams, such as NHL (hockey), MLB (baseball), and NFL (football). Another refactoring choice I 
would make regards the GuiMain class. Examining my UML, there are too many fields that the class depends on. Specifically, 
I have three JFrame and two JPanel fields. In refactoring, I would redesign this aspect so that those fields were worked 
into one or more classes. Although it could be argued that this would increase the coupling, I would raise the 
counterargument that it would SUBSTANTIALLY increase the cohesion. Right now, the GuiMain class is tasked with way too 
much. It is responsible for creating and manipulating every single JFrame and JPanel in the application. By refactoring 
these into one or more classes, the GuiMain would be a class just focused on creating the GUI, as opposed to also dealing
with the setup and creation of all the components of the GUI. 

  