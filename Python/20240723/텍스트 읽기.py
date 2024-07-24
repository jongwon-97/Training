import random

hangules = list("가나다라마바사아자차카나타파하")

with open("info.txt","w")as file:
    for i in range(1000):
        name=random.choice(hangules)+random.choice(hangules)
        weight=random.randrange(40,100)
        height=random.randrange(140,200)
        
        file.write("{},{},{}\n".format(name,weight,height))