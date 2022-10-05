Vilka förändringar har vi gjort?
Vi har ändrat sättet vi gör insättningar i Event-kön. Innan så satte vi alltid in kollisions-eventen längst bak i kön.
Nu så sorterar vi eventen med MinHeap princip utifrån tiden till kollision.

Vilken version är bättre? Varför? 
Versionen vi har gjort är bättre på så sätt att simulationen blir nogrannare. Detta då kollisons-eventen
sker i rätt ordning.

Finns det fall då den “sämre” implementationen är bättre än den du valde ut?
Om vi inte bryr oss om att kollisions-eventen ska ske i exakt rätt ordning så är den sämre implementationen snabbare då vi 
inte behöver sortera eventen varje gång vi får in ett nytt kollisions-event.