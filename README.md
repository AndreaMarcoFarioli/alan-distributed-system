(In costruzione)

**Introduzione**
...



# Documentazione teorico divulgativa sull'architettura del sistema distribuito

**RMI**

** Per comprendere meglio alcuni punti e' necessario avere conoscenze pregresse di programmazione ad oggetti, in particolare di Java **

Per poter introdurre l'architettura del sistema distribuito e' necessario definire cosa sia Java RMI. Tra le varie tecnologie, e tecniche, utilizzate per distribuire delle operazioni all'interno di una rete, ognuna con le sue peculiarità, RMI (Remote Method Invocation) permette di chiamare metodi di oggetti definiti su altre macchine; sostanzialmente questa tecnologia permette di sfruttare il paradigma ad oggetti in sistemi distribuiti, molto efficace perché un sistema natura di rappresentare i dati.

Più praticamente RMI mette a disposizione un Server che registra degli oggetti da distribuire, i client si connettono al server e richiedono l'oggetto messo a disposizione, allora questo (attraverso un processo di referenziazione remota, un concetto piuttosto astruso e non immediato) viene trasferito sotto forma di referenza al client (quindi non passa l'oggetto in se, ma una interfaccia che ti permette di comunicarci) che potrà richiamare i metodi definiti. Questi non vengono eseguiti sul client, in caso contrario non esisterebbe alcuna differenza tra RMI e un qualsiasi oggetto, ma parte una richiesta al Server che comunica l'intenzione del client di eseguire il metodo, il Server fa alcuni controlli sulla validità della richiesta e infine esegue il metodo (che prima o poi ritornerà al client, e nel caso delle funzioni ritornerà il risultato senza sforzi sintattici), in questo modo si delega un'altra macchina nel supportare lo sforzo. 

La cosa interessante e' che RMI e' perfettamente ottimizzato, infatti per quanto sul client si resti in attesa di una risposta, questo non rimarrà bloccato (ovvero non blocca il thread) in quanto ogni richiesta RMI converge in un unico socket che gestisce anche il clock dei vari thread in attesa, quindi questi aspettano ma non pesano sulla CPU.

**Idealizzazione dell'architettura**

Per poter proseguire e' necessario fare una breve digressione sulle componenti principali che comporranno il sistema distribuito: il master e gli slave. Il nodo master e' quello che ha il compito di coordinare le richieste, deciderà quale nodo e' il più adatto per l'assoluzione di un compito e fornirà l'unica linea di comunicazione con l'esterno. Gli slave sono gli esecutori, quelli che effettivamente svolgono calcoli e cooperano tra di loro (attraverso il master, in quanto nessuno dei nodi slave conoscerà direttamente gli altri nodi). Precedentemente abbiamo definito, generalmente, cosa offre la tecnologia Java RMI; questa struttura qui definita verrà proposta attraverso un applicativo Java, che sfrutterà appunto RMI.

Programmare ad oggetti può semplificare di molto i lavori, ma crea comunque delle complicanze che non sono da sottovalutare. Un programma costruito su questo paradigma deve essere ben definito, astratto e soprattutto riutilizzabile; uno dei punti focali su cui si concentra il progetto e' la riusabilità del codice (e in particolare della struttura, che adesso andremo a definire) al fine di creare una base solida su cui costruire il codice. Facendo caso alle proprietà del sistema distribuito in costruzione, ovvero l'esistenza di un master generico e di nodi slave da lui controllati, viene spontaneo immaginare le due strutture intrinsecamente differenti. Tuttavia, con la giusta astrazione possiamo ricavare una struttura generica, che non e' ne master ne slave, ma che possa, mediante opportune estensioni, applicarsi per svolgere una serie di compiti. 

**Riferimenti a sistemi distribuiti e reti**

// cfr. Tanenbaum - da scrivere

**Come viene applicato RMI all'architettura**

In primo luogo possiamo isolare la natura distribuita dei nodi, ovvero separare concettualmente i compiti in aree di competenza che comporranno un'unica grande struttura. Come abbiamo accennato all'inizio, RMI e' una interfaccia che permette di invocare metodi remoti , in una macchina server, da una macchina client. Questa definizione definisce RMI come un sistema client-server le cui azioni, ovvero le richieste, verranno fatte solo ed esclusivamente dal client, il server risponderà e fornirà quello che in teoria delle reti si chiama servizio. Possiamo notare già una scomodità, un ipotetico master, che deve in grado di comunicare con ogni nodo, può solo richiedere (in quanto lui sara' Client RMI dei nodi slave) ma non potrà rispondere (in quanto i nodi slave risultano server, quindi non possono fare richieste). Possiamo modellizzare una prima idea, che comporrà in seguito le entità più in cima nella struttura, ovvero: **ogni nodo e' sia Client che Server RMI.** 

Questa prima astrazione fornisce ad ogni nodo la capacita' di fornire metodi e di richiamarli (ipotizziamo che un nodo slave voglia eseguire qualcosa che si trova su un altro nodo slave, la richiesta dovrebbe essere fatta al master che inoltrerà allora al nodo, questo fa si che il master, che di base sarebbe il client RMI, si comporti anche da server RMI).

Un'altra astrazione che si potrebbe aggiungere e' che ogni interfaccia messa a disposizioni dai nodi e' la medesima, e deve essere un metodo generico utile ad eseguire una gamma più ampia di azioni. 

**I Servizi**

Facendo un piccolo sforzo di astrazione, possiamo identificare una entità che verrà usata molto all'interno dell'architettura. I servizi sono particolari classi che hanno lo scopo di fornire uno spazio dove scrivere il codice, secondo un certo formato, in maniera tale che l'architettura, e più in generale il sistema distribuito, possano gestirlo, e distribuirlo, in maniera trasparente. Ogni metodo ed esecuzione che vengono gestiti dai nodi, vengono definiti all'interno dei campi appositi nei servizi. Questo permette di definire le attività fondamentali dello slave, e del master, come un oggetto che può venire cambiato in ogni momento (cosi' otteniamo un sistema altamente scalabile, ovvero la sua modifica non costa eccessivo tempo e lavoro, in quanto ogni servizio risulta fine a se stesso ed e' indipendente da altri servizi).

I Servizi possono essere accessibili da altri nodi, posseggono una parte che e' orientata alla distribuzione, e una parte interna accessibile solo dal servizio stesso.

Ogni servizio viene fornito, originalmente, con un insieme di metodi che definiscono il suo ciclo di vita. Infatti e' possibile far comprendere al servizio quando un superiore vuole distruggerlo o metterlo in pausa, attivarlo o riattivarlo o semplicemente crearlo. Questo genere di metodi e' molto utile perché fornisce anche una sorta di "main" da cui parte tutto il codice.

Queste identità vengono fornite in forma generica e astratta, e devono essere estese da servizi applicativi, ovvero orientati verso uno scopo.

**I Manager**

Per fornire un grado di trasparenza, e anche un contenitore in cui inserirli, i Manager giocano un ruolo chiave per la gestione dei Servizi. Infatti il Manager non e' niente di meno che un gestore di tanti servizi, gestisce quando vengono creati o distrutti e compie il ruolo fondamentale di inoltro delle richieste di esecuzione. 

I Manager sono anche propagatori degli eventi facenti parte del ciclo di vita dei servizi e gestiscono i servizi in base alle esigenze (un Manager generico, che prima o poi ci sara' e definiremo come bombe2.distributed.Main Manager, richiederà l'esecuzione di questi metodi in base alle condizioni della macchina, o in base alle richieste di altri servizi).

**Gli Eventi** (potrebbe variare il nome in Intenti)

Introduciamo ora una parte davvero importante, gli eventi. Per evento, in questo caso, si intendono tutte quelle intenzioni che hanno lo scopo di eseguire un metodo di un servizio. Come abbiamo accennato in precedenza, i servizi non conoscono minimamente la struttura del nodo (quindi non sanno cosa e' o meno implementato come servizio) e ovviamente non conoscono come e' strutturata tutto il sistema distribuito nel suo generico. Gli Eventi cercano di eludere questo limite, in quanto definiscono una richiesta che verrà eseguita solo in caso di esistenza dei metodi. Allegati agli eventi ci sono i parametri da passare ai metodi, definiscono anche la firma del metodo quindi esiste il supporto all'overload (metodi con lo stesso nome di etichetta ma con la combinazione differente dei tipi dei parametri).

**Propagazione degli Eventi attraverso i Manager**

// Definire come possono essere "esauriti" gli eventi attraverso i Manager, inoltro, propagazione top2bottom, propagazione bottom2top



**Distribuzione degli Eventi**

// Serializzazione ecc ecc



**Descrizione del pattern degli Eventi**

// argomentare - fork ecc ecc

```java
^((?:(?<fork>[a-z0-9_\-]+)#)?(?<whole>(?<first>(?:[a-z0-9]+|\^))(?:(?:\.(?:[a-z0-9]+|\^))+)?:))?(?<method>[a-z](?:[a-z0-9]+)?)$
```



**Questione sul ritorno dei valori**

//

**ReturnableObject e EventObject**

//

**Extendable Services e Services**

//

**Introduzione dei nodi (Master e Slave)**

//

**Prime problematiche di portabilità della struttura**

//

**Descrizione fondamentale del sistema distribuito (entità fondamentali)**

//

**bombe2.distributed.Main Manager**

//

**Remote Node**

//

**Introduzione Node Provider**

//



# Introduzione dei servizi fondamentali per il sistema distribuito

**Slave2MasterProvider (NodeProvider)**

**Master2SlaveProvider (NodeProvider)**

**Jumper (Master Service)**

**Autoloader (Generic Service)**



# Fault tollerance e distribuzione dei cluster



# Modelli matematici per la predizione, e l'analisi, delle prestazioni e Benchmark



# Documentazione pratica

