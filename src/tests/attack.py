import random

def minToTime(min: int):
    hrs = str(min // 60).zfill(2)
    min = str(min % 60).zfill(2)
    return hrs + ":" + min

ATTACKS = ["NONE", "BLACK", "BLUE", "YELLOW", "RED"]
DATE = "2021-10-01"
CITIES = []
TIME = 1440 

with open("cities.txt") as file:
    for line in file.readlines():
        CITIES.append(line[:-1])

with open("attack.txt", 'w') as file:
    aChoice = ""
    cChoice = ""
    for i in range(TIME):
        aChoice = random.choice(ATTACKS)
        cChoice = random.choice(CITIES)
        if (aChoice != "NONE" and random.randint(0, 10) > 5):
            file.write(cChoice + ", " + aChoice + ", " + DATE + ", " + minToTime(i) + "\n")
        