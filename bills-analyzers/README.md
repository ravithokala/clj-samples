# phonebill-analyzer

A Clojure library designed to analyze the phone bill

## Usage

(use 'phonebill-analyzer.core)
(get-talk-times "/tmp/lyca1.txt")

Sample phone bill file
----------------------
TYPE OF CALL,CALLED,NUMBER,DATE,TIME,DURATION,COST
VOICE,918019821345,19/12/2012,18:00:09,0:09:14,0.0000
VOICE,919441763129,19/12/2012,17:11:27,0:11:28,0.0000
SMS,447440224871,18/12/2012,12:53:14,0,0.0000

## License

Distributed under the Eclipse Public License, the same as Clojure.
