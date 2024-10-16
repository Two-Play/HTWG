lexer grammar ExprLexer;

Uhrzeit: Stunden ':' Minuten ;
Bus: 'Bus' Digits ;
Zug: 'RE' Digits ;
Schiff: 'KAT' ;
Ort: 'nach' Text (',' Text)* ;
Wochentag: WochenTag ;
Nicht: 'nicht' ;
Datum: Digits '.' Monat ;
Zeitraum: Datum 'bis' Datum ;
Täglich: 'täglich' ;

fragment Text: [a-zA-ZäöüÄÖÜß()]+ ;
fragment Stunden: ('0'..'1' Digits) | ('2' [0-3]) ;
fragment Minuten: [0-5] Digits ;
fragment Digits: [0-9]+ ;
fragment WochenTag: 'Mo' | 'Di' | 'Mi' | 'Do' | 'Fr' | 'Sa' | 'So' ;
fragment Monat: 'Jan' | 'Feb' | 'Mär' | 'Apr' | 'Mai' | 'Jun' | 'Jul' | 'Aug' | 'Sep' | 'Okt' | 'Nov' | 'Dez' ;

WS: [ \t\r\n]+ -> skip ;