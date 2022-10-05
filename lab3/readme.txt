/**********************************************************************
 *  Mönsterigenkänning readme.txt
 **********************************************************************/

 Ungefärligt antal timmar spenderade på labben (valfritt):

/**********************************************************************
 *  Empirisk    Fyll i tabellen nedan med riktiga körtider i sekunder
 *  analys      när det känns vettigt att vänta på hela beräkningen.
 *              Ge uppskattningar av körtiden i övriga fall.
 *
 **********************************************************************/
    
      N       brute                sortering
 -------------------------------------------
    150        51ms                32ms
    200        57ms                38ms
    300        89ms                43ms
    400        137ms               57ms
    800        693ms               159ms
   1600        4585ms              361ms
   3200        38125ms             901ms
   6400        lång tid(minuter)   3475ms
  12800        lång tid(timme?)    14950ms


/**********************************************************************
 *  Teoretisk   Ge ordo-uttryck för värstafallstiden för programmen som
 *  analys      en funktion av N. Ge en kort motivering.
 *
 **********************************************************************/

Brute:  Ordo(n^4)      fyra nästlade loopar där alla

Sortering: Ordo(n^2 * log(n))    
