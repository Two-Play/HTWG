# Protokoll SysProg
## Ausgabe

Adresse: 0x16ae57408	 Platzbedarf: 4	 Typ: int	 Name: zahlInt	 Wert: 1    
Adresse: 0x16ae57406	 Platzbedarf: 2	 Typ: short	 Name: zahlShort	 Wert: 2    
Adresse: 0x16ae573f8	 Platzbedarf: 8	 Typ: long	 Name: zahlLong	 Wert: 3    
Adresse: 0x16ae573f0	 Platzbedarf: 8	 Typ: long long	 Name: zahlLL	 Wert: -4  
Adresse: 0x16ae573ec	 Platzbedarf: 4	 Typ: unsigned int	 Name: bytefolge	 Wert: 4294967295  
Adresse: 0x16ae573ea	 Platzbedarf: 2	 Typ: unsigned short	 Name: byteShort	 Wert: 65535  
Adresse: 0x16ae573e0	 Platzbedarf: 8	 Typ: unsigned long	 Name: ul	 Wert: 23  
Adresse: 0x16ae573d8	 Platzbedarf: 8	 Typ: unsigned long long	 Name: ull	 Wert: 3  
Adresse: 0x16ae573d4	 Platzbedarf: 4	 Typ: float	 Name: pi	 Wert: 3.140000  
Adresse: 0x16ae573c8	 Platzbedarf: 8	 Typ: double	 Name: piD	 Wert: 3.140000  
Adresse: 0x16ae573c0	 Platzbedarf: 8	 Typ: long double	 Name: ld	 Wert: 1.313200  
Adresse: 0x16ae573bf	 Platzbedarf: 1	 Typ: char	 Name: zeichen	 Wert: a  
Adresse: 0x16ae573b0	 Platzbedarf: 8	 Typ: char*	 Name: s	 Wert: Hallo  
Adresse: 0x16ae573af	 Platzbedarf: 1	 Typ: unsigned char	 Name: uch	 Wert: 255  
Adresse: 0x16ae573ae	 Platzbedarf: 1	 Typ: signed char	 Name: sch	 Wert: -1  
Adresse: 0x16ae573ad	 Platzbedarf: 1	 Typ: bool	 Name: bo	 Wert: 1  

## Fragen
1.  Sind die Variablen in der Reihenfolge ihrer Definition im Hauptspeicher abgelegt?  
    **Nein, sondern genau andersrum. Das liegt daran, dass der zuletzt deklarierten Variablen der oberste Speicherplatz zugewiesen wird.**
2.  Liegen die Variablen direkt hintereinander oder gibt es Lücken? Falls es Lücken gibt, wo liegen sie? Können Sie sich erklären, warum es eventuell Lücken gibt?  
    **Es gibt Lücken, da die variablen nicht vollständig in den Speicher passen**
3.  Wie viel Speicher belegen die Variablen insgesamt, also inklusive eventueller Lücken?  
    **(0x16ae57408 - 0x16ae573ad) + 4 = 95 Byte**