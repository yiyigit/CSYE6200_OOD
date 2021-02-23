String password;
char inChar;
do{
char inChar =(char)System.in.read();
password+= inChar
}while(inChar != '\n');
return password;