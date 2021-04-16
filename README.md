# CSYE6200_OOD
The final project simulates a tree growth with an input of generations, growth degree and growth rule.
![image](https://github.com/yiyigit/CSYE6200_OOD/blob/main/BG%20simulation1.png)
![image](https://github.com/yiyigit/CSYE6200_OOD/blob/main/BG%20simulation2.png)

## Running the project
- The first method is to run `src/edu/neu/csye6200/ui/BGApplication.java` directly on IDE.
- Run it on Terminal or CMD: `java –jar bg-project.jar`

## UI
- Extended from the supplied BGApp abstract class and make my own User Interface application. 
- Add a ComboBox to select an available rule 
- Add a start button to generate a full simulation.
- Add a pause/resume button to pause/resume a full simulation.
- Add a stop button for early processing termination
- Display the progress and results of BG simulation using a graphical display panel

## BG
- Create supporting classes to perform Biological Growth calculations

- Create a Stem class which contains an array of child Stem instances plus length, direction, age, etc.
- Create a BGRule class which can extend an existing Stem, and possibly create new generation of stems based on a Stem growth
- Create a BGGenerationSet (aka BGLifespanSet) that holds multiple BG generations and can call the BGRule class repeatedly to “grow” successive generations.
- Add interfaces to collect growth data and generate statistics
- Demonstrate valid growth
- Simulation
  - Set initial conditions
  - Add a run() method that begins execution of a simulation loop
  - Simulation loop: Advance time by a unit amount and generate new growth
- Add instrumentation points to gather desired statistics
