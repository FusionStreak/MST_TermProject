# TEAM MST - NET/OSS3004 Term Project

FALL 2021

```txt
                                 ,-^-.
                                 |\/\|
                                 `-V-'
                                   H
                                   H        PlagueSim
                                   H           v0.9
                                .-;":-.
                              ,'  |  `; \
```

## Team Members

- Madeline Quang
- Sayfullah Eid
- Tolu Adebayo

## General Structure

![General Structure](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/fusionstreak/MST_TermProject/master/structure.puml)

## Requirements

- JavaSE-17

## How To Use

- Run(either method):
  - `Main.main()` in an IDE(e.g. Ecliplse, IntelliJ, VS Code)
  - `$ java -jar TeamMST-vX.X.jar` in a terminal
- A Command Line Interface(CLI) should display in your terminal with a menu
- Follow the prompts to use the program
- You must first parse in a `Graph` in the `Graph menu` to use the rest of the functionality
- Use `Q` to return to main menu, or quit the program
- Available functions:
  - Graph
    - Import in a graph txt file, this will delete any current Graph data
    - Print the current graph, list of cities and their neighbours
  - Attack
    - Parse in an attack file
    - A new attack file does not reset the current Graph
  - Search
    - Get all cities that have had an attack
    - Get all cities that have a firewall
    - Get all cities that have a firewall and have been attacked
    - Get all cities that have an outbreak(and are not offline)
    - Get all cities that are currently offline
  - Path finding
    - Currently not working

## TODO

- Safe route:
  - List of 'safe' routes between two City nodes
  - Determine shortest routes
