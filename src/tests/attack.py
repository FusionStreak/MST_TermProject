import random

def secToTime(sec: int):
    hrs = sec // 3600
    sec -= (hrs*3600)
    min = sec // 60
    sec -= (min*60)
    return str(hrs).zfill(2) + ":" + str(min).zfill(2) + ":" + str(sec).zfill(2)

ATTACKS = ["NONE", "BLACK", "BLUE", "YELLOW", "RED"]
DATES = ["2021-10-01", "2021-10-02", "2021-10-03", "2021-10-04", "2021-10-05"]
CITIES = []
TIME = 86400
MAX_PER_DAY = 100

with open("src/tests/cities.txt") as file:
    for line in file.readlines():
        CITIES.append(line[:-1])

with open("src/tests/attack.txt", 'w') as file:
    aChoice = ""
    cChoice = ""
    for DATE in DATES:
        numAttacks = 0
        for i in range(TIME):
            aChoice = random.choice(ATTACKS)
            cChoice = random.choice(CITIES)
            if (aChoice != "NONE" and random.randint(4, 1000) < 5 and numAttacks < MAX_PER_DAY):
                file.write(cChoice + ", " + aChoice + ", " + DATE + ", " + secToTime(i) + "\n")
                numAttacks += 1

        