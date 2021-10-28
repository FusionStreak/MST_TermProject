import random

def minToTime(min: int):
    hrs = str(min // 60).zfill(2)
    min = str(min % 60).zfill(2)
    return hrs + ":" + min

ATTACKS = ["NONE", "BLACK", "BLUE", "YELLOW", "RED"]
DATES = ["2021-10-01", "2021-10-02", "2021-10-03", "2021-10-04", "2021-10-05"]
CITIES = []
TIME = 1440 

with open("cities.txt") as file:
    for line in file.readlines():
        CITIES.append(line[:-1])

with open("attack.txt", 'w') as file:
    aChoice = ""
    cChoice = ""
    for DATE in DATES:
        for i in range(TIME):
            aChoice = random.choice(ATTACKS)
            cChoice = random.choice(CITIES)
            if (aChoice != "NONE" and random.randint(0, 10) > 5):
                file.write(cChoice + ", " + aChoice + ", " + DATE + ", " + minToTime(i) + "\n")
        